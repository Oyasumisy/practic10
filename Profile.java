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
            throw new IllegalArgumentException("Досягнуто максимальну кількість користувачів! Неможливо додати нового користувача.");
        }
        if (!isValidUsername(username)) {
            throw new IllegalArgumentException("Некоректне ім'я користувача: має бути не менше 5 символів і не містити пробілів");
        }
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Некоректний пароль: має відповідати вимогам безпеки");
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
        if (!blockedPasswords.contains(password.toLowerCase())) {
            blockedPasswords.add(password.toLowerCase());
        }
    }

    private boolean isValidUsername(String username) {
        return username != null && username.length() >= 5 && !username.contains(" ");
    }

    private boolean isValidPassword(String password) {
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