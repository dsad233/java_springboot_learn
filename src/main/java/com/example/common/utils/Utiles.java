package com.example.common.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

//@AllArgsConstructor
//@NoArgsConstructor
//@RequiredArgsConstructor
public class Utiles {
    public static String constRegEx(String prop){
        if (!prop.equals("loginId") && !prop.equals("email") && !prop.equals("password")) {
            return "";
        }

        if(prop.equals("email")) {
            return "/^(?![\\w\\.@]*\\.\\.)(?![\\w\\.@]*\\.@)(?![\\w\\.]*@\\.)\\w+[\\w\\.]*@[\\w\\.]+\\.\\w{2,}$/";
        }

        return "";
    }
}
