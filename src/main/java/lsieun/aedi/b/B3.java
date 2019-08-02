package lsieun.aedi.b;

import java.io.File;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import lsieun.utils.archive.JarUtils;
import lsieun.utils.io.FileUtils;

public class B3 {
    public static void main(String[] args) {
        String user_dir = System.getProperty("user.dir");
        String jarpath = user_dir + File.separator + "lib" + File.separator + "idea.jar";
        System.out.println("file://" + jarpath);

        String item = "com/jetbrains/a/a/g.class";
        byte[] origin_bytes = JarUtils.readClass(jarpath, item);
        ClassReader cr = new ClassReader(origin_bytes);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        B_ClassAdapter cv = new B_ClassAdapter(cw);
        cr.accept(cv, 0);

        String className = "com.jetbrains.a.a.g";
        byte[] bytes = cw.toByteArray();

        String filepath = FileUtils.getFilePath(B3.class, className);
        System.out.println("file://" + filepath);
        FileUtils.writeBytes(filepath, bytes);
    }
}
