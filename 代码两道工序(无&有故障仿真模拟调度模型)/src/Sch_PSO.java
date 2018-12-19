public class Sch_PSO extends Scheduling {

    public Sch_PSO(God god) {
        super(god);
    }

    @Override
    public RGV_STATE s() {
        return RGV_STATE.WAIT ;
    }

}
