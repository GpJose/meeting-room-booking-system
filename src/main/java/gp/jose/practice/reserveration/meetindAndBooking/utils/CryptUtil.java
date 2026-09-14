package gp.jose.practice.reserveration.meetindAndBooking.utils;

import org.mindrot.jbcrypt.BCrypt;

public class CryptUtil {

    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(5));
    }
    public static boolean checkHash(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
