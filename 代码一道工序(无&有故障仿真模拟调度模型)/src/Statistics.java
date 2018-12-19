import java.io.*;
import java.util.ArrayList ;
import java.util.List ;

public class Statistics {
    public int tmrRunnings[] = {0, 0, 0, 0, 0, 0, 0, 0} ;
    public int tmrTotals[] = {0, 0, 0, 0, 0, 0, 0, 0} ;
    public List<List<CNC_STATE>> cnc_statess ;
    public List<RGV_STATE> rgv_states ;

    public Statistics() {
        rgv_states = new ArrayList<RGV_STATE>() ;
        cnc_statess = new ArrayList<List<CNC_STATE>>() ;
    }

    public double getSysEfficiency() {
        int running = 0 ;
        int total = 0 ;

        for (int in: tmrRunnings)
            running += in ;
        for (int in: tmrTotals)
            total += in ;

        return (double)running/(double)total ;
    }

    public double getSingleEfficiency(int index) {
        return (double)tmrRunnings[index]/(double)tmrTotals[index] ;
    }

    public void output(String filename) {
        try {
            //1、打开流
            Writer w=new FileWriter("./" + filename);
            //2、写入内容
            for (int i = 0; i < this.cnc_statess.size(); ++i) {
                for (int j = 0; j < 8; ++j)
                {
                    w.write(cnc_statess.get(i).get(j) + "\t") ;
                }
                w.write("\n") ;
            }
            //3、关闭流
            w.close();
        } catch (IOException e) {
            System.out.println("文件写入错误：" + e.getMessage());
        }
    }

}
