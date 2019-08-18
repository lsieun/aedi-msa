package utils;

import java.io.*;

public class FileUtils {
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
