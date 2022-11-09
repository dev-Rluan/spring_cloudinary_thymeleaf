package test.spring.cloudinary.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    // 메인 페이지
    @GetMapping("/")
    public String index(){

        return "index";
    }

    // 이미지 변경
    @PostMapping("mypageUpdateProfileImg")
    public String mypageUpdateProfileImg(Model model, HttpServletRequest req, HttpServletResponse res,
         @RequestParam("upload-img") MultipartFile profile_img) throws IOException {
        System.out.println("test");
        String url = "cloudinary_url";
        Cloudinary cloudinary = new Cloudinary(url);

        // 최적의 프로필 이미지를 위해 이미지 편집 후 업로드
        Map result = cloudinary.uploader().upload(convert(profile_img), ObjectUtils.asMap("folder", "userprofile",
                "transformation", new Transformation().gravity("auto:classic").width(400).height(400).crop("thumb")));

        String profile_img_url = (String) result.get("secure_url");



        return "redirect:result?"+profile_img_url;
    }


    // multipart -> 파일 변환(stream 사용. heroku에서 파일제어에 제약이 있기 때문)
    public File convert(MultipartFile file) throws IOException {
        // 파일명 충돌방지
        UUID uuid = UUID.randomUUID();
        String uuidFilename = uuid + "_" + file.getOriginalFilename();
        File convFile = new File(uuidFilename);
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
    // 결과 확인을 위한 페이지
    @GetMapping("result")
    public String resultPage(Model model, String url, HttpServletResponse res){

        model.addAttribute("url", url);
        return "result";
    }

}
