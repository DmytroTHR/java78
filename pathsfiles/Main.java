package pathsfiles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        pathExamples("/usr/bin", "java");
        System.out.println("==============================");
        fileExamles(System.getenv("HOME"), "values.yaml");
    }

    private static void pathExamples(String dir, String file) {
        Path p = Paths.get(dir, file);
        System.out.println(p);
        Path fn = p.getFileName();
        System.out.println(fn);

        System.out.printf("Filesystem: %s; Separator: %s\n", p.getFileSystem().toString(),
                p.getFileSystem().getSeparator());
        System.out.printf("Root: %s; Parent: %s\n", p.getRoot(), p.getParent());
        for (int i = 0; i < p.getNameCount(); i++) {
            System.out.printf("Name: %s\n", p.getName(i));
        }
        System.out.println("URI: " + p.toUri());
    }

    private static void fileExamles(String dir, String file) throws IOException {
        Path p = Paths.get(dir, file);
        System.out.println(p);
        byte[] bytes = Files.readAllBytes(p);
        String content = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("File content:\n" + content);

        Path pc = Paths.get(dir, "copy_"+file);
        Files.createFile(pc);
        Files.copy(p, pc, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);

        String newLine = "Hey there";
        Files.write(p, newLine.getBytes(), StandardOpenOption.APPEND);

        List<String> lines = Files.readAllLines(p, StandardCharsets.UTF_8);
        for (String line : lines) {
            System.out.println("Line content: " + line);
        }

        Files.move(pc, p, StandardCopyOption.REPLACE_EXISTING);
        //Files.delete(pc);    
    }
}
