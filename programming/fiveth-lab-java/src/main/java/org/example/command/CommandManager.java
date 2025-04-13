package org.example.command;

import org.example.model.StudyGroup;
import org.example.util.ConsoleManager;
import org.example.util.FileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final Vector<StudyGroup> collection;
    private final ConsoleManager consoleManager;
    private final FileManager fileManager;
    private final LocalDateTime initializationTime;
    private final Set<String> executingScripts = new HashSet<>();

    public CommandManager(String fileName) {
        this.collection = new Vector<>();
        this.consoleManager = new ConsoleManager();
        this.fileManager = new FileManager(fileName);
        this.initializationTime = LocalDateTime.now();
        initializeCommands();
        loadCollection();
    }

    private void initializeCommands() {
        commands.put("help", new Command() {
            @Override
            public String execute(String[] args) {
                StringBuilder help = new StringBuilder("Доступные команды:\n");
                commands.values().stream()
                        .sorted(Comparator.comparing(Command::getName))
                        .forEach(cmd -> help.append(String.format("%-25s %s%n", cmd.getName(), cmd.getDescription())));
                return help.toString();
            }

            @Override
            public String getName() {
                return "help";
            }

            @Override
            public String getDescription() {
                return "показать справку по доступным командам";
            }
        });

        commands.put("info", new Command() {
            @Override
            public String execute(String[] args) {
                return String.format("Информация о коллекции:%n" +
                        "Тип: %s%n" +
                        "Время инициализации: %s%n" +
                        "Количество элементов: %d", 
                        collection.getClass().getSimpleName(),
                        initializationTime,
                        collection.size());
            }

            @Override
            public String getName() {
                return "info";
            }

            @Override
            public String getDescription() {
                return "показать информацию о коллекции";
            }
        });

        commands.put("show", new Command() {
            @Override
            public String execute(String[] args) {
                if (collection.isEmpty()) {
                    return "Коллекция пуста";
                }
                StringBuilder result = new StringBuilder("Элементы коллекции:\n");
                collection.forEach(group -> result.append(group).append("\n"));
                return result.toString();
            }

            @Override
            public String getName() {
                return "show";
            }

            @Override
            public String getDescription() {
                return "показать все элементы коллекции";
            }
        });

        commands.put("add", new Command() {
            @Override
            public String execute(String[] args) {
                StudyGroup group = consoleManager.readStudyGroup();
                collection.add(group);
                return "Элемент успешно добавлен";
            }

            @Override
            public String getName() {
                return "add";
            }

            @Override
            public String getDescription() {
                return "добавить новый элемент";
            }
        });

        commands.put("update", new Command() {
            @Override
            public String execute(String[] args) {
                if (args.length != 1) {
                    return "Использование: update <id>";
                }
                try {
                    long id = Long.parseLong(args[0]);
                    Optional<StudyGroup> group = collection.stream()
                            .filter(g -> g.getId() == id)
                            .findFirst();
                    if (group.isPresent()) {
                        StudyGroup newGroup = consoleManager.readStudyGroup();
                        collection.set(collection.indexOf(group.get()), newGroup);
                        return "Элемент успешно обновлен";
                    }
                    return "Элемент с id " + id + " не найден";
                } catch (NumberFormatException e) {
                    return "Неверный формат id";
                }
            }

            @Override
            public String getName() {
                return "update";
            }

            @Override
            public String getDescription() {
                return "обновить элемент по id";
            }
        });

        commands.put("remove_by_id", new Command() {
            @Override
            public String execute(String[] args) {
                if (args.length != 1) {
                    return "Использование: remove_by_id <id>";
                }
                try {
                    long id = Long.parseLong(args[0]);
                    Optional<StudyGroup> groupToRemove = collection.stream()
                            .filter(group -> group.getId() == id)
                            .findFirst();
                    
                    if (groupToRemove.isPresent()) {
                        StudyGroup group = groupToRemove.get();
                        if (group.getGroupAdmin() != null) {
                            group.getGroupAdmin().releasePassportID();
                        }
                        collection.remove(group);
                        return "Элемент успешно удален";
                    }
                    return "Элемент с id " + id + " не найден";
                } catch (NumberFormatException e) {
                    return "Неверный формат id";
                }
            }

            @Override
            public String getName() {
                return "remove_by_id";
            }

            @Override
            public String getDescription() {
                return "удалить элемент по id";
            }
        });

        commands.put("clear", new Command() {
            @Override
            public String execute(String[] args) {
                collection.forEach(group -> {
                    if (group.getGroupAdmin() != null) {
                        group.getGroupAdmin().releasePassportID();
                    }
                });
                collection.clear();
                return "Коллекция очищена";
            }

            @Override
            public String getName() {
                return "clear";
            }

            @Override
            public String getDescription() {
                return "очистить коллекцию";
            }
        });

        commands.put("save", new Command() {
            @Override
            public String execute(String[] args) {
                try {
                    fileManager.writeCollection(collection);
                    return "Коллекция успешно сохранена";
                } catch (IOException e) {
                    return "Ошибка при сохранении коллекции: " + e.getMessage();
                }
            }

            @Override
            public String getName() {
                return "save";
            }

            @Override
            public String getDescription() {
                return "сохранить коллекцию в файл";
            }
        });

        commands.put("execute_script", new Command() {
            @Override
            public String execute(String[] args) {
                if (args.length != 1) {
                    return "Использование: execute_script <имя_файла>";
                }
                String fileName = args[0];
                
                if (executingScripts.contains(fileName)) {
                    return "Ошибка: Обнаружено рекурсивное выполнение скрипта";
                }

                try {
                    executingScripts.add(fileName);
                    List<String> lines = Files.readAllLines(Paths.get(fileName));
                    StringBuilder result = new StringBuilder();
                    
                    for (String line : lines) {
                        String commandResult = executeCommand(line);
                        result.append(String.format("$ %s%n%s%n", line, commandResult));
                    }
                    
                    return result.toString();
                } catch (IOException e) {
                    return "Ошибка при выполнении скрипта: " + e.getMessage();
                } finally {
                    executingScripts.remove(fileName);
                }
            }

            @Override
            public String getName() {
                return "execute_script";
            }

            @Override
            public String getDescription() {
                return "выполнить скрипт из файла";
            }
        });

        commands.put("insert_at", new Command() {
            @Override
            public String execute(String[] args) {
                if (args.length != 1) {
                    return "Использование: insert_at <индекс>";
                }
                try {
                    int index = Integer.parseInt(args[0]);
                    if (index < 0 || index > collection.size()) {
                        return "Индекс вне допустимого диапазона";
                    }
                    StudyGroup group = consoleManager.readStudyGroup();
                    collection.add(index, group);
                    return "Элемент успешно вставлен";
                } catch (NumberFormatException e) {
                    return "Неверный формат индекса";
                }
            }

            @Override
            public String getName() {
                return "insert_at";
            }

            @Override
            public String getDescription() {
                return "вставить элемент в заданную позицию";
            }
        });

        commands.put("remove_first", new Command() {
            @Override
            public String execute(String[] args) {
                if (collection.isEmpty()) {
                    return "Коллекция пуста";
                }
                StudyGroup group = collection.get(0);
                if (group.getGroupAdmin() != null) {
                    group.getGroupAdmin().releasePassportID();
                }
                collection.remove(0);
                return "Первый элемент удален";
            }

            @Override
            public String getName() {
                return "remove_first";
            }

            @Override
            public String getDescription() {
                return "удалить первый элемент";
            }
        });

        commands.put("add_if_min", new Command() {
            @Override
            public String execute(String[] args) {
                StudyGroup newGroup = consoleManager.readStudyGroup();
                if (collection.isEmpty() || newGroup.compareTo(Collections.min(collection)) < 0) {
                    collection.add(newGroup);
                    return "Элемент успешно добавлен";
                }
                return "Элемент не добавлен (не меньше минимального)";
            }

            @Override
            public String getName() {
                return "add_if_min";
            }

            @Override
            public String getDescription() {
                return "добавить элемент, если он меньше минимального";
            }
        });

        commands.put("group_counting_by_group_admin", new Command() {
            @Override
            public String execute(String[] args) {
                Map<String, Long> groupCount = collection.stream()
                        .collect(Collectors.groupingBy(
                                group -> group.getGroupAdmin() != null ? 
                                        group.getGroupAdmin().getName() : "Нет администратора",
                                Collectors.counting()
                        ));
                
                if (groupCount.isEmpty()) {
                    return "Коллекция пуста";
                }

                StringBuilder result = new StringBuilder("Группы по администраторам:\n");
                groupCount.forEach((admin, count) -> 
                        result.append(String.format("%s: %d групп(а)%n", admin, count)));
                return result.toString();
            }

            @Override
            public String getName() {
                return "group_counting_by_group_admin";
            }

            @Override
            public String getDescription() {
                return "сгруппировать элементы по администратору";
            }
        });

        commands.put("count_by_students_count", new Command() {
            @Override
            public String execute(String[] args) {
                if (args.length != 1) {
                    return "Использование: count_by_students_count <количество>";
                }
                try {
                    int targetCount = Integer.parseInt(args[0]);
                    long count = collection.stream()
                            .filter(group -> group.getStudentsCount() == targetCount)
                            .count();
                    return String.format("Количество групп с %d студентами: %d", targetCount, count);
                } catch (NumberFormatException e) {
                    return "Неверный формат числа";
                }
            }

            @Override
            public String getName() {
                return "count_by_students_count";
            }

            @Override
            public String getDescription() {
                return "посчитать количество групп по числу студентов";
            }
        });

        commands.put("print_field_ascending_group_admin", new Command() {
            @Override
            public String execute(String[] args) {
                if (collection.isEmpty()) {
                    return "Коллекция пуста";
                }

                List<String> admins = collection.stream()
                        .map(StudyGroup::getGroupAdmin)
                        .filter(Objects::nonNull)
                        .map(admin -> String.format("%s (Рост: %.1f, Паспорт: %s)", 
                                admin.getName(), 
                                admin.getHeight(),
                                admin.getPassportID()))
                        .sorted()
                        .collect(Collectors.toList());

                if (admins.isEmpty()) {
                    return "Администраторы групп не найдены";
                }

                StringBuilder result = new StringBuilder("Администраторы групп в порядке возрастания:\n");
                admins.forEach(admin -> result.append(admin).append("\n"));
                return result.toString();
            }

            @Override
            public String getName() {
                return "print_field_ascending_group_admin";
            }

            @Override
            public String getDescription() {
                return "вывести поля администраторов в порядке возрастания";
            }
        });
    }

    private void loadCollection() {
        try {
            collection.addAll(fileManager.readCollection());
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке коллекции: " + e.getMessage());
        }
    }

    public String executeCommand(String commandLine) {
        String[] parts = commandLine.trim().split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

        Command command = commands.get(commandName);
        if (command == null) {
            return "Неизвестная команда. Введите 'help' для просмотра доступных команд.";
        }

        try {
            return command.execute(args);
        } catch (Exception e) {
            return "Ошибка при выполнении команды: " + e.getMessage();
        }
    }
} 