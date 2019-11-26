package com.liu.mapper;

import com.liu.bean.Userinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;

import java.util.List;


public interface UserifnoMapper {

    List<Userinfo> show_list();

    int isLogin(@Param("appid") String  appid);

    int add(Userinfo user);

}
