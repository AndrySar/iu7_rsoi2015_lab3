package other;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by user on 20.12.15.
 */
public class workSpace {

    public static String generateToken()
    {
        Random rand = new Random();
        String code = null;

        try
        {
            code = hashString(String.valueOf(rand.nextLong()), "MD5");

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return code.substring(0, code.length()/2);
    }

    protected static String hashString(String message, String algorithm)
    {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

            return convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {

        }
        return null;
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }

}
