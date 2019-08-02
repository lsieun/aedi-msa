package lsieun.aedi.c;

import static org.objectweb.asm.Opcodes.ASM6;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class C_ClassAdapter extends ClassVisitor {
    public C_ClassAdapter(ClassVisitor classVisitor) {
        super(ASM6, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if (mv != null && name.equals("a") && descriptor.equals("(Ljava/lang/String;IICLjava/lang/String;[Lcom/jetbrains/a/a/l;)V")) {
            mv = new C_MethodAdapter(mv);
        }
        return mv;
    }
    
}
