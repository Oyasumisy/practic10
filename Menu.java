import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Додати користувача");
            System.out.println("2. Переглянути користувачів");
            System.out.println("3. Умови використання");
            System.out.println("4. Додати заборонене слово для пароля");
            System.out.println("5. Видалити користувача");
            System.out.println("6. Очистити всіх користувачів");
            System.out.println("7. Увійти в систему");
            System.out.println("8. Вийти");
            System.out.print("Ваш вибір: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Throwable e) {
                System.out.println("Помилка: введіть число від 1 до 8.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.print("Введіть ім'я користувача (або 'назад' для виходу): ");
                        String username = scanner.nextLine();
                        if (username.equalsIgnoreCase("назад")) break;
                        System.out.print("Введіть пароль: ");
                        String password = scanner.nextLine();
                        try {
                            Profile user = new Profile(username, password);
                            System.out.println("Користувача створено: " + user.getUsername());
                            break;
                        } catch (Throwable e) {
                            System.out.println("Помилка: " + e.getMessage());
                        }
                    }
                    break;
                case 2:
                    Profile.showUsers();
                    break;
                case 3:
                    showTerms();
                    break;
                case 4:
                    while (true) {
                        System.out.print("Введіть слово, яке потрібно заборонити у паролі (або 'назад' для виходу): ");
                        String forbiddenWord = scanner.nextLine();
                        if (forbiddenWord.equalsIgnoreCase("назад")) break;
                        if (!Profile.getBlockedPasswords().contains(forbiddenWord.toLowerCase())) {
                            Profile.addBlockedPassword(forbiddenWord);
                        } else {
                            System.out.println("Цей пароль вже заборонений.");
                        }
                    }
                    break;
                case 5:
                    while (true) {
                        System.out.print("Введіть ім'я користувача для видалення (або 'назад' для виходу): ");
                        String userToDelete = scanner.nextLine();
                        if (userToDelete.equalsIgnoreCase("назад")) break;
                        ProfileManag.removeUser(userToDelete);
                    }
                    break;
                case 6:
                    ProfileManag.clearAllUsers();
                    break;
                case 7:
                    System.out.print("Введіть ім'я користувача: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Введіть пароль: ");
                    String loginPassword = scanner.nextLine();
                    ProfileManag.authenticateUser(loginUsername, loginPassword);
                    break;
                case 8:
                    System.out.println("Вихід...");
                    return;
                default:
                    System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
    }

    private static void showTerms() {
        System.out.println("Умови використання:");
        System.out.println("- Ім'я користувача має бути не менше 5 символів і не містити пробілів.");
        System.out.println("- Пароль має містити не менше 10 символів, хоча б 1 спеціальний символ і мінімум 3 цифри.");
        System.out.println("- Заборонені паролі: " + String.join(", ", Profile.getBlockedPasswords()));
        System.out.println("- Максимальна кількість користувачів: 15.");
    }
}