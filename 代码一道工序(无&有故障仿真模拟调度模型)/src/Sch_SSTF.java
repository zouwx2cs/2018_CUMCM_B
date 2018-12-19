public class Sch_SSTF extends Scheduling {

    public Sch_SSTF(God god) {
        super(god);
    }

    @Override
    public RGV_STATE s() {
        return RGV_STATE.WAIT ;
    }

}
