package com.openthinks.demo.sprj.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  @RequestMapping("/")
  public String index() {
    return "index";
  }

  @RequestMapping("/public")
  public String publicPage() {
    return "index";
  }
}
