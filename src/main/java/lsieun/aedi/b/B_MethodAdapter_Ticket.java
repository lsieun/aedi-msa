package lsieun.aedi.b;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.MethodVisitor;

public class B_MethodAdapter_Ticket extends MethodVisitor {
    public B_MethodAdapter_Ticket(MethodVisitor methodVisitor) {
        super(ASM5, methodVisitor);
    }

    @Override
    public void visitCode() {
        mv.visitCode();
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Hello");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        mv.visitTypeInsn(NEW, "java/lang/Exception");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Exception", "<init>", "()V", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception", "printStackTrace", "()V", false);

        mv.visitTypeInsn(NEW, "com/jetbrains/ls/responses/ObtainTicketResponse");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "<init>", "()V", false);
        mv.visitVarInsn(ASTORE, 8);

        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        mv.visitVarInsn(LSTORE, 9);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitFieldInsn(GETSTATIC, "com/jetbrains/ls/responses/ResponseCode", "OK", "Lcom/jetbrains/ls/responses/ResponseCode;");
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "setResponseCode", "(Lcom/jetbrains/ls/responses/ResponseCode;)V", false);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitLdcInsn("1");
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "setTicketId", "(Ljava/lang/String;)V", false);

        mv.visitLdcInsn("user.name");
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "getProperty", "(Ljava/lang/String;)Ljava/lang/String;", false);
        mv.visitVarInsn(ASTORE, 11);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        mv.visitLdcInsn("licensee=");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitVarInsn(ALOAD, 11);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitLdcInsn("\u0009licenseType=0");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "setTicketProperties", "(Ljava/lang/String;)V", false);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitLdcInsn("DIY ObtainTicketResponse Message");
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "setMessage", "(Ljava/lang/String;)V", false);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitVarInsn(LLOAD, 9);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "setSalt", "(J)V", false);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitVarInsn(LLOAD, 9);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(J)Ljava/lang/String;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "setConfirmationStamp", "(Ljava/lang/String;)V", false);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitLdcInsn("DIY_server_uid");
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "setServerUid", "(Ljava/lang/String;)V", false);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitLdcInsn("DIY_server_lease");
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "setServerLease", "(Ljava/lang/String;)V", false);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitLdcInsn("DIY_lease_signature");
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "setLeaseSignature", "(Ljava/lang/String;)V", false);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitLdcInsn("DIY_signature");
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "setSignature", "(Ljava/lang/String;)V", false);

        mv.visitLdcInsn(new Long(1741730816L));
        mv.visitVarInsn(LSTORE, 12);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitVarInsn(LLOAD, 12);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "setProlongationPeriod", "(J)V", false);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitVarInsn(LLOAD, 12);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "setValidationPeriod", "(J)V", false);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitVarInsn(LLOAD, 12);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/jetbrains/ls/responses/ObtainTicketResponse", "setValidationDeadlinePeriod", "(J)V", false);

        mv.visitVarInsn(ALOAD, 8);
        mv.visitInsn(ARETURN);
    }

}
