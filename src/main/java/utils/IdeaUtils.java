package utils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import adapter.ObtainPingAdapter;
import adapter.ObtainTicketAdapter;
import adapter.RegexClassAdapter;

public class IdeaUtils {
    public static void process(final String input_file_path) {
        if (input_file_path == null) {
            System.out.println("Invalid File Path");
            return;
        }

        String jar_path = input_file_path.trim();
        if ("".equals(jar_path)) {
            System.out.println("Invalid File Path");
            return;
        }

        if (!jar_path.endsWith(".jar")) {
            System.out.println("Not Jar File");
            return;
        }

        File file = new File(jar_path);
        if (!file.exists()) {
            System.out.println("File Not Exist");
            return;
        }

        FileUtils.backup(input_file_path);

        String tmpdir = System.getProperty("java.io.tmpdir") + File.separator + "jetbrains_idea_" + System.currentTimeMillis();
        File tmpFile = new File(tmpdir);
        tmpFile.mkdirs();

        Map<String, String> map = new HashMap<>();

        List<String> list = JarUtils.getClassEntries(jar_path);

        JarUtils.filter(list, "^com/jetbrains/\\w/\\w/\\w+\\.class$");

        int count = 0;
        for (String item : list) {
            byte[] origin_bytes = JarUtils.readClass(jar_path, item);
            if (origin_bytes == null) continue;
            ClassReader cr = new ClassReader(origin_bytes);

            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ObtainTicketAdapter ticketAdapter = new ObtainTicketAdapter(cw, new String[]{"^\\w+:\\(.+\\)Lcom/jetbrains/ls/responses/ObtainTicketResponse;$"});
            ObtainPingAdapter obtainPingAdapter = new ObtainPingAdapter(ticketAdapter, new String[] {"^\\w+:\\(.+\\)Lcom/jetbrains/ls/responses/PingResponse;$"});
            RegexClassAdapter returnAdapter = new RegexClassAdapter(obtainPingAdapter, new String[] {
                    "^\\w+:\\(Ljava/lang/String;Ljava/lang/String;J\\[Lcom/jetbrains/\\w+/\\w+/\\w+;\\)V$", // 2018.03
                    "^\\w+:\\(Ljava/lang/String;SSILjava/lang/String;.+\\)V$" // 2019.02
            });

            cr.accept(returnAdapter, 0);

            if (ticketAdapter.gotcha || obtainPingAdapter.gotcha || returnAdapter.gotcha) {
                byte[] bytes = cw.toByteArray();
                //String filepath = FileUtils.getFilePath(jar_path, item);
                String filepath = tmpdir + File.separator + item;
                System.out.println("file://" + filepath);
                FileUtils.writeBytes(filepath, bytes);

                map.put(item, filepath);
                count++;
            }

        }

        if (count != 2) {
            System.out.println("It seems that something is wrong.");
            return;

        }
        System.out.println("Go ahead and Try Step 2.");

//        boolean backup_success = FileUtils.backup(input_file_path);
//        if (backup_success) {
//            System.out.println("back up success");
//        }

        updateJar(jar_path, map);

    }

    public static void updateJar(String jar_path, Map<String, String> map) {
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        // locate file system by using the syntax
        // defined in java.net.JarURLConnection
        URI uri = URI.create("jar:file:" + jar_path);

        try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                String item = entry.getKey();
                String filepath = entry.getValue();

                Path externalTxtFile = Paths.get(filepath);
                Path pathInZipfile = zipfs.getPath(item);
                // copy a file into the zip file
                Files.copy( externalTxtFile,pathInZipfile, StandardCopyOption.REPLACE_EXISTING );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
