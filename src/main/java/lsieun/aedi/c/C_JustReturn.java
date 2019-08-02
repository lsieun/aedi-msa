package lsieun.aedi.c;

import com.jetbrains.a.a.l;
import com.jetbrains.a.a.u;

public class C_JustReturn {
    public static void a(String var0, int var1, int var2, char var3, String var4, l... var5) throws u {
        System.out.println("********Into Just Return");
        new Exception().printStackTrace();

        System.out.println("==1==:" + var0);
        System.out.println("==2==:" + var1);
        System.out.println("==3==:" + var2);
        System.out.println("==4==:" + var3);
        System.out.println("==5==:" + var4);
        System.out.println("==6==:" + var5);
        return;
    }
}
