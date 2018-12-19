public class Sch_ACO extends Scheduling {

    public Sch_ACO(God god) {
        super(god);
    }

    @Override
    public RGV_STATE s() {
        return RGV_STATE.WAIT ;
    }

}
