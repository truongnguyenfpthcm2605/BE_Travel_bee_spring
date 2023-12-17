package com.travelbee.app.model.Qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;


import javax.imageio.ImageIO;
import javax.management.MBeanAttributeInfo;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Service
public class QRcodeService {
    public String generationQRcode(String data){
        StringBuilder stringBuilder = new StringBuilder();
        if(!data.isEmpty()){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           try {
               QRCodeWriter qrCodeWriter = new QRCodeWriter();
               BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE,300 , 300);
               BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
               ImageIO.write(bufferedImage,"png",byteArrayOutputStream);
               stringBuilder.append("data:image/png;base64,");
               stringBuilder.append(new String(Base64.getEncoder().encode(byteArrayOutputStream.toByteArray())));
           }catch (Exception e){
               return "Fail";
           }
        }
        return stringBuilder.toString();
    }





}
