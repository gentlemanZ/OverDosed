package tianyangzhong.com.overdosed;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Messenger;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by tiany on 2017/8/15.
 */

public class ClockCheckService extends Service {

    private static final String TAG = ClockCheckService.class.getSimpleName();
    private MediaPlayer mPlayer;
    public Messenger mMessenger = new Messenger(new ClockCheckHandler(this));

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
        return mMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        mPlayer.release();
    }

    @Override
    public void onCreate() {
        Log.d(TAG,"onCreate");
        mPlayer = MediaPlayer.create(this, R.raw.jingle);
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {

        Notification.Builder notificationBuilder = new Notification.Builder(this);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        Notification notification = notificationBuilder.build();
        startForeground(12, notification);

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopSelf();
                stopForeground(true);
            }
        });

        return Service.START_NOT_STICKY;
    }

    //Client Methods
    public boolean isPlaying(){
        return mPlayer.isPlaying();
    }
    public void play(){
        mPlayer.start();
    }

    public void pause(){
        mPlayer.pause();
    }
}
