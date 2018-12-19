import java.util.ArrayList;
import java.util.List;

public abstract class Scheduling {
    God god ;
    int aim ;
    List<CNC> initCNCS(List<CNC_MODE> modes)
    {
        List<CNC> cncs = new ArrayList<CNC>() ;
        for (int i = 0; i < 8; ++i)
        {
            cncs.add(new CNC(god, CNC_MODE.ONE, i)) ;
        }
        return cncs ;
    }

    public Scheduling(God god) {
        this.god = god;
    }

    abstract public RGV_STATE s() ;

    public int getAim() {
        return this.aim ;
    }
}
