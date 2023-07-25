package com.bakujin.jump_to_spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Sbb {
  @GetMapping("/sbb")
  @ResponseBody
  public String hello2() {
    System.out.println("index");
    return "안녕하세요 sbb에 오신걸 환영합니다.";
  }
}
