public class Sch_SqeOrder extends Scheduling {

    public Sch_SqeOrder(God god) {
        super(god);
    }

    @Override
    public RGV_STATE s() {if (god.cncs.get(god.rgv.position*2).cnc_state == CNC_STATE.END)
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

        for (int i = 0; i < god.cncs.size(); ++i) {
            if (god.cncs.get(i).cnc_state == CNC_STATE.WAIT
                    || god.cncs.get(i).cnc_state == CNC_STATE.END) {
                aim = i/2 ;
                return RGV_STATE.MOVING ;
            }
        }


        return RGV_STATE.WAIT ;
    }

}
