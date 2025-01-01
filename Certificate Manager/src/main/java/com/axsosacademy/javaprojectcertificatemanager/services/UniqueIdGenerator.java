package com.axsosacademy.javaprojectcertificatemanager.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UniqueIdGenerator {
    public static String generateUniqueId(Long certificateId, Date dateCreated) {
        // Format the year as YY from the Date object
        SimpleDateFormat yearFormatter = new SimpleDateFormat("yy");
        String year = yearFormatter.format(dateCreated);

        // Generate a 4-character random alphanumeric string
        String randomPart = generateRandomAlphanumeric();

        // Format the certificate ID to 4 characters, zero-padded
        String certIdPart = String.format("%04d", certificateId);

        // Concatenate to form the unique ID
        return year + randomPart + "-" + certIdPart;
    }

    private static String generateRandomAlphanumeric() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }
}
