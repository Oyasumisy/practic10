import java.util.ArrayList;
import java.util.List;

public class Profile {
    private static final int MAX_USERS = 15;
    private static List<Profile> users = new ArrayList<>();
    private static List<String> blockedPasswords = new ArrayList<>(List.of("admin", "pass", "password", "qwerty", "ytrewq"));
    private String username;
    private String password;

    public Profile(String username, String password) {
        if (users.size() >= MAX_USERS) {
            System.out.println("Досягнуто максимальну кількість користувачів! Неможливо додати нового користувача.");
            return;
        }
        if (!isValidUsername(username)) {
            System.out.println("Некоректне ім'я користувача: має бути не менше 5 символів і не містити пробілів");
            return;
        }
        if (!isValidPassword(password)) {
            System.out.println("Некоректний пароль: має відповідати вимогам безпеки");
            return;
        }
        this.username = username;
        this.password = password;
        users.add(this);
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public static void addBlockedPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Помилка: заборонене слово не може бути порожнім.");
            return;
        }
        if (!blockedPasswords.contains(password.toLowerCase())) {
            blockedPasswords.add(password.toLowerCase());
            System.out.println("Слово додано до списку заборонених паролів.");
        } else {
            System.out.println("Цей пароль вже заборонений.");
        }
    }

    private boolean isValidUsername(String username) {
        return username != null && username.length() >= 5 && !username.contains(" ");
    }

    private boolean isValidPassword(String password) {
        if (!password.matches("[a-zA-Z0-9!@#$%^&*()-_+=]+")) {
            System.out.println("Помилка: пароль має містити лише латинські символи, цифри та спеціальні символи.");
            return false;
        }
        if (password == null || password.length() < 10 || password.contains(" ") || blockedPasswords.contains(password.toLowerCase())) {
            return false;
        }
        boolean hasSpecial = false, hasDigit = false;
        int digitCount = 0;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) digitCount++;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        hasDigit = digitCount >= 3;

        return hasSpecial && hasDigit;
    }
}
