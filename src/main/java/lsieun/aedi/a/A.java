package lsieun.aedi.a;

import java.io.File;
import java.util.List;

import org.objectweb.asm.ClassReader;

import lsieun.utils.archive.JarUtils;

public class A {
    public static void main(String[] args) {
        String user_dir = System.getProperty("user.dir");
        String filepath = user_dir + File.separator + "lib" + File.separator + "idea.jar";
        System.out.println("file://" + filepath);

        List<String> list = JarUtils.getClassEntries(filepath);
        for(String str : list) {
            byte[] bytes = JarUtils.readClass(filepath, str);
            A_ClassAdapter cv = new A_ClassAdapter(null, str);
            ClassReader cr = new ClassReader(bytes);
            cr.accept(cv, 0);
        }
    }
}
