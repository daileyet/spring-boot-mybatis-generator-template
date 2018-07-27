package com.openthinks.demo.springbootmybatisgeneratortemplate.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.User;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.service.UserService;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.JSONArray;
import com.openthinks.libs.utilities.json.JSONConverters;
import com.openthinks.libs.utilities.json.JSONObject;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/list/all")
    public Object listAll() {
		JSONObject root = JSON.object();
		JSONObject data = root.createEmbedJSONObj("data");
		JSONArray array = data.createEmbedArray("items");
        List<User> userList = userService.selectAll();
        userList.forEach(u->{
        	array.add(JSONConverters.perparedAndGet(u));
        });
        return root;
    }
	
}
