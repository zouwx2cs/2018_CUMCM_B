public class Production {
    static int num = 1 ;
    int no ;
    Production_MODE mode ;
    Production_STATE production_state ;
    public int tmrStart ;
    public int tmrEnd ;
    public int tmrStart_1 ;
    public int tmrEnd_1 ;
    public int tmrStart_2 ;
    public int tmrEnd_2 ;
    public int ONE_CNC_No = -1 ;
    public int TWO1_CNC_No = -1 ;
    public int TWO2_CNC_No = -1 ;

    public Production(Production_MODE mode) {
        production_state = Production_STATE.UN ;
        this.mode = mode ;
        this.no = num++ ;
    }

    public void start(int clk)
    {
        tmrStart = clk ;
    }

    public void end(int clk)
    {
        tmrEnd = clk ;
    }

    public void start1(int clk)
    {
        tmrStart_1 = clk ;
    }

    public void end1(int clk)
    {
        tmrEnd_1 = clk ;
    }

    public void start2(int clk)
    {
        tmrStart_2 = clk ;
    }

    public void end2(int clk)
    {
        tmrEnd_2 = clk ;
    }
}
