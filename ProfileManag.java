import java.util.List;

public class ProfileManag {

    public static void addUser(Profile user) {
        Profile.getUsers().add(user);
    }

    public static void removeUser(String username) {
        List<Profile> users = Profile.getUsers();
        Profile toRemove = null;
        for (Profile user : users) {
            if (user.getUsername().equals(username)) {
                toRemove = user;
                break;
            }
        }
        if (toRemove != null) {
            users.remove(toRemove);
            System.out.println("Користувача видалено: " + username);
        } else {
            System.out.println("Користувача не знайдено");
        }
    }

    public static void clearAllUsers() {
        Profile.getUsers().clear();
        System.out.println("Усі користувачі видалені");
    }

    public static int getUserCount() {
        return Profile.getUsers().size();
    }

    public static void authenticateUser(String username, String password) {
        List<Profile> users = Profile.getUsers();
        for (Profile user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                System.out.println("Успішна аутентифікація для користувача: " + username);
                return;
            }
        }
        System.out.println("Помилка: неправильне ім'я користувача або пароль.");
    }
}
