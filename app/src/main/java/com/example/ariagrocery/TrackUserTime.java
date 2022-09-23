package com.example.ariagrocery;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class TrackUserTime extends Service {

    private int seconds=0;
    private boolean shouldfinish;
    private GrocerItem item;

    public void setItem(GrocerItem item) {
        this.item = item;
    }

    private IBinder binder=new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        trackTime();
        return binder;
    }

    public class LocalBinder extends Binder{
        TrackUserTime getService(){
            return TrackUserTime.this;
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shouldfinish=true;
        int mins=seconds/60;
        if(mins>0){
            if(item!=null){
                Utils.ChangeUserPoint(this,item,mins);

            }

        }
    }

    private void trackTime(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (!shouldfinish){
                    try {
                        Thread.sleep(1000);
                        seconds++;

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }


                }
            }
        });
        thread.start();
    }
}
