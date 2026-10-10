# spring_cloudinary_thymeleaf

Spring Boot에서 Cloudinary 이미지 업로드와 Thymeleaf 화면을 연동한 예제입니다. Bootstrap으로 업로드 화면을 구성합니다.

- 구현: 파일 업로드, 프로필 이미지 크롭·리사이즈, 결과 화면 이동
- 구성: Java 11, Spring Boot 2.7.5, Cloudinary, Thymeleaf, Bootstrap
- 업로드 처리: [MainController.java](src/main/java/test/spring/cloudinary/controller/MainController.java)
- 화면: [templates](src/main/resources/templates)

## 실행

JDK 11을 준비하고 `MainController`의 `cloudinary_url` 자리표시자를 본인의 Cloudinary 설정으로 구성한 뒤 실행합니다. 인증 값은 저장소에 커밋하지 않습니다.

```bash
./gradlew bootRun
```

Windows에서는 `gradlew.bat bootRun`을 사용합니다. 현재 예제의 업로드·결과 처리 흐름은 컨트롤러와 템플릿에서 확인할 수 있습니다.

## 화면

![image](https://user-images.githubusercontent.com/73861946/200781306-62dc24d4-2422-4c68-83c2-f5ad11005f8c.png)
