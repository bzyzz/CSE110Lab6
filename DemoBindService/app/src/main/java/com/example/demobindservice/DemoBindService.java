package com.example.demobindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DemoBindService extends Service {
    private final IBinder iBinder = new LocalService();

    public DemoBindService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class LocalService extends Binder {
        public DemoBindService getService(){
            return DemoBindService.this;
        }
    }

    public String getFirstMessage(){
        return "First Message";
    }
    public String getSecondMessage(){
        return "Second Message";
    }
}
