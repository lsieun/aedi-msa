package utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarUtils {
    public static List<String> getAllEntries(String filePath) {
        List<String> list = new ArrayList<String>();
        try {
            JarFile jarFile = new JarFile(filePath);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                list.add(entry.getName());
            }
            jarFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getClassEntries(String filepath) {
        List<String> list = getAllEntries(filepath);
        filter(list, "^.+\\.class$");
        return list;
    }

    public static void filter(List<String> list, String regex) {
        if (list == null || list.size() < 1) return;
        if (regex == null || "".equals(regex)) return;

        int size = list.size();
        for(int index = size -1; index >=0; index--) {
            String item = list.get(index);
            if (!matches(item, regex)) {
                list.remove(index);
            }
        }
    }


    public static boolean matches(String str, String regex) {
        if (str == null) return false;
        if (regex == null) return true;

        if (str.matches(regex)) return true;
        return false;
    }

    public static boolean matches(String str, String[] regex_array) {
        if (str == null) return false;
        if (regex_array == null || regex_array.length < 1) return true;

        for (String regex : regex_array) {
            if (matches(str, regex)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] readClass(String jarPath, String entryName) {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarPath);
            JarEntry entry = jarFile.getJarEntry(entryName);
            InputStream in = jarFile.getInputStream(entry);

            in = new BufferedInputStream(in);

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            in.close();
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (jarFile != null) {
                try {
                    jarFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
