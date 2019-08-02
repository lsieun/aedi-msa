package lsieun.aedi.a;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.jetbrains.ls.responses.ObtainTicketResponse;
import com.jetbrains.ls.responses.PingResponse;

public class A_ClassAdapter extends ClassVisitor {
    private String filepath;

    public A_ClassAdapter(ClassVisitor classVisitor, String filepath) {
        super(Opcodes.ASM5, classVisitor);
        this.filepath = filepath;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        {
            Type t = Type.getType(ObtainTicketResponse.class);
            String desc = t.getDescriptor();
            if (descriptor.endsWith(desc)) {
                System.out.println(String.format("%s\n%s:%s", filepath, name, descriptor));
            }
        }
        {
            Type t = Type.getType(PingResponse.class);
            String desc = t.getDescriptor();
            if (descriptor.endsWith(desc)) {
                System.out.println(String.format("%s\n%s:%s", filepath, name, descriptor));
            }

        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
