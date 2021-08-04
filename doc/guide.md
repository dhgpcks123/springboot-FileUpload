#사용 가이드

---

1. <form>태그에 해당 값을 넣어주어야 함.  enctype="multipart/form-data"

2. properties에 경로 설정하는 법  file.dir=/Users/dhgpcks/IdeaProjects/upload/
+ 로깅 debug 뿌려주기  logging.level.org.apache.coyote.http11=debug

3. 스프링에서 실제로 쓰려면 경로를 가져와야한다. ServletUploadControllerV2 확인      @Value("${file.dir}")  
   private String fileDir;

