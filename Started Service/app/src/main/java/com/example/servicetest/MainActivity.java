package com.example.servicetest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView t1;
    private SharedPreferences sp;
    private boolean b = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        Button b1 = findViewById(R.id.startService);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                starter(view);
//            }
//        });

        starter();

        sp = getSharedPreferences("demo", MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString("demo1", "demo2");
        spe.apply();

        DSAsyncTask at = new DSAsyncTask();
        at.execute("10");

        Button b3 = findViewById(R.id.buttonShow);
        t1 = findViewById(R.id.textView);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b) {
                    showsp();
                }
            }
        });

//        Button b2 = findViewById(R.id.stopService);
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                stopper(view);
//            }
//        });
    }

    public void showsp(){
        String str =  sp.getString("demo1", "None");
        t1.setText(str);
    }

    private class DSAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strs) {
            //int target = Integer.parseInt(strs[0]);
            publishProgress(Integer.toString(1));
            try {
                Thread.sleep(10000);
                stopper();
                b = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... strs) {
            //textResult.setText(strs[0]);
        }

        @Override
        protected void onPreExecute(){
        }

        @Override
        protected void onPostExecute(String s){
        }
    }

    public void starter(View view){
        Intent intent = new Intent(MainActivity.this, DemoService.class);
        startService(intent);
    }

    public void starter(){
        Intent intent = new Intent(MainActivity.this, DemoService.class);
        startService(intent);
    }

    public void stopper(View view){
        Intent intent = new Intent(MainActivity.this, DemoService.class);
        stopService(intent);
    }

    public void stopper(){
        Intent intent = new Intent(MainActivity.this, DemoService.class);
        stopService(intent);
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
