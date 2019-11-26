package com.liu.controller;

import com.liu.bean.Userinfo;
import com.liu.service.UserService;
import com.liu.util.MemuAppid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserinfoCon {
    @Autowired
    private UserService service;

    @ResponseBody
    @RequestMapping("/show")
    public List<Userinfo> show() {
        return service.show_list();
    }

//    @RequestMapping("/getcode")
//    public String getCode()
//    {
//     return "redirect:/islogin";
//    }

   //理想是存在文件中，2个小时后会失效
   private String appids="";
    @RequestMapping("/islogin")
    public String islogin(String code, String state) {
        //System.out.println("code:"+code+" ,state:"+state);

        String appid = MemuAppid.getApid(code);
        appids=appid;
       // System.out.println("appid=" + appid);
        int i = service.islogin(appid);
        //System.out.println("i=" + i);

       if(i==1)
          return "redirect:index.jsp";
       else
           return "redirect:login.jsp";

    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(String name, String pwd) {
        String appid = appids;
        int a=service.add(name, pwd, appid) ;
        if(a>0)
            return "redirect:index.jsp";
        else
            return "redirect:login.jsp";
    }
}
