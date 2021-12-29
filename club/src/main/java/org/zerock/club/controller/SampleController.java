package org.zerock.club.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/all")
    public String exAll(){
        log.info("exAll..........");
        return "/sample/all";
    }

    @GetMapping("/member")
    public String exMember(){
        log.info("exMember..");
        return "/sample/member";
    }

    @GetMapping("/admin")
    public String exAdmin(){
        log.info("exAdmin..");
        return "/sample/admin";
    }
}
