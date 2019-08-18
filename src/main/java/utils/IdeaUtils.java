package utils;

import java.io.File;
import java.util.List;

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

        List<String> list = JarUtils.getClassEntries(jar_path);

        JarUtils.filter(list, "^com/jetbrains/\\w/\\w/\\w+\\.class$");

        int count = 0;
        for (String item : list) {
            byte[] origin_bytes = JarUtils.readClass(jar_path, item);
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
                String filepath = FileUtils.getFilePath(jar_path, item);
                System.out.println("file://" + filepath);
                FileUtils.writeBytes(filepath, bytes);

                count++;
            }

        }

        if (count == 2) {
            System.out.println("Go ahead and Try Step 2 and 3.");
        }
        else {
            System.out.println("It seems that something is wrong.");
        }
    }
}
