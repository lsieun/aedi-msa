package lsieun.aedi.c;

import java.io.IOException;
import java.io.PrintWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.TraceClassVisitor;

public class C {
    public static void main(String[] args) throws IOException {
        int parsingOptions = 0;
        String className = "lsieun.aedi.c.C_JustReturn";
        Printer printer = new ASMifier();
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, printer, new PrintWriter(System.out));
        new ClassReader(className).accept(traceClassVisitor, parsingOptions);
    }
}
