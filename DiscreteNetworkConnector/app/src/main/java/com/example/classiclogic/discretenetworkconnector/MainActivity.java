package com.example.classiclogic.discretenetworkconnector;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    final String LOGTAG = "DNC_LOG";

    Switch appSwitch;
    RadioGroup rg_ConnType;

    Timer timer;
    Button dummyButton;

    private MyLocalService mLocalService;
    private ServiceConnection mConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appSwitch = (Switch) findViewById(R.id.switch1);
        rg_ConnType = (RadioGroup) findViewById(R.id.rg_conntype);

        dummyButton = (Button) findViewById(R.id.dummybutton1);
        timer = new Timer(true);
    }


    @Override
    protected void onStart()    {
        super.onStart();

        if( appSwitch.isChecked() ) {
            enableRadioGroup(rg_ConnType);
        } else  {
            disableRadioGroup(rg_ConnType);
        }

        appSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked ) {
                    Log.v(LOGTAG, " Checked! ");
                    enableRadioGroup(rg_ConnType);
                }
                else {
                    Log.v(LOGTAG, " Unchecked! ");
                    disableRadioGroup(rg_ConnType);
                }

            }
        });

        dummyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.schedule(new MyTimerTask(), 10000, 20000);
            }
        });
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        timer.cancel();
        timer.purge();
    }

    private void disableRadioGroup(RadioGroup rg)   {
        for(int i = 0; i < rg.getChildCount(); i++) {
            rg.getChildAt(i).setEnabled(false);
        }
    }

    private void enableRadioGroup(RadioGroup rg)    {
        for(int i = 0; i < rg.getChildCount(); i++) {
            rg.getChildAt(i).setEnabled(true);
        }
    }


    mConnection = new ServiceConnection()   {
        public void onServiceConnected()    {

        }
    };
}

class MyTimerTask extends TimerTask {

    final String LOGTAG = "DNC_LOG";



    @Override
    public void run() {
        //task to be run should be specified here

        Log.v(LOGTAG, " Inside run() of MyTimerTask");
    }
}