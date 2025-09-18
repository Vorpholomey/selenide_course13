package utils;

import java.util.*;

public class Generators {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Integer LOGIN_LENGTH = 6;
    private static final Integer PASSWORD_LENGTH = 8;

    /**
     * Генерирует логин в формате 'любая строка'@mail.ru
     *
     * @return возвращает логин
     */
    public static String generatorLogin() {
        StringBuilder sb = generatorString(LOGIN_LENGTH).append("@mail.ru");
        return sb.toString();
    }

    /**
     * Метод генерирует пароль как любую строку
     *
     * @return возвращает пароль
     */
    public static String generatorPassword() {
        return generatorString(PASSWORD_LENGTH).toString();
    }

    /**
     * Метод генерирует случайную строку с указанным размером через length
     * с применением символов из CHARACTERS
     *
     * @param length - длина строки
     * @return - возвращает сгенерированную строку
     */
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

