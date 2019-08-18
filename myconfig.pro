-injars       target/Inability-2019.02.jar
-outjars      target/Inability-2019.02-pro.jar
-libraryjars  <java.home>/lib/rt.jar
-overloadaggressively
-repackageclasses In.God.We.Trust
-printmapping target/myapplication.map

-keep public class Main{
    public static void main(java.lang.String[]);
}
