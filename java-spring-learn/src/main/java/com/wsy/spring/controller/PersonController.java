package com.wsy.spring.controller;

import com.wsy.spring.service.PersonService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class PersonController {
    //@Autowired
    @Resource
    private PersonService personService;
    public void test(){
        personService.setName();
    }
}
