package com.lichen.lichenblog.admin.util;


import com.lichen.lichenblog.admin.enums.StatusCodeEnum;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author lichen
 * @version 1.0.0
 * @className DabaituResult.java
 * @Description TODO
 * @createTime 2022年09月13日 15:59:00
 */
public class DabaituResult extends HashMap{

    public DabaituResult Message (String message){
        put("message",message);
        return this;
    }
    public DabaituResult Data (Object data){
        put("data",data);
        return this;
    }
    public DabaituResult Code (HttpStatus status){
        put("code",status.value());
        return this;
    }
    public DabaituResult Code (StatusCodeEnum statusCodeEnum){
        put("code",statusCodeEnum.getCode());
        put("desc",statusCodeEnum.getDesc());
        return this;
    }

    public DabaituResult ok () {
        Code(HttpStatus.OK);
        put("flag",true);
          return this;
    }
    public DabaituResult ok(Object data){
        put("data",data);
        Code(HttpStatus.OK);
        put("flag",true);
        return this;
    }
    public DabaituResult isFail(){
        Code(HttpStatus.INTERNAL_SERVER_ERROR);
          return this;
    }

    public DabaituResult put(String key,Object value){
        super.put(key,value);
        return this;
    }

}
