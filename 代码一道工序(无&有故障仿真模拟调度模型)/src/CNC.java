public class CNC
{
    God god ;
    CNC_MODE mode ;
    fault fault ;
    int tmr ;
    public CNC_STATE cnc_state ;
    private int no ;
    private Production production = null ;

    private String P(String info) {
        String str = "CNC_" + getNo() + ": " + info ;
        return god.P(str) ;
    }

    public int getNo() {
        return no+1 ;
    }

    public CNC(God god, CNC_MODE mode, int no) {
        this.god = god;
        this.mode = mode;
        this.no = no;
        tmr = 0 ;
        cnc_state = CNC_STATE.WAIT ;
        fault = null ;
    }

    public void process()
    {
        cnc_state = CNC_STATE.PROCESSING ;
        if (mode == CNC_MODE.ONE)
            tmr = god.setting.tmrCncProcessing ;
        else if (mode == CNC_MODE.TWO_1)
            tmr = god.setting.tmrCncProcessing1 ;
        else
            tmr = god.setting.tmrCncProcessing2 ;

        if (Math.random() < god.setting.rateFault) {
            fault() ;
        }
    }

    private void fault() {
        fault = new fault(production, getNo(), god.clk) ;
        production = null ;
        cnc_state = CNC_STATE.FAULT ;
        tmr = god.setting.tmrFaultFix ;
        P("出现故障，产品" + fault.p.no + "报废") ;
    }

    public Production down()
    {
        P("产品" + production.no + "开始下料") ;
        production.tmrEnd = god.clk ;
        Production pDown = production ;
        production = null ;
        cnc_state = CNC_STATE.DOWN ;
        if (no%2 == 0)  // 0 2 4 6
            tmr = god.setting.tmrRgvCnc_Odd ;
        else            // 1 3 5 7
            tmr = god.setting.tmrRgvCnc_even ;
        return pDown ;
    }

    public void up(Production p)
    {
        P("产品" + p.no + "开始上料") ;
        production = p ;
        p.tmrStart = god.clk ;
        p.ONE_CNC_No = getNo() ;
        cnc_state = CNC_STATE.UP ;
        if (no%2 == 0)
            tmr = god.setting.tmrRgvCnc_Odd ;
        else
            tmr = god.setting.tmrRgvCnc_even ;
    }

    public Production updown(Production pUp)
    {
        P("产品" + production.no + "开始下料， 同时产品" + pUp.no + "开始上料") ;
        production.tmrEnd = god.clk ;
        Production pDown = production ;
        production = pUp ;
        production.tmrStart = god.clk ;
        production.ONE_CNC_No = getNo() ;
        cnc_state = CNC_STATE.UPDOWN ;
        if (no%2 == 0)
            tmr = god.setting.tmrRgvCnc_Odd ;
        else
            tmr = god.setting.tmrRgvCnc_even ;

        return pDown ;
    }

    public void clock()
    {
        if (cnc_state == CNC_STATE.PROCESSING
                || cnc_state == CNC_STATE.UP
                || cnc_state == CNC_STATE.DOWN
                || cnc_state == CNC_STATE.UPDOWN
                || cnc_state == CNC_STATE.FAULT)
            --tmr ;

        switch (cnc_state) { // 动作
            case FAULT:
                if (0 == tmr) {
                    cnc_state = CNC_STATE.WAIT;
                    fault.end = god.clk ;
                    god.productions_fault.add(fault) ;
                    fault = null ;
                    production = null ;
                    P("故障恢复，重新进入等待状态") ;
                }
                break ;
            case WAIT:
                break;
            case UP:
                if (0 == tmr)
                    process();
                break ;
            case DOWN:
                if (0 == tmr) {
                    cnc_state = CNC_STATE.WAIT;
                    production = null ;
                }
                break ;
            case UPDOWN:
                if (0 == tmr)
                    process();
                break ;
            case PROCESSING:
                if (0 == tmr)
                {
                    cnc_state = CNC_STATE.END ;
                }
                break;
            case END:
                break;
            default:
                break;
        }

    }
}
