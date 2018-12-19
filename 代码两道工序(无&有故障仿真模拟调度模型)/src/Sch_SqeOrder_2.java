public class Sch_SqeOrder_2 extends Scheduling {

    public Sch_SqeOrder_2(God god) {
        super(god);
    }

    @Override
    public RGV_STATE s() {
        if (god.cncs.get(god.rgv.position*2).cnc_state == CNC_STATE.END)
        {
            aim = god.rgv.position*2 ;
            return RGV_STATE.UP_DOWN ;
        }

        if (god.cncs.get(god.rgv.position*2+1).cnc_state == CNC_STATE.END)
        {
            aim = god.rgv.position*2+1 ;
            return RGV_STATE.UP_DOWN ;
        }

        if (god.cncs.get(god.rgv.position*2).cnc_state == CNC_STATE.WAIT)
        {
            aim = god.rgv.position*2 ;
            return RGV_STATE.UP ;
        }

        if (god.cncs.get(god.rgv.position*2+1).cnc_state == CNC_STATE.WAIT)
        {
            aim = god.rgv.position*2+1 ;
            return RGV_STATE.UP ;
        }

        int t[] = {0, god.setting.tmrRgvMove1, god.setting.tmrRgvMove2, god.setting.tmrRgvMove3} ;

        for (int i = 0; i < god.cncs.size(); ++i) {
            if (god.cncs.get(i).cnc_state == CNC_STATE.WAIT
                    || god.cncs.get(i).cnc_state == CNC_STATE.END
                    || (god.cncs.get(i).cnc_state == CNC_STATE.PROCESSING && t[Math.abs(i/2 - god.rgv.position)] >= god.cncs.get(i).tmr)) {
                aim = i/2 ;
                return RGV_STATE.MOVING ;
            }
        }


        return RGV_STATE.WAIT ;
    }

}
