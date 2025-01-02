package com.axsosacademy.javaprojectcertificatemanager.initializers;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Method to hash a password
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Method to verify a password
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}

