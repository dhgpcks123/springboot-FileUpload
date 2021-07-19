package com.springboot.upload_inf.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v2")
public class ServletUploadControllerV2 {


    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile(){
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest request) throws ServletException, IOException{
        log.info("request={}", request);
        String itemName = request.getParameter("itemName");
        log.info("itemName={}", itemName);

        Collection<Part> parts = request.getParts();
        //이 parts가 부분별로 나눠진 거 각각 받아볼 수 있는 것임!
        log.info("parts={}", parts);


        //내부 내용 읽기... 복잡하다 복잡해. 다 제공해주니까 그냥 데이터만 가져다 써.
        for (Part part : parts) {
            log.info("===== PART =====");
            log.info("name={}", part.getName());
            Collection<String> headerNames = part.getHeaderNames();
            for (String headerName : headerNames) {
                log.info("header {}: {}", headerName, part.getHeader(headerName));
            }
            //편의 메서즈
            //content-disposition; filename
            log.info("submittedFilename={}", part.getSubmittedFileName());
            log.info("size={}", part.getSize()); //part body size

            //data read
            InputStream inputStream = part.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("body ={} ", body);
            
            //파일 저장하기
            if(StringUtils.hasText(part.getSubmittedFileName())){
                String fullPath = fileDir + part.getSubmittedFileName();
                log.info("file 저장 fullPath = {}", fullPath);
                part.write(fullPath);
            }
            //파일 저장하고, db에 path랑 name 뿌려두면 되겠다!

            //서블릿이 제공하는 part는 편하긴 하지만 httpServletRequest를 사용해야 하고,
            //추가로 파일 부분만 구분하려면 여러가지 코드를 넣어야 한다. 스프링이 제공하는 걸 써보자!
        }

        return "upload-form";


    }
}
