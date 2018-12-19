import java.util.ArrayList;
import java.util.List;

public class God {
    private final boolean flgPrint;
    SystemSetting setting ;
    public int clk ;
    int clkEND ;
    Scheduling scheduling ;
    public List<CNC> cncs ;
    RGV_II rgv ;
    public List<Production> productions_end = new ArrayList<Production>() ;
    public List<fault> productions_fault = new ArrayList<fault>() ;

    String printPerS = "" ;
    Statistics statistics = new Statistics() ;


    public void start() {
        for (int i = 0; i < clkEND; ++i) {
            clock() ;
        }

        System.out.println("\n======此次工作完成, 共生产二道工序产品成料" + productions_end.size() + "件， 他们的信息如下：======");

        if (flgPrint) {
            int i = 0;
            for (Production p : productions_end) {
                System.out.println(p.no + "\t"
                        + p.TWO1_CNC_No + "\t" + p.tmrStart_1 + "\t" + p.tmrEnd_1+ "\t" +
                        + p.TWO2_CNC_No + "\t" + p.tmrStart_2 + "\t" + p.tmrEnd_2);
            }
        }

        if (setting.rateFault > 0) {
            System.out.println("\n======期间共产生故障" + productions_fault.size() + "起， 他们的信息如下：======");

            if (flgPrint) {
                int i = 0;
                for (fault f : productions_fault) {
                    System.out.println(f.p.no + "\t" + f.cnc_no + "\t" + f.start + "\t" + f.end);
                }
            }
        }

        System.out.println("") ;

        for (int i = 0; i < 8; ++i) {
            System.out.printf("CNC_%d 单台作业效率 %.2f%%\n", i+1, 100*statistics.getSingleEfficiency(i)) ;
        }
        System.out.printf("\nCNCs 总作业效率 %.2f%%", 100*statistics.getSysEfficiency()) ;

        statistics.output(setting.rateFault > 0? "sta3_2.txt": "sta2.txt") ;
    }



    public God(SystemSetting setting, int clkEND, boolean flgPrint) {
        this.setting = setting;
        this.clkEND = clkEND;
        this.scheduling = new Sch_SqeOrder_TWO_2(this) ;
        this.flgPrint = flgPrint ;
        clk = 0 ;

        List<List<CNC_MODE>> cnc_modes = new ArrayList<List<CNC_MODE>>() {
            {
                //8-0
                add(new ArrayList<CNC_MODE>(){
                    {
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                    }
                }) ;

                //7-1
                add(new ArrayList<CNC_MODE>(){
                    {
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                    }
                }) ;

                //6-2
                add(new ArrayList<CNC_MODE>(){
                    {
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                    }
                }) ;

                //5-3
                add(new ArrayList<CNC_MODE>(){
                    {
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_1);
                    }
                }) ;

                //4-4
                add(new ArrayList<CNC_MODE>(){
                    {
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                    }
                }) ;

                //3-5
                add(new ArrayList<CNC_MODE>(){
                    {
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                    }
                }) ;

                //2-6
                add(new ArrayList<CNC_MODE>(){
                    {
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                    }
                }) ;

                //1-7
                add(new ArrayList<CNC_MODE>(){
                    {
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_1);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                    }
                }) ;

                // 0-8
                add(new ArrayList<CNC_MODE>(){
                    {
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                        add(CNC_MODE.TWO_2);
                    }
                }) ;
            }
        } ;



        this.cncs = this.scheduling.initCNCS(cnc_modes.get(calcCNC())) ;
        this.rgv = new RGV_II(this) ;

    }

    private int calcCNC() {
        int min = (this.setting.tmrCncProcessing1+this.setting.tmrCncProcessing2)*8 ;
        int mini = 4 ;
        for (int i = 1; i < 8; ++i)
        {
            int tmp = Math.abs(i*this.setting.tmrCncProcessing1 - (8-i)*this.setting.tmrCncProcessing2) ;
            if (tmp < min) {
                min = tmp ;
                mini = i ;
            }
        }
        System.out.println("分配了" + (8-mini) + "台CNC进行第一道工序") ;
        return mini ;
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
