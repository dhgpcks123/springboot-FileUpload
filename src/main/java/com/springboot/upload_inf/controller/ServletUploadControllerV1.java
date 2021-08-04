package com.springboot.upload_inf.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v1")
public class ServletUploadControllerV1 {

    @GetMapping("/upload")
    public String newFile(){
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest request) throws ServletException, IOException{
        log.info("request={}", request);
        String itemName = request.getParameter("itemName");
        /*
            request는 http요청으로 넘어온 데이터 담겨있다. multipart/form-data로 넘겨서
            파일이 담길 수 있는 다른 형식의 포맷으로 데이터가 넘어왔다.
         */
        log.info("itemName={}", itemName);

        Collection<Part> parts = request.getParts();
        //이 parts가 부분별로 나눠진 거 각각 받아볼 수 있는 것임!
        log.info("parts={}", parts);
        /*
            여기에 parts가 담겨있겠죠?
         */

        return "upload-form";


    }
}
