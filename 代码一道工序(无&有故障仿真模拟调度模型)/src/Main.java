public class Main
{
    static double rateFault = 0.01 ;
    static int tmrFaultFix = 15*60 ;

    public static void main(String[] args) {
        //System.out.println("Hello World!") ;
        SystemSetting setting1 = new SystemSetting(20, 33, 46, 560, 400, 378, 28, 31, 25, rateFault, tmrFaultFix) ;
        SystemSetting setting2 = new SystemSetting(23, 41, 59, 580, 280, 500, 30, 35, 30, rateFault, tmrFaultFix) ;
        SystemSetting setting3 = new SystemSetting(18, 32, 46, 545, 455, 182, 27, 32, 25, rateFault, tmrFaultFix) ;
        God god = new God(setting1, 8*60*60, true) ;
        god.start() ;
    }
}
