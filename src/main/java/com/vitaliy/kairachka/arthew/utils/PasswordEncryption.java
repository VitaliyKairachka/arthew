package com.vitaliy.kairachka.arthew.utils;

import org.mindrot.jbcrypt.BCrypt;


/**
 * @author Vitaliy Kayrachka
 */
public class PasswordEncryption {
    public static String hashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String candidate, String password) {
        return BCrypt.checkpw(candidate, password);
    }
}
