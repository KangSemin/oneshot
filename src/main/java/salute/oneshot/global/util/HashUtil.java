package salute.oneshot.global.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashUtil {

    public static String sha256(String message) {

        byte[] bytes = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            bytes = md.digest(message.getBytes());
        } catch (NoSuchAlgorithmException ex) {
            bytes = message.getBytes();
        }

        return byteToHex(bytes);
    }

    public static String byteToHex(byte[] bytes){
        StringBuilder strBuilder = new StringBuilder();

        for(byte b : bytes){
            strBuilder.append(String.format("%02x", b));
        }
        return strBuilder.toString();
    }
}
