import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static StringBuilder sb = new StringBuilder();

    private static void createDirectory(String path){
        File src = new File(path);
        src.mkdir();
        sb.append(logAction(src));
    }

    private static void createFile(String path, String fileName){
        File src = new File(path, fileName);
        try {
            src.createNewFile();
            sb.append(logAction(src));
        } catch (IOException ex) {
            sb.append(ex.getMessage());
        }
    }

    private static String logAction(File loggingObj){
        return (loggingObj.isFile() ? "Файл " : "Каталог ") + loggingObj.getName() + (loggingObj.exists() ? " успешно " : " не ") + "создан" + "\n";
    }

    public static void main(String[] args) {

        File games = new File("Games");

        createDirectory("Games/src");
        createDirectory("Games/src/main");
        createFile("Games/src/main", "Main.java");
        createFile("Games/src/main", "Utils.java");
        createDirectory("Games/src/test");

        createDirectory("Games/res");
        createDirectory("Games/res/drawables");
        createDirectory("Games/res/vectors");
        createDirectory("Games/res/icons");

        createDirectory("Games/savegames");

        createDirectory("Games/temp");
        File logFile = new File("Games/temp", "temp.txt");
        createFile("Games/temp", "temp.txt");

        try(FileWriter writer = new FileWriter(logFile)) {
            writer.write(sb.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
