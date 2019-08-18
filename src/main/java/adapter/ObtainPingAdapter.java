package adapter;

import static org.objectweb.asm.Opcodes.*;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class ObtainPingAdapter extends RegexClassAdapter {
    public ObtainPingAdapter(ClassVisitor classVisitor, String[] name_desc_regex_array) {
        super(classVisitor, name_desc_regex_array);
    }

    @Override
    protected void generateNewBody(MethodVisitor mv, int access, String name, String descriptor, String signature, String[] exceptions) {
        mv.visitTypeInsn(NEW, "com/jetbrains/ls/responses/PingResponse");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "com/jetbrains/ls/responses/PingResponse", "<init>", "()V", false);

        mv.visitInsn(DUP);
        mv.visitFieldInsn(GETSTATIC, "com/jetbrains/ls/responses/ResponseCode", "OK", "Lcom/jetbrains/ls/responses/ResponseCode;");
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/PingResponse", "setResponseCode", "(Lcom/jetbrains/ls/responses/ResponseCode;)V", false);

        mv.visitInsn(ARETURN);
    }
}
