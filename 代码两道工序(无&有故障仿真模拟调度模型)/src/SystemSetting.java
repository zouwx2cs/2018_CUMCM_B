public class SystemSetting
{
    public int tmrRgvMove1 ;
    public int tmrRgvMove2 ;
    public int tmrRgvMove3 ;
    public int tmrCncProcessing ;
    public int tmrCncProcessing1 ;
    public int tmrCncProcessing2 ;
    public int tmrRgvCnc_Odd ;
    public int tmrRgvCnc_even ;
    public int tmrWashing ;
    public double rateFault ;
    public int tmrFaultFix ;

    public SystemSetting(int tmrRgvMove1, int tmrRgvMove2, int tmrRgvMove3,
                         int tmrCncProcessing, int tmrCncProcessing1, int tmrCncProcessing2,
                         int tmrRgvCnc_Odd, int tmrRgvCnc_even, int tmrWashing,
                         double rateFault, int tmrFaultFix) {
        this.tmrRgvMove1 = tmrRgvMove1;
        this.tmrRgvMove2 = tmrRgvMove2;
        this.tmrRgvMove3 = tmrRgvMove3;
        this.tmrCncProcessing = tmrCncProcessing;
        this.tmrCncProcessing1 = tmrCncProcessing1;
        this.tmrCncProcessing2 = tmrCncProcessing2;
        this.tmrRgvCnc_Odd = tmrRgvCnc_Odd;
        this.tmrRgvCnc_even = tmrRgvCnc_even;
        this.tmrWashing = tmrWashing;
        this.rateFault = rateFault ;
        this.tmrFaultFix = tmrFaultFix ;
    }


}
