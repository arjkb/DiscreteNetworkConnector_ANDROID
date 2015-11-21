package com.example.classiclogic.discretenetworkconnector;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.util.Log;

public class MainActivity extends Activity {

    final String LOGTAG = "DNC_LOG";

    Switch appSwitch;
    RadioGroup rg_ConnType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appSwitch = (Switch) findViewById(R.id.switch1);
        rg_ConnType = (RadioGroup) findViewById(R.id.rg_conntype);


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
}
