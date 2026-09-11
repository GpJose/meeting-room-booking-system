package gp.jose.practice.reserveration.meetindAndBooking.utils;

import org.mindrot.jbcrypt.BCrypt;

public class CryptUtil {

    private static final String salt = BCrypt.gensalt(5);

    public static String hash(String password) {
        return BCrypt.hashpw(password, salt);
    }
    public static boolean checkHash(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
