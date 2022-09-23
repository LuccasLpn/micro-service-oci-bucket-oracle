package br.com.microserviceocibucket.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Decode {

    public InputStream decodeString(String image){
        BufferedImage bufferedImage;
        InputStream inputStream = null;
        try {
            byte[] decode = Base64.getDecoder().decode(image);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decode);
            bufferedImage = ImageIO.read(byteArrayInputStream);
            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", bas);
            inputStream = new ByteArrayInputStream(bas.toByteArray());
            byteArrayInputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return inputStream;
    }
}
