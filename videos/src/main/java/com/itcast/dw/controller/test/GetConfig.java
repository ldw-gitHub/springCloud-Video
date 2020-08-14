package com.itcast.dw.controller.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author: liudawei
 * @date: 2020/8/13 14:14
 */
@RefreshScope
@RestController
public class GetConfig {

  @Value("${test.name}")
  String name;
  @Value("${test.age}")
  String age;

  @GetMapping(value = "/hi")
  public String hi(){
    return "my name is " + name + " ,age is " + age;
  }

}
