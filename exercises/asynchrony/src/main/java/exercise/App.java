package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

class App {

    // BEGIN
    // чтение в параллель содержимое двух файлов и запись в третий, без блокировки основного потока
    public static CompletableFuture<String> unionFiles(String file1, String file2, String destPath) {

        CompletableFuture<String> readFile1 = readFile(file1);
        CompletableFuture<String> readFile2 = readFile(file2);

        return readFile1.thenCombine(readFile2, (content1, content2) -> content1 + content2)
                .thenApply((unionContent -> { // для записи новый поток не создаём, используем существующий
                    try {
                        System.out.println(Thread.currentThread().getName() + ": writeToFile(" + destPath + ")");
                        Files.writeString(getFullPath(destPath), unionContent);
                        return unionContent;
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }))
                .exceptionally(e -> {
                    System.out.println(e.getMessage());
                    return "Error union files";
                });
    }

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    private static CompletableFuture<String> readFile(String file) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + ": readFile(" + file + ")");
                return Files.readString(getFullPath(file));
            } catch (IOException e) {
                throw new RuntimeException(e.getClass().getName() + ": " + e.getMessage());
            }
        });
    }

    // получение размера текущей директории, не включая подкаталоги, в асинхронном режиме
    public static CompletableFuture<Long> getDirectorySize(String dir) throws IOException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + ": getDirectorySize(" + dir + ")");
                return Files.walk(getFullPath(dir))
                        .filter(Files::isRegularFile)
                        .mapToLong(x -> x.toFile().length())
                        .sum();
            } catch (IOException e) {
                throw new RuntimeException(e.getClass().getName() + ": " + e.getMessage());
            }
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        // \\wsl$\Ubuntu-20.04\home\mch\Hexlet\java\exercises\asynchrony
        //String destPath = Paths.get(".").toRealPath().toString() + "/asynchrony"; // для запуска из IDEA

        String destPath = Paths.get(".").toRealPath().toString();
        String file1 = destPath + "/src/main/resources/file1.txt";
        String file2 = destPath + "/src/main/resources/file2.txt";
        String result = unionFiles(file1, file2,destPath + "/union.txt").get();
        System.out.println("result: " + result);

        System.out.println(String.format("Size %s: %d bytes", destPath, getDirectorySize(destPath).get()));
        // END
    }
}

