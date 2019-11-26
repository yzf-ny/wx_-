package com.liu.service;

import com.liu.bean.Userinfo;
import com.liu.mapper.UserifnoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserifnoMapper mapper;
    public UserService(UserifnoMapper mappers) {
//        System.out.println("this is UserService.....");
        this.mapper=mappers;
//        System.out.println("mappers:"+mappers);
//        int q = mapper.isLogin("oFSsfwa5Lyl0C1UHA1HStFfdTdlQ");
//        System.out.println("q="+q);
    }





    public List<Userinfo> show_list()
    {
        return mapper.show_list();
    }

    public int islogin(String appid)
    {
        int i= mapper.isLogin(appid);
        System.out.println("service.i="+i);
        return i;
    }


    public int add(String name,String pwd,String appid)
    {
       Userinfo user=new Userinfo();
       user.setName(name);
       user.setPwd(pwd);
       user.setAppid(appid);
       return mapper.add(user);
    }

}
