public class Sch_FCFS extends Scheduling {

    public Sch_FCFS(God god) {
        super(god);
    }

    @Override
    public RGV_STATE s() {
        return RGV_STATE.WAIT ;
    }

}
