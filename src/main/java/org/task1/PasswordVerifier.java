package org.task1;

import java.util.Scanner;



public class PasswordVerifier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите пароль: ");
        String password = scanner.nextLine();

        verifyPassword(password);



        }

        public static void verifyPassword(String password) {
        try {
            if (password.length() < 8) {
                throw new IncorrectPaswordExeption("длинна пароля менее 8 символов");
            }
            boolean hasDigit = false;
            boolean hasCApitalLetter = false;


            for (char c : password.toCharArray()) {
                if (Character.isDigit(c)) {
                    hasDigit = true;
                }
                if (Character.isUpperCase(c)) {
                    hasCApitalLetter = true;
                }
            }
                if (!hasDigit) {
                    throw new IncorrectPaswordExeption("Пароль должен содержать цифру");
                }
                if (!hasCApitalLetter) {
                    throw new IncorrectPaswordExeption("Пароль должен содержать заглавную букву");

                }
            System.out.println("Пароль соответвует правилам");
            } catch (IncorrectPaswordExeption e) {
            System.out.println("ошибка - "+ e.getMessage());
            }
        }


}
