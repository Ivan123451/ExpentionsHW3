package task2;

import java.io.*;

class FileManager {
    public static void main(String[] args) {
        try {
            FileManager fileManager = new FileManager();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Введите имя файла: ");
            String fileName = reader.readLine();

            System.out.println("Выберите действие: ");
            System.out.println("1. Прочитать файл");
            System.out.println("2. Дописать информацию в файл");
            System.out.println("3. Скопировать файл");

            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    String content = fileManager.readFromFile(fileName);
                    System.out.println("Содержимое файла: " + content);
                    break;
                case 2:
                    System.out.println("Введите текст для дописывания:");
                    String appendText = reader.readLine();
                    fileManager.appendTo(fileManager.readFromFile(fileName), appendText, fileName);
                    System.out.println("Текст успешно дописан в файл.");
                    break;
                case 3:
                    System.out.println("Введите имя файла-копии:");
                    String copyFileName = reader.readLine();
                    fileManager.copyFile(fileName, copyFileName);
                    System.out.println("Файл успешно скопирован.");
                    break;
                default:
                    System.out.println("Некорректный выбор.");
                    break;
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public String readFromFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    public void appendTo(String currentContent, String newText, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(currentContent + newText);
        }
    }

    public void copyFile(String sourceFileName, String destinationFileName) throws IOException {
        try (InputStream inputStream = new FileInputStream(sourceFileName);
             OutputStream outputStream = new FileOutputStream(destinationFileName)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}