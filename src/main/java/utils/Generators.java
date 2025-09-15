package utils;

import java.util.Random;

public class Generators {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Integer LOGIN_LENGTH = 6;
    private static final Integer PASSWORD_LENGTH = 8;

    public static String generatorLogin() {
        StringBuilder sb = generatorString(LOGIN_LENGTH).append("@mail.ru");
        return sb.toString();
    }

    public static String generatorPassword() {
        return generatorString(PASSWORD_LENGTH).toString();
    }

    private static StringBuilder generatorString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb;
    }
}

