public class Sch_SCAN extends Scheduling {

    public Sch_SCAN(God god) {
        super(god);
    }

    @Override
    public RGV_STATE s() {
        return RGV_STATE.WAIT ;
    }

}
