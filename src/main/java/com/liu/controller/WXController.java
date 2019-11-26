package com.liu.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.util.WXUtil;

@Controller
public class WXController {
//                 @ResponseBody
//                 @GetMapping("/checkFromWXMsg")
	          public static String checkFromWXMsg(HttpServletRequest request) {
                	 String signature = request.getParameter("signature");
                	 String timestamp = request.getParameter("timestamp");
                	 String nonce = request.getParameter("nonce");
                	 String echostr = request.getParameter("echostr");
                 System.out.println("signature:"+signature);
                 System.out.println("nonce:"+nonce);
                     System.out.println("timestamp:"+timestamp);
                 System.out.println("echostr:"+echostr);
                 return  WXUtil.check(signature, timestamp, nonce, echostr);
                 }
	         
                 
}
