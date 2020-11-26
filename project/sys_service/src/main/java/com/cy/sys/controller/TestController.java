package com.cy.sys.controller;

import com.cy.common.utils.apiUtil.ApiResp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping("test1")
    public ApiResp test1(){
        return ApiResp.success("test1");
    }

}
