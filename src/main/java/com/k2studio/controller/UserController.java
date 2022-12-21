package com.k2studio.controller;

import java.util.HashMap;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.k2studio.model.User;
import com.k2studio.validaterequest.PostUserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@RestController
@Validated
public class UserController {

    @GetMapping(value = "/user")
    public String getUser() {
        return "Hello World";
    }

    @GetMapping(value = "/user1")
    public String getUser1(@RequestParam(required = false, defaultValue = "World") String name) {
        return "Hello "+name;
    }

    @GetMapping(value = "/user2", produces = "application/json")
    public String getUser2() {
        return "{\"name\":\"World\"}";
    }

    @GetMapping(value = "/user3", produces = "application/json")
    public String getUser3(@RequestParam(required = false, defaultValue = "World") String name) {
        return String.format("{\"name\":\"%s\"}", name);
    }

    @GetMapping(value = "/user4", produces = "application/json")
    public ResponseEntity<String> getUser4(@RequestParam(required = false, defaultValue = "World") String name) {
        String content = String.format("{\"name\":\"%s\"}", name);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Custom-Header", "foo");
        return new ResponseEntity<String>(content, headers, HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping(value = "/user5", produces = "application/json")
    public HashMap<String, String> getUser5(@RequestParam(required = false, defaultValue = "World") String name) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("name", name);
        return data;
    }

    @GetMapping(value = "/user6", produces = "application/json")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public HashMap<String, String> getUser6(@RequestParam(required = false, defaultValue = "World") String name) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("name", name);
        return data;
    }

    @GetMapping(value = "/user7/{id:[\\d]+}", produces = "application/json")
    public HashMap<String, String> getUser7(
        @PathVariable String id,
        @RequestParam(required = false, defaultValue = "World") String name
    ) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("id", id);
        data.put("name", name);
        return data;
    }

    //

    @PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
    public User postUser(@RequestBody User user) {
        return user;
    }

    @PostMapping(value = "/user1", consumes = "multipart/form-data", produces = "application/json")
    public User postUser1(@ModelAttribute User user) {
        return user;
    }

    @PostMapping(value = "/user2", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> postUser2(@RequestBody HashMap<String, String> data) {
        return data;
    }

    @RequestMapping(value = "/user3", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
    public HashMap<String, String> postUser3(@RequestBody HashMap<String, String> data) {
        return data;
    }

    @PostMapping(value = "/user4", consumes = "application/json")
    public String postUser4(@RequestBody HashMap<String, String> data) {
        String name = data.get("name");
        String name2 = data.get("name2");
        return String.format("%d. name = %s, name2 = %s", 1, name, name2);
    }

    @PostMapping(value = "/user5", consumes = "multipart/form-data", produces = "application/json")
    public HashMap<String, String> postUser5(@RequestParam String name, @RequestParam String name2) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("name", name);
        data.put("name2", name2);
        return data;
    }

    @PostMapping(value = "/user6", consumes = "multipart/form-data", produces = "application/json")
    public User postUser6(@RequestParam String name, @RequestParam String name2) {
        User user = new User();
        user.setName(name);
        user.setName2(name2);
        return user;
    }

    @PostMapping(value = "/user7", consumes = "multipart/form-data", produces = "application/json")
    public HashMap<String, String> postUser7(@RequestParam HashMap<String, String> datas) {
        return datas;
    }

    @PostMapping(value = "/user8", consumes = "multipart/form-data", produces = "application/json")
    public HashMap<String, String> postUser8(
        @RequestHeader(required = false, value = "X-Custom-Header") String header,
        @RequestParam String name
    ) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("id", "1");
        data.put("header", header);
        data.put("name", name);
        return data;
    }

    @PostMapping(value = "/user9", consumes = "multipart/form-data", produces = "application/json")
    public HashMap<String, String> postUser9(
        @RequestParam @Size(min = 2, max = 8, message = "name1.length must be between {min} and {max}") String name1,
        @RequestParam @Size(min = 2, max = 8, message = "name2.length must be between {min} and {max}") String name2
    ) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("name1", name1);
        data.put("name2", name2);
        return data;
    }

    @PostMapping(value = "/user10", consumes = "application/json", produces = "application/json")
    public PostUserRequest postUser10(@Valid @RequestBody PostUserRequest request, BindingResult result) {
        Logger logger = Logger.getAnonymousLogger();
        logger.info("Log Hello World");

        return request;
    }

}
