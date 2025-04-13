package org.example;

import org.example.command.CommandManager;

import java.util.Scanner;

/**
 * Главный класс приложения
 */
public class Main {
    private static final String ENV_FILE_PATH = "STUDY_GROUP_FILE";
    private static volatile boolean running = true;

    public static void main(String[] args) {
        String filePath = System.getenv(ENV_FILE_PATH);
        if (filePath == null || filePath.trim().isEmpty()) {
            System.err.println("Переменная окружения " + ENV_FILE_PATH + " не установлена");
            System.exit(1);
        }

        CommandManager commandManager = new CommandManager(filePath);
        Scanner scanner = new Scanner(System.in);

        // Добавляем обработчик SIGINT (Ctrl+C)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nПолучен сигнал завершения. Сохранение коллекции...");
            commandManager.executeCommand("save");
            System.out.println("До свидания!");
            running = false;
        }));

        System.out.println("Добро пожаловать в Менеджер Учебных Групп!");
        System.out.println("Введите 'help' для просмотра доступных команд или 'exit' для выхода.");
        System.out.println("Нажмите Ctrl+C для сохранения и выхода.");

        while (running) {
            try {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Сохранение коллекции перед выходом...");
                    commandManager.executeCommand("save");
                    System.out.println("До свидания!");
                    break;
                }

                if (!input.isEmpty()) {
                    String result = commandManager.executeCommand(input);
                    System.out.println(result);
                }
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }
} 