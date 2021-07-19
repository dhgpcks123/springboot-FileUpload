#HTML Form 데이터 전송
##2가지 방식

application/x-www-form-urlencoded
multipart/form-data (학원에서 배운 방식)  
application/x-www-form-urlencoded (일반적 방식이라네)
<form action="/save" method="post">
    <input type="text" name="username" />
    <input type="text" name="age" />
    <button type="submit">submit</button>
</form>
이렇게 하면 기본적으로 웹 브라우저가 HTTP 메시지를 생성한다.

=============================================== 

POST /save HTTP/1.1 Host: localhost:8080 Content-Type: application/x-www-form-urlencoded

username=kim&age=20 (key-value 형식으로 만들어짐, &로 구분)

===============================================

encType없으면 기본적으로 이렇게 추가 함!

파일을 업로드 하려면 파일은 문자가 아니라 바이너리 데이터로 전송해야 한다. 문자를 전송하는 방식으로? 전송할 수 없다. 보통 폼 전송할 때 파일 하나만 전송하지 않음.

이름
나이
첨부파일 2, 3, 4 등등 문자와 바이너리를 동시에 보내줘야 함! 즉 문자와 바이너리 동시 전송이 필요 함!
HTTP는 multipart/form-data 제공

<form action="/save" method="post" enctype="multipart/form-data">
    <input type="text" name="username" />
    <input type="text" name="age" />
    <input type="file" name="file" />
    <button type="submit">submit</button>
</form>
=============================================== 

POST /save HTTP/1.1 Host: localhost:8080 Content-Type: multipart/form-data, boundary = ----XXX

----XXX

Content-Disposition: form-data; name="username" 

----XXX

Content-Disposition: form-data; name="age"

----XXX 

Content-Disposition: form-data; name="file";

filename="intro.png" Content-Type: image/png

129jdfoiwjf2eiomf2i23-e0kmr3rij10r... binary데이터 넘김

----XXX--(끝)

===============================================

다른 종류의 여러 방식의 타입을 폼의 내용과 함께 전송할 때 사용합니다.

이미지 말고 영상파일도 가능! 그래서 multipart!/form-data 는 각각의 항목을 구분해서 한번에 전송!

"Part" multipart/form-data는 application/x-www-form-urlencoded와 비교해서

매우 복잡하고 각각의 부분 Part 나뉘어져 있다. 그렇다면 이렇게 복잡한 HTTP 메서드를 서버에서 어떻게 사용할 수 있을까?

