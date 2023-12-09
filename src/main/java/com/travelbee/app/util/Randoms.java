package com.travelbee.app.util;

import java.time.LocalDate;
import java.util.Random;

public class Randoms {

    public static String randomCodeMail(){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i =0 ;i<6;i++){
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }





}
