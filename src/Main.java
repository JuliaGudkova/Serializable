import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void saveGames(String directory, GameProgress name){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(directory))) {
            oos.writeObject(name);
        } catch (Exception ex) {
            System.out.println(ex.getMessage()); }
    }
public static void zipFile(String directory, String name) {
    try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(directory))) {
        File fileNew = new File(name);
        for (File file : fileNew.listFiles()) {
            String nameFail = file.toString();
            if (nameFail.endsWith(".dat")) {
                FileInputStream fis = new FileInputStream(nameFail);
                byte[] bytes = new byte[fis.available()];
                fis.read(bytes);
                fis.close();

                ZipEntry entry = new ZipEntry(file.getName());
                zout.putNextEntry(entry);
                zout.write(bytes);
                zout.closeEntry();
            }
        }

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}


    public static void deleteFile (String name){
        File fileNew = new File(name);
        fileNew.delete();
    }

    public static void main (String[]args){

        GameProgress one = new GameProgress(10, 3, 5, 2.3);
        GameProgress two = new GameProgress(2, 34, 45, 3.4);
        GameProgress three = new GameProgress(3, 45, 56, 3.7);

        saveGames("/Users/uliagudkova/Games/savegames/save1.dat", one);
        saveGames("/Users/uliagudkova/Games/savegames/save2.dat", two);
        saveGames("/Users/uliagudkova/Games/savegames/save3.dat", three);
        zipFile("/Users/uliagudkova/Games/savegames/new.zip",
                "/Users/uliagudkova/Games/savegames");


        deleteFile("/Users/uliagudkova/Games/savegames/save3.dat");
        deleteFile("/Users/uliagudkova/Games/savegames/save2.dat");
        deleteFile("/Users/uliagudkova/Games/savegames/save1.dat");

    }
}

