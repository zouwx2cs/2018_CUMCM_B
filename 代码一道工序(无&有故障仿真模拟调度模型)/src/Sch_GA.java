public class Sch_GA extends Scheduling {

    public Sch_GA(God god) {
        super(god);
    }

    @Override
    public RGV_STATE s() {
        return RGV_STATE.WAIT ;
    }

}
