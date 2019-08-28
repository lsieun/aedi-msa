package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {

    public static void backup(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            return;
        }

        String newFilePath = filepath + new SimpleDateFormat(".yyyy.MM.dd_HH.mm.ss").format(new Date()) + ".backup";

        Path source = Paths.get(filepath);
        Path target = Paths.get(newFilePath);
        try {
            Files.copy(source, target);
        } catch (IOException ex) {
            //ex.printStackTrace();
            // swallow exception
        }

    }

    public static String getFilePath(String jar_path, String jar_item) {
        File file = new File(jar_path);
        File dirFile = file.getParentFile();
        File newFile = new File(dirFile, jar_item.replace('/', File.separatorChar));
        return newFile.getAbsolutePath();
    }

    public static void writeBytes(String filename, byte[] bytes) {
        File file = new File(filename);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (OutputStream out = new FileOutputStream(filename); BufferedOutputStream buff = new BufferedOutputStream(out);) {

            buff.write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
