package lsieun.aedi.b;

import static org.objectweb.asm.Opcodes.ASM5;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class B_ClassAdapter extends ClassVisitor {
    public B_ClassAdapter(ClassVisitor classVisitor) {
        super(ASM5, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if (mv != null && name.equals("a") && descriptor.equals("(ILjava/lang/String;ILjava/lang/String;IBIZ)Lcom/jetbrains/ls/responses/ObtainTicketResponse;")) {
            mv = new B_MethodAdapter_Ticket(mv);
        }
        if (mv != null && name.equals("a") && descriptor.equals("(BLjava/lang/String;JLjava/lang/String;)Lcom/jetbrains/ls/responses/PingResponse;")) {
            mv = new B_MethodAdapter_Ping(mv);
        }
        return mv;
    }
}
