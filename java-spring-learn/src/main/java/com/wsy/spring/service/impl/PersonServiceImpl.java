package com.wsy.spring.service.impl;

import com.wsy.spring.annotation.HiTrace;
import com.wsy.spring.service.PersonService;
import org.springframework.stereotype.Service;

@Service(value = "PersonServiceImpl")
//@Component(value = "personService") //value==id,value默认值为类名首字母小写
public class PersonServiceImpl implements PersonService {
    @HiTrace
    public void setName(){
        System.out.println("what fuck");
    }
}
