import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        Path directorioRaiz = Path.of("/tmp/niats");
        ordenarFicheros(directorioRaiz);

    }

    public static void ordenarFicheros(Path dir) {
        try {
            Files.walk(dir).skip(1).sorted(Comparator.reverseOrder()).forEach(p -> {
                try {
                    if(Files.isDirectory(p)){
                        Files.delete(p);
                    }
                    else
                        Files.move(p, dir.resolve(p.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}