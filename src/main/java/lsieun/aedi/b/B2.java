package lsieun.aedi.b;

import java.io.IOException;
import java.io.PrintWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.TraceClassVisitor;

public class B2 {
    public static void main(String[] args) throws IOException {
        int parsingOptions = 0;
        String className = "lsieun.aedi.b.B_PingResponse";
        Printer printer = new ASMifier();
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, printer, new PrintWriter(System.out));
        new ClassReader(className).accept(traceClassVisitor, parsingOptions);
    }
}
