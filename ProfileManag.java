import java.util.ArrayList;
import java.util.List;

public class ProfileManag {
    private static List<Profile> users = new ArrayList<>();

    public static void removeUser(String username) {
        for (Profile user : users) {
            if (user.getUsername().equals(username)) {
                users.remove(user);
                System.out.println("Користувача видалено: " + username);
                return;
            }
        }
        System.out.println("Користувача не знайдено!");
    }

    public static void showUsers() {
        if (users.isEmpty()) {
            System.out.println("Немає зареєстрованих користувачів.");
        } else {
            System.out.println("Список користувачів:");
            for (Profile user : users) {
                System.out.println("- " + user.getUsername());
            }
        }
    }

    public static Profile findUser(String username) {
        for (Profile user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        System.out.println("Користувача не знайдено!");
        return null;
    }

    public static void clearAllUsers() {
        users.clear();
        System.out.println("Усі користувачі видалені!");
    }

    public static int getUserCount() {
        return users.size();
    }
}
