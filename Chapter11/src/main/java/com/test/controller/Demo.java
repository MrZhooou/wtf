package com.test.controller;


import com.test.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.ReturnNode;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@Api(value = "v1",description = "这是我第一个版本的demo")
@RequestMapping("v1")
public class Demo {
    //首先获取一个执行sql语句的对象

    private final SqlSessionTemplate template;

    @Autowired
    public Demo(SqlSessionTemplate template) {
        this.template = template;
    }

    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value = "可以获取到用户数",httpMethod = "GET")
    public int getUserCount(){
        return template.selectOne("getUserCount");
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public int addUser(@RequestBody User user){

        return template.insert("addUser",user);
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public int updateUser(@RequestBody User user){
        return template.update("updateUser",user);
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public int deleteUser(@RequestParam int id){
        return template.delete("deleteUser",id);
    }
}
