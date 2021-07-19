//멀티파트 사용 옵션
```yml
spring.servlet.multipart.max-file-size:1MB
spring.servlet.multipart.max-request.size=10MB
```
- 큰 파일을 무제한 업로드 막아야지!
- 사이즈 넘으면 SizeLimitExceededException발생
- max-file-size : 파일 하나의 사이즈
- max-request-size : 전체 합

```yml
spring.servlet.multipart.enabled=false
```
기본이 켜져 있는데 끄면?  
요청이 RequestFacade로 넘김(톰캣 기본 구현체임)
- itemName = null
- parts =[]  
멀티파트와 관련된 처리 끄는 것과 같음
  
