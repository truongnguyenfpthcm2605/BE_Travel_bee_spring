package com.travelbee.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static final String EMAIL_ADMIN = "truongnguenlqm@gmail.com";
    public static final String TITLE_SEND_FEEDBACK = "Phản Hồi Của Người Dùng";
    public static final String TITLE_REPLY_ADMIN = "Phản hồi của ADMIN";

    public static String dateFormat(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(date);
    }
}
