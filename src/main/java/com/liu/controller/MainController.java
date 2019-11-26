package com.liu.controller;

import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.util.MessageType;
import com.liu.util.ReturnMessage;

import com.liu.util.XMLUtil;

@Controller
public class MainController {


    @ResponseBody
    @RequestMapping(value = "/main", produces = "application/json;charset=utf-8")
    public String main(HttpServletRequest request) {
        System.out.println("method=" + request.getMethod());
        String method = request.getMethod();

        try {
            if (method.equals("POST")) {

                //获得微信服务器发送的xml格式字符串数据（流）
                ServletInputStream inputStream = request.getInputStream();
                Map<String, String> map = XMLUtil.xmlRead(inputStream);

                String toUserName = map.get("ToUserName");//接收者
                String fromUserName = map.get("FromUserName");//发送者
                String msgType = map.get("MsgType");  //获得msgType数据
                System.out.println("msgtype:" + msgType);
                switch (msgType) {
                    case MessageType.EVENT://事件推送
                        String event = map.get("Event");//
                        if (MessageType.EVENT_SUBSCRIBE.equals(event)) {//关注事件
                            String msg = "感谢关注：\n 1、会回复一个文本消息\n"
                                    + "2、大哥,你好\n "
                                    + "3、会回复一个音乐消息\n";
                            return ReturnMessage.text(fromUserName, toUserName, msg);
                        } else if (MessageType.EVENT_UNSUBSCRIBE.equals(event)) {//取消关注
                            String msg = "感谢您的关注";
                            return ReturnMessage.text(fromUserName, toUserName, msg);
                        } else if (MessageType.EVENT_CLICK.equals(event))//点击菜单
                        {
                            String key = map.get("EventKey");//那种事件
                            System.out.println("key:" + key);
                            switch (key) {
                                case "music":
                                 return  ReturnMessage.text(fromUserName,toUserName,"一条音乐信息");
                            }
                        }
                        break;
                    case MessageType.TEXT://自动回复消息
                        String context = map.get("Content");
                        String msg = "";
                        if (context.equals("1"))
                            msg = "ny,where are you to from";
                        else if (context.equals("2"))
                            msg = "大哥,你好";
                        else if (context.equals("3"))
                            msg = "啦啦啦啦啦了";
                        else
                            msg = "哎呀,输入错了";
                        return ReturnMessage.text(fromUserName, toUserName, msg);
                }
            }
            if (method.equals("GET")) {
                return WXController.checkFromWXMsg(request);
            }

        } catch (Exception e) {
            System.out.println("主程序异常");
            e.printStackTrace();
        }
        return "";
    }

}
