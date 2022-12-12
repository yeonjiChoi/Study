[TOC]



### 📌 정적 페이지와 동적 페이지  

*Web Server와 WAS를 배우기에 앞서 정적 페이지와 동적 페이지에 대해 알아보자*

![static_vs_dynamic](./CS_Images/static_vs_dynamic.png)

##### 정적 페이지

* Web Server는 파일 경로 이름을 받아 경로와 일치하는 파일 컨텐츠를 반환
* **항상 동일한 페이지**를 반환
* 예) 이미지, HTML, CSS, Javascript 파일 등 컴퓨터에 저장되어 있는 파일로, 응답가능한 컨텐츠

##### 동적 페이지

* 인자의 내용에 맞게 동적인 컨텐츠를 반환
* **웹 서버**에 의해서 실행되는 프로그램을 통해서 만들어진 결과물



---

### 💡Web Server

> 웹 브라우저 클라이언트로부터 **HTTP 요청을 받아들이고** HTML 문서와 같은 **웹 페이지를 반환**하는 컴퓨터 프로그램

* 웹 서버란 클라이언트(사용자)가 웹 브라우저에서 어떠한 페이지 요청을 하면 웹 서버에서 그 요청을 받아 <span style="background-color : #fff5b1">정적 컨텐츠를 제공하는 서버</span>

* 기능 1. 정적인 컨텐츠 제공 및 WAS를 거치지 않고 바로 자원을 제공
* 기능 2. 동적인 컨텐츠 제공을 위한 요청 전달로 클라이언트의 요청을 WAS에 보내고, WAS가 처리한 결과를 클라이언트에게 응답

* 대표적인 웹 서버 : Apache Server, Nginx, IIS(Windows 전용 웹 서버) 



### 💡 WAS(Web Application Server)

> 인터넷 상에서 HTTP 프로토콜을 통해 사용자 컴퓨터나 장치에 애플리케이션을 수행해주는 **미들웨어**로서, 주로 **동적 서버 컨텐츠**를 수행하는 것으로 웹 서버와 구별이 되며, 주로 데이터베이스 서버와 같이 수행

* WAS는 <span style="background-color : #fff5b1">**Web Server + Web Container**</span>가 합쳐진 형태로서, 웹 서버 단독으로 처리할 수 없는 <span style="background-color : #fff5b1">데이터베이스 조회나 다양한 로직 처리가 필요한 동적 컨텐츠를 제공</span>
* WAS는 **JSP, Servlet 구동 환경**을 제공해주기 때문에 <u>웹 컨테이너</u> 혹은 <u>서블릿 컨테이너</u>라고도 불림
  * *컨테이너란 JSP, Servlet을 실행시킬 수 있는 소프트웨어로 WAS는 JSP, Servlet 구동 환경을 제공* *(Servlet : WAS 위에서 돌아가는 자바 프로그램)*
  * *웹 컨테이너 : 웹 서버가 보낸 JSP, PHP 등의 파일을 수행한 결과를 다시 웹 서버로 보내주는 역할을 수행*
* 기능 1. 프로그램 실행 환경과 DB 접속 기능 제공
* 기능 2. 여러 개의 트랜잭션(논리적인 작업 단위) 관리 기능
* 기능 3. 업무를 처리하는 비즈니스 로직 수행

* 대표적인 WAS : Tomcat, JBoss, Jeus, Web Sphere 



![webserver_was](./CS_Images/webserver_was.png)



---

#### ❓ Web Server가 필요한 이유

> 클라이언트(웹 브라우저)에게 이미지 파일(정적 컨텐츠)을 보내는 과정을 생각해본다면
>
> 1. 이미지 파일같은 정적 파일들은 웹 문서(HTML 문서)가 클라이언트로 보내질 때 함께 가는 것이 아님
> 2. 클라이언트는 `HTML 문서`를 먼저 받고, 그에 맞게 필요한 `이미지 파일`을 다시 서버로 요청
> 3. 웹 서버를 통해 정적인 파일들을 WAS까지 가지 않고 앞단에서 빠르게 전송 가능

<span style="background-color : #fff5b1">웹 서버는 정적 컨텐츠만 처리하도록 기능을 분배하여 서버의 부담을 줄일 수 있음</span>



#### ❓ WAS가 필요한 이유

> 웹 페이지는 정적/동적 컨텐츠가 모두 존재
>
> 1. 사용자의 요청에 맞게 적절한 동적 컨텐츠를 만들어서 제공해야 함
> 2. 이때, 웹 서버만을 이용한다면 사용자가 원하는 요청에 대한 결과값을 모두 미리 만들어 놓고 서비스를 해야 함
> 3. 2번을 수행하기 에는 자원이 절대적으로 부족

<span style="background-color : #fff5b1">WAS를 통해 요청에 맞는 데이터를 DB에서 가져와서 비즈니스 로직에 맞게 그때 그때 결과를 만들어서 제공함으로써 자원을 효율적으로 사용 가능</span>



#### ❓ 그렇다면 WAS가 Web Server의 기능도 모두 수행하면 되지 않을까?

1. 기능을 분리하여 서버 부하 방지

   * WAS는 바쁘다

     <span style="color:gray">WAS는 DB 조회나 로직 처리를 수행하기 바쁘기 때문에 단순한 정적 컨텐츠는 웹 서버에서 빠르게 클라이언트에게 제공하는 것이 좋음</span>

   * WAS는 동적 컨텐츠 제공을 위해 존재

     <span style="color:gray">정적 컨텐츠 요청까지 WAS가 처리한다면 정적 데이터 처리로 인해 부하가 커지게 되고, 동적 컨텐츠의 처리가 지연됨에 따라 수행 속도가 느려짐 -> 페이지 노출 시간 증가</span> 

2. 물리적으로 분리하여 보안 강화

3. 여러 대의 WAS 연결 가능

   * Load Balancing을 위해서 웹 서버 사용

   * fail over, fail back 처리에 유리

   * 대용량 웹 어플리케이션(여러 개의 서버)의 경우 웹 서버와 WAS를 분리하여 무중단 운영을 위한 장애 극복에 쉽게 대응

     예를 들어, WAS에서 오류 발생 시 웹 서버에서 WAS를 이용하지 못하도록 한 후 WAS를 재시작함으로써 사용자는 오류를 느끼지 못하고 이용 가능

4. 여러 웹 어플리케이션 서비스 가능

**<span style="background-color : #fff5b1">자원 이용의 효율성 및 장애 극복, 배포 및 유지보수의 편의성을 위해 웹 서버와 WAS를 분리</span>**



---

### 🛠 Web Service Architecture

> 웹 서비스는 다양한 구조를 가질 수 있으며 아래 사진은 세 번째 구조에 해당
>
> * Client -> Web Server -> DB
> * Client -> WAS -> DB
> * **Client -> Web Server -> WAS -> DB**

![web_service_arch](./CS_Images/web_service_arch.png)

**웹 서버와 WAS를 분리헀을 때의 동작 과정**

1. 웹 서버는 웹 브라우저 클라이언트로부터 HTTP 요청을 받음

2. 웹 서버는 클라이언트의 요청을 WAS로 전달

3. WAS는 관련된 Servlet을 메모리에 올림

4. WAS는 **web.xml**을 참조하여 해당 Servlet에 대한 Thread를 생성(Thread Pool 이용)

5. **HttpServletRequest**와 **HttpServletResponse** 객체를 생성하여 Servlet에 전달

   5-1. Thread는 Servlet의 service() 메서드 호출

   5-2. service() 메서드는 요청에 맞게 doGet() 또는 doPost() 메서드를 호출

   5-3. protected doGet(HttpServletRequest request, HttpServletResponse response)

6. doGet() 또는 doPost() 메서드는 인자에 맞게 생성된 적절한 동적 페이지를 Response 객체에 담아 WAS에 전달
7. WAS는 Response 객체를 HttpResponse 형태로 바꾸어 웹 서버에 전달
8. 생성된 Thread를 종료하고, HttpServletRequest와 HttpServletResponse 객체를 제거

