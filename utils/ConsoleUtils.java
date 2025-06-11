import java.util.Scanner;

public class ConsoleUtils {
    private static Scanner scanner = new Scanner(System.in);

    public static int lerInt(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextInt()) {
            System.out.print("Valor inválido. " + msg);
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    public static double lerDouble(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextDouble()) {
            System.out.print("Valor inválido. " + msg);
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    public static String lerString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    public static boolean lerBoolean(String msg) {
        System.out.print(msg);
        return Boolean.parseBoolean(scanner.nextLine());
    }
} 