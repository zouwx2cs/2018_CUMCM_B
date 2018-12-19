public class fault {
    static int cnt = 1 ;
    public int no ;
    public Production p ;
    public int cnc_no ;
    public int start ;
    public int end ;

    public fault(Production p, int cnc_no, int start) {
        this.p = p;
        this.cnc_no = cnc_no;
        this.start = start;
        this.no = cnt++ ;
    }


}
