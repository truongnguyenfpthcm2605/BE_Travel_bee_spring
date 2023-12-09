package com.travelbee.app.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static final String EMAIL_ADMIN = "truongnguenlqm@gmail.com";
    public static final String TITLE_SEND_FEEDBACK = "Phản Hồi Của Người Dùng";
    public static final String TITLE_REPLY_ADMIN = "Phản hồi của ADMIN";

    public static String providerId;
    public static String email_OAuth2;

    public static String dateFormat(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(date);
    }
    public static String dateFormat2(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public static Date stringFormat(String date){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(date);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public  static String decimalFormat(Double price){
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(price);
    }

}
