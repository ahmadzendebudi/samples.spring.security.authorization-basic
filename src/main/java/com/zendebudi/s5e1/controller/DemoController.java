package com.zendebudi.s5e1.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class DemoController {
  
  @GetMapping(value="demo")
  public String demo() {
      return "Hello Endpoint Authorizatin!";
  }

  @GetMapping(value="public")
  public String publicendpoint() {
      return "Hello Endpoint public!";
  }
  
}
