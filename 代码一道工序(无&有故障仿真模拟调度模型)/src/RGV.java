public class RGV
{
    God god ;
    int tmr ;
    int position ;
    RGV_STATE rgv_state ;
    int aim ;
    Production pDown = null ;

    public RGV(God god) {
        this.god = god ;
        this.tmr = 0 ;
        this.position = 0 ;
        this.rgv_state = RGV_STATE.WAIT ;
        this.aim = 0 ;
    }



    public void clock()
    {
        if (rgv_state == RGV_STATE.UP
                || rgv_state == RGV_STATE.DOWN
                || rgv_state == RGV_STATE.UP_DOWN
                || rgv_state == RGV_STATE.MOVING
                || rgv_state == RGV_STATE.WASHING)
            --tmr ;

        switch (rgv_state) {
            case UP:
                if (0 == tmr) {
                    rgv_state = RGV_STATE.WAIT;
                    P("上料完毕") ;
                    s() ;
                }
                break;
            case DOWN:
                if (0 == tmr) {
                    rgv_state = RGV_STATE.WASHING;
                    tmr = god.setting.tmrWashing ;
                    P("下料完毕，开始对产品" + pDown.no + "进行清洗") ;
                }
                break;
            case UP_DOWN:
                if (0 == tmr) {
                    rgv_state = RGV_STATE.WASHING;
                    tmr = god.setting.tmrWashing ;
                    P("上下料完毕，开始对产品" + pDown.no + "进行清洗") ;
                }
                break;
            case WAIT:
                s() ;
                break;
            case MOVING:
                if (0 == tmr) {
                    position = aim;
                    P("已经移动到位置" + position) ;
                    s() ;
                }
                break;
            case WASHING:
                if (0 == tmr) {
                    rgv_state = RGV_STATE.WAIT;
                    P("产品" + pDown.no + "清洗完毕") ;
                    god.productions_end.add(pDown) ;
                    s() ;
                }
                break;
            default:
                break;
        }

    }

    private void s() {
        rgv_state = god.scheduling.s();
        aim = god.scheduling.getAim() ;
        switch (rgv_state) {
            case UP_DOWN:
                pDown = god.cncs.get(aim).updown(new Production());
                tmr = god.cncs.get(aim).tmr;
                P("正在对CNC_" + god.cncs.get(aim).getNo() + "进行上下料") ;
                break;
            case UP:
                god.cncs.get(aim).up(new Production());
                tmr = god.cncs.get(aim).tmr;
                P("正在对CNC_" + god.cncs.get(aim).getNo()  + "进行上料") ;
                break;
            case DOWN:
                pDown = god.cncs.get(aim).down();
                tmr = god.cncs.get(aim).tmr;
                P("正在对CNC_" + god.cncs.get(aim).getNo()  + "进行下料" + pDown) ;
                break;
            case MOVING:
                switch (Math.abs(position - aim)) {
                    case 1:
                        tmr = god.setting.tmrRgvMove1;
                        P("正从位置" + position + "向位置" + aim + "移动，此次移动1个单位") ;
                        break;
                    case 2:
                        tmr = god.setting.tmrRgvMove2;
                        P("正从位置" + position + "向位置" + aim + "移动，此次移动2个单位") ;
                        break;
                    case 3:
                        tmr = god.setting.tmrRgvMove3;
                        P("正从位置" + position + "向位置" + aim + "移动，此次移动3个单位") ;
                        break;
                    default:
                        break;
                }
                break;
            case WASHING:
                tmr = god.setting.tmrWashing;
                P("正在对熟料" + pDown.no + "进行清洗" + pDown) ;
                break;
            case WAIT:
                //P("进入闲置状态") ;
                break;
            default:
                break;

        }
    }

    private String P(String info) {
        String str = "RGV: " + info ;
        return god.P(str) ;
    }
}
