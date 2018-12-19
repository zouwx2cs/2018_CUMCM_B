public class Production {
    static int num = 1 ;
    int no ;
    int tmrStart ;
    int tmrEnd ;
    Production_STATE production_state ;
    public int ONE_CNC_No = -1 ;

    public Production() {
        production_state = Production_STATE.UN ;
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
}
