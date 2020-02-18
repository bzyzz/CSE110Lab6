package com.example.demobindservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.IBinder;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DemoBindService demoBindService;
    private boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, DemoBindService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DemoBindService.LocalService localService = (DemoBindService.LocalService)service;
            demoBindService = localService.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void setFirstMessage(View view){
        TextView textView = (TextView)findViewById(R.id.aloha);
        textView.setText(demoBindService.getFirstMessage());
    }

    public void setSecondMessage(View view){
        TextView textView = (TextView)findViewById(R.id.aloha);
        textView.setText(demoBindService.getSecondMessage());
    }

    @Override
    protected void onDestroy(){
        if (isBound){
            unbindService(serviceConnection);
            isBound = false;
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
