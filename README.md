# SpringbootRestAPI
## 참고
 * [아빠프로그래머 SpringBoot2로 Rest api 만들기](https://daddyprogrammer.org/post/series/springboot2-make-rest-api/)

## 문제해결
1. H2 Console에서 데이테비이스 URL문제 

![image](https://user-images.githubusercontent.com/44068819/151172171-b705dcdf-c855-4332-bee3-3c77bc195bb2.png)
<br/> JDBC URL을 바꿔준다. **jdbc:h2:mem:testdb**
 
2. freemaker 템플릿 엔진 의존성 추가해도 /templetes내에서 .ftl파일을 인식하지 못하는 경우
 
![image](https://user-images.githubusercontent.com/44068819/151173627-42b21178-3c23-4577-8507-c2d81729eee7.png)
<br/> application.yml에서 따로 잡아줘야한다.
