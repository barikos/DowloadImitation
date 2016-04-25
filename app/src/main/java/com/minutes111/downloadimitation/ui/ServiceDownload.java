package com.minutes111.downloadimitation.ui;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.minutes111.downloadimitation.Const;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServiceDownload extends Service {

    ExecutorService mExecutorService;

    public ServiceDownload() {
    }

    @Override
    public void onCreate() {
        mExecutorService = Executors.newFixedThreadPool(1);
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mExecutorService.execute(new MyRun(startId));
        return super.onStartCommand(intent, flags, startId);
    }

    private class MyRun implements Runnable {

        int startId;

        public MyRun(int startId) {
            this.startId = startId;
        }

        @Override
        public void run() {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < Const.IMAGES_BOOK.length; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    arrayList.add(Const.IMAGES_BOOK[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Intent intent = new Intent(Const.BROADCAST_ACTION).putIntegerArrayListExtra(Const.ATTR_IMAGES, arrayList);
            sendBroadcast(intent);
            stopSelfResult(startId);
        }
    }
}
