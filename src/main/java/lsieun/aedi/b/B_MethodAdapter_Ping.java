package lsieun.aedi.b;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.MethodVisitor;

public class B_MethodAdapter_Ping extends MethodVisitor {
    public B_MethodAdapter_Ping(MethodVisitor methodVisitor) {
        super(ASM5, methodVisitor);
    }

    @Override
    public void visitCode() {
        mv.visitCode();
        mv.visitTypeInsn(NEW, "java/lang/Exception");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Exception", "<init>", "()V", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception", "printStackTrace", "()V", false);

        mv.visitTypeInsn(NEW, "com/jetbrains/ls/responses/PingResponse");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "com/jetbrains/ls/responses/PingResponse", "<init>", "()V", false);
        mv.visitVarInsn(ASTORE, 5);

        mv.visitVarInsn(ALOAD, 5);
        mv.visitFieldInsn(GETSTATIC, "com/jetbrains/ls/responses/ResponseCode", "OK", "Lcom/jetbrains/ls/responses/ResponseCode;");
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/PingResponse", "setResponseCode", "(Lcom/jetbrains/ls/responses/ResponseCode;)V", false);

        mv.visitVarInsn(ALOAD, 5);
        mv.visitInsn(ARETURN);
    }
}
