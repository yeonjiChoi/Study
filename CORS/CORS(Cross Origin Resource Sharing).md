## CORS(Cross Origin Resource Sharing)

* CORS란 현재 IP가 아닌 다른 IP로 리소스를 요청하는 구조



### Origin

* 요청이 시작된 서버의 위치를 나타내는 문구

* 예) Naver 로그인 서버로 로그인 요청을 할 경우

  1. Client 서버(http://client:80)

  2. Naver 로그인 API 서버(http://login:3000)

  * 2개의 IP는 서로 다른 IP 번호를 갖고 있음

![cors1](C:\Users\yeonz\Desktop\Docker\Study\Image\cors1.png)

* 클라이언트 서버가 로그인 서버에게 HTTP 요청을 보낼 때 **Origin이 다르다(Cross Origin)**
* 만약 로그인 서버가 내부 통신을 통해 서버 내 데이터를 가져온다면 Same Origin이라 함(그럴 일이 드물긴함,,)



### Origin 구분 방법

1. 스키마
2. HOST
3. Port

![cors2](C:\Users\yeonz\Desktop\Docker\Study\Image\cors2.png)

* https://yeonz.tistory:80/post
* https://yeonz.tistory:80/post/id?page=10
* https://yeonz.tistory:80/main/post/commet
* 위 세개의 주소는 모두 동일한 Origin

