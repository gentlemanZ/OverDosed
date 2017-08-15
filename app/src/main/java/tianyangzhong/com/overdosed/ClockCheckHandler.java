package tianyangzhong.com.overdosed;

import android.os.Handler;

/**
 * Created by tiany on 2017/8/15.
 */

public class ClockCheckHandler extends Handler {
    private ClockCheckService mClockCheckService;

    public ClockCheckHandler(ClockCheckService clockCheckService){

        mClockCheckService = clockCheckService;
    }
}
