# SpringbootRestAPI 공부
## 참고
 * [아빠프로그래머 SpringBoot2로 Rest api 만들기](https://daddyprogrammer.org/post/series/springboot2-make-rest-api/)

## 문제해결(공부하면서 계속 추가할 예정)
### 1.  H2 Console에서 데이터베이스 URL문제 

![image](https://user-images.githubusercontent.com/44068819/151172171-b705dcdf-c855-4332-bee3-3c77bc195bb2.png)
<br/> JDBC URL을 바꿔준다. **jdbc:h2:mem:testdb**
 
### 2.  freemaker 템플릿 엔진 의존성 추가해도 /templetes내에서 .ftl파일을 인식하지 못하는 경우
 
![image](https://user-images.githubusercontent.com/44068819/151173627-42b21178-3c23-4577-8507-c2d81729eee7.png)
<br/> application.yml에서 따로 잡아줘야한다.

### 3.Swagger2 연동 시 문제해결
<br/>

#### - 도움을 받은 사이트
* [Swagger2 연동(버전 2.9.2)](https://inseok9068.github.io/springboot/springboot-swagger/)
* [오류해결 (application.yml에 추가)](https://www.inflearn.com/questions/230160)

<br/>

## build.gradle(추가될 수 있음)

```gradle
plugins {
    id 'org.springframework.boot' version '2.6.3'
    id 'io.spring.dependency-management' version '1.0.11.RELEASE'
    id 'java'
}
group = 'com.rest'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

ext {
    swagger2Version = '2.9.2'
    oldSwagger2Version = '1.5.21'
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-freemarker'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly 'com.h2database:h2'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'

    /*--------------------------[ Swagger 2 ]--------------------------------*/
    // Swagger2 내부 라이브러리 이슈로 인한 Annotatios과 Models 과거 버전으로 사용
    //implementation 'io.springfox:springfox-swagger2:2.9.2'
    implementation 'io.springfox:springfox-swagger-ui:2.9.2'

    implementation("io.springfox:springfox-swagger2:${swagger2Version}") {
        exclude group: 'io.swagger', module: 'swagger-annotations'
        exclude group: 'io.swagger', module: 'swagger-models'
    }

    implementation "io.swagger:swagger-annotations:${oldSwagger2Version}"
    implementation "io.swagger:swagger-models:${oldSwagger2Version}"
    /*-----------------------------------------------------------------------*/
}

test {
    useJUnitPlatform()
}


```

