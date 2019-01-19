package restaurant.misc;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

    public static String hash(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] passBytes = password.getBytes();
            byte[] passHash = sha256.digest(passBytes);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte hash : passHash) {
                stringBuilder.append(Integer.toString((hash & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
