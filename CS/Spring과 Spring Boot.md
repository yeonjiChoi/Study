## Spring과 Spring Boot의 차이

### Spring(=Spring Framework)

* 자바 기반의 웹 어플리케이션을 만들기 위한 프레임워크
* 동적인 웹 사이트를 개발하기 위한 여러가지 서비스를 제공
* 전자정부 표준 프레임워크의 기반 기술
* 특징
  * **제어의 역전(IOC, Inversion of Control)**
    * 객체의 생성부터 소멸까지의 생명주기를 개발자가 아닌 컨테이너가 담당하는 것
    * 스프링 컨테이너는 기본적으로 싱글톤 패턴의 객체 생성을 지향(빈 객체는 딱 하나, 재사용)
  * **의존성 주입(DI, Dependency Injection)**
    * 컨테이너에서 관리할 객체를 지정해주고, 코드 내에서는 컨테이너에서 객체를 받아 사용하는 방식
    * 빈(Bean)을 정의할 떄 객체 간 의존 관계를 명시해 코드에서 사용 시 자동으로 주입받음
  * 관점 지향 프로그래밍(AOP, Aspect Oriented Programming)
* 위 세 가지 특징을 통해 <u>결합도를 낮춰</u> 유연한 개발이 가능

### Spring Boot

* 스프링 프레임워크를 사용하기 위한 설정을 자동화하여 사용자가 편리하게 스프링을 활용 가능하게 함
* 기존 스프링에서는 버전을 명시하고 버전에 맞는 설정이 필요했지만, 스프링 부트는 버전 관리가 스프링 부트에 의해서 관리됨
* Spring Boot Starter
  * 종속된 모든 라이브러리를 알맞게 가져오기 때문에 종속성이나 호환에 대해 신경을 쓰지 않아도 됨
  * spring-boot-starter-web

[참고 링크1]: https://codevang.tistory.com/241



### Spring과 Spring Boot의 차이점

* Spring Boot에는 내장 서버인 Embed Tomcat이 포함되어 있기 떄문에 따로 서버를 설치하거나 매번 버전을 관리하지 않아도 됨
* Spring Boot Starter를 통해 대부분의 Dependency를 관리하기 때문에 버전 호환에 대한 어려움이 줄음
* Spring Boot는 XML 설정을 하지 않아도 됨
* Spring Boot의 jar 파일을 이용해 손쉽게 배포가 가능하다