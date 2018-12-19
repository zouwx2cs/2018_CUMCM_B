import java.util.ArrayList;
import java.util.List;

public class God {
    private final boolean flgPrint;
    SystemSetting setting ;
    public int clk ;
    int clkEND ;
    Scheduling scheduling ;
    public List<CNC> cncs ;
    RGV rgv ;
    public List<Production> productions_end = new ArrayList<Production>() ;
    public List<fault> productions_fault = new ArrayList<fault>() ;

    String printPerS = "" ;
    Statistics statistics = new Statistics() ;


    public void start() {
        for (int i = 0; i < clkEND; ++i) {
            clock() ;
        }

        System.out.println("\n======此次工作完成, 共生产产品成料" + productions_end.size() + "件， 他们的信息如下：======");

        if (flgPrint) {
            int i = 0;
            for (Production p : productions_end) {
                System.out.println(p.no + "\t" + p.ONE_CNC_No + "\t" + p.tmrStart + "\t" + p.tmrEnd) ;
            }
        }

        if (setting.rateFault > 0) {
            System.out.println("\n======期间共产生故障" + productions_fault.size() + "起， 他们的信息如下：======");

            if (flgPrint) {
                int i = 0;
                for (fault f : productions_fault) {
                    System.out.println(f.p.no + "\t" + f.cnc_no + "\t" + f.start + "\t" + f.end) ;
                }
            }
        }

        System.out.println("") ;

        for (int i = 0; i < 8; ++i) {
            System.out.printf("CNC_%d 单台作业效率 %.2f%%\n", i+1, 100*statistics.getSingleEfficiency(i)) ;
        }
        System.out.printf("\nCNCs 总作业效率 %.2f%%", 100*statistics.getSysEfficiency()) ;

        statistics.output(setting.rateFault > 0? "sta3_1.txt": "sta1.txt") ;
    }

    public God(SystemSetting setting, int clkEND, boolean flgPrint) {
        this.setting = setting;
        this.clkEND = clkEND;
        this.scheduling = new Sch_SqeOrder_2(this) ;
        this.flgPrint = flgPrint ;
        clk = 0 ;

        List<CNC_MODE> cnc_mode = new ArrayList<CNC_MODE>(){
            {
                add(CNC_MODE.ONE);
                add(CNC_MODE.ONE);
                add(CNC_MODE.ONE);
                add(CNC_MODE.ONE);
                add(CNC_MODE.ONE);
                add(CNC_MODE.ONE);
                add(CNC_MODE.ONE);
                add(CNC_MODE.ONE);
            }
        } ;
        this.cncs = this.scheduling.initCNCS(cnc_mode) ;
        this.rgv = new RGV(this) ;

    }

    public void clock()
    {
        for (CNC cnc: cncs) {
            cnc.clock() ;
        }
        rgv.clock() ;

        if (flgPrint && !printPerS.equals("")) {
            System.out.printf("%6d>\t%s", clk, printPerS);
            printPerS = "";
        }
        stat() ;
        ++clk ;
    }

    /*public String P(String info) {
        String clkString = clk + ">  \t" ;
        clkString += info ;
        System.out.println(clkString);
        return clkString ;
    }*/

    public String P(String info) {
        if (flgPrint) {
            if (!printPerS.equals(""))
                printPerS += "\t\t";
            printPerS += info + '\n';
        }
        return "" ;
    }

    private void stat() {
        List<CNC_STATE> cnc_states = new ArrayList<>() ;
        for (int i = 0; i < 8; ++i) {
            statistics.tmrTotals[i] += 1;
            if (cncs.get(i).cnc_state == CNC_STATE.PROCESSING
                    || cncs.get(i).cnc_state == CNC_STATE.UP
                    || cncs.get(i).cnc_state == CNC_STATE.DOWN
                    || cncs.get(i).cnc_state == CNC_STATE.UPDOWN)
                ++statistics.tmrRunnings[i] ;
            cnc_states.add(cncs.get(i).cnc_state) ;
        }
        if (clk % 10 == 0 && clk <= 6000) {
            statistics.cnc_statess.add(cnc_states);
            statistics.rgv_states.add(rgv.rgv_state);
        }
    }


}
