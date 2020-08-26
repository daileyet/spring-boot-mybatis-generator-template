package com.openthinks.demo.sprj.web.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openthinks.demo.sprj.core.model.SystemRole;
import com.openthinks.demo.sprj.core.service.SystemUserService;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.JSONArray;
import com.openthinks.libs.utilities.json.JSONConverters;
import com.openthinks.libs.utilities.json.JSONObject;

@RestController
@RequestMapping("public/user")
public class UserAPI {

	@Autowired
	SystemUserService userService;
	
	@GetMapping("/list/all")
    public Object listAll() {
		JSONObject root = JSON.object();
		JSONObject data = root.createEmbedJSONObj("data");
		JSONArray array = data.createEmbedArray("items");
        List<SystemRole> userList = userService.listAllSystemRoles();
        userList.forEach(u->{
        	array.add(JSONConverters.perparedAndGet(u));
        });
        return root;
    }
	
}
