package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Пользовательское исключение для обработки ошибок ввода/вывода
class FileOperationException extends Exception {
    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class FileIOExample {

    private static final String FILENAME = "data.txt";

    // Метод для записи данных в файл
    public void writeDataToFile(List<String> data) throws FileOperationException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new FileOperationException("Ошибка при записи в файл", e);
        }
    }

    // Метод для чтения данных из файла
    public List<String> readDataFromFile() throws FileOperationException {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            throw new FileOperationException("Ошибка при чтении из файла", e);
        }
        return data;
    }

    public static void main(String[] args) {
        FileIOExample fileIO = new FileIOExample();
        List<String> dataToWrite = List.of("Строка 1", "Строка 2", "Строка 3");

        try {
            fileIO.writeDataToFile(dataToWrite);
            List<String> readData = fileIO.readDataFromFile();
            System.out.println("Данные из файла:");
            for (String line : readData) {
                System.out.println(line);
            }
        } catch (FileOperationException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            System.out.println("Запись данных прошла успешно");
        }
    }
}
