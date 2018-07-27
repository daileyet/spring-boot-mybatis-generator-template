package com.openthinks.demo.springbootmybatisgeneratortemplate.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/list/all")
    public Object listAll() {
        return userService.selectAll();
    }
	
}
