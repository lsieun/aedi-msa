package adapter;

import static org.objectweb.asm.Opcodes.*;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import utils.JarUtils;

public class RegexClassAdapter extends ClassVisitor {
    // 开始前，传入的参数
    private String[] name_desc_regex_array;

    // 过程中，记录的参数
    protected String className;
    protected boolean isInterface;
    private boolean hasPrintClassName;

    // 结束后，输出的参数
    public boolean gotcha = false;

    public RegexClassAdapter(ClassVisitor classVisitor, String[] name_desc_regex_array) {
        super(ASM5, classVisitor);
        this.name_desc_regex_array = name_desc_regex_array;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        className = name;
        isInterface = (access & ACC_INTERFACE) != 0;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        // （1） 接口，不处理
        if (isInterface) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }

        //（2）抽象方法，不处理
        boolean isAbstractMethod = (access & ACC_ABSTRACT) != 0;
        if (isAbstractMethod) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }

        // （3）方法名和描述符不符合要求，不处理。
        String name_desc = String.format("%s:%s", name, descriptor);
        if (!JarUtils.matches(name_desc, name_desc_regex_array)) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }

        // （4）条件符合了，可以进行处理了
        gotcha = true;
//        if (!hasPrintClassName) {
//            System.out.println(String.format("%s%s: %s", System.lineSeparator(), "ClassName", className));
//            hasPrintClassName = true;
//        }
//        System.out.println(String.format("method: %s:%s", name, descriptor));

        String newName = getNewName(name);
        generateNewBody(access, name, descriptor, signature, exceptions);

        return super.visitMethod(access, newName, descriptor, signature, exceptions);
    }

    private final String getNewName(String name) {
        return String.format("%s%s%s", "origin$", name, System.currentTimeMillis());
    }

    private final void generateNewBody(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        mv.visitCode();
        generateNewBody(mv, access, name, descriptor, signature, exceptions);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    protected void generateNewBody(MethodVisitor mv, int access, String name, String descriptor, String signature, String[] exceptions) {
        mv.visitInsn(RETURN);
    }

}
