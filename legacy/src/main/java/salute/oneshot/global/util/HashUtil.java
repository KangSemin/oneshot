package salute.oneshot.global.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    public static String encodeSha256(String message) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] bytes = md.digest(message.getBytes());

        return byteToHex(bytes);
    }

    public static String byteToHex(byte[] bytes) {
        StringBuilder strBuilder = new StringBuilder();

        for (byte b : bytes) {
            strBuilder.append(String.format("%02x", b));
        }
        return strBuilder.toString();
    }
}
