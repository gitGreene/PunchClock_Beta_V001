package co.codemaestro.punchclock_beta_v001;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    public Handler mHandler;
    public long timeOnClick;
    public Long seconds;
    public TextView timeMain;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String TIME_ON_CLOCK = "co.codemaestro.punchclock_beta_v001.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    private boolean mStarted = true;

    public ToggleButton startPause;
    private String currentTime;
    private SharedPreferences mPreferences;
    public SharedPreferences.Editor editor;
    public Timer timer1, timer2;

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            seconds = (System.currentTimeMillis() - timeOnClick) / 1000;
            timeMain.setText(String.format("%02d:%02d:%02d", seconds / 3600, seconds / 60, seconds % 60));
            mHandler.postDelayed(mRunnable, 1000L);
            mStarted = true;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating reference variables for View objects
        timeMain = findViewById(R.id.timer_main);
        mHandler = new Handler();
        startPause = findViewById(R.id.start_pause);
        timeMain.setText(R.string.timer_main);

        }

    public void StartPause(View view) {

        if (startPause.isChecked()) {
            Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
            toast.show();
            timeOnClick = System.currentTimeMillis();
            mRunnable.run();
        } else {
            mStarted = false;
            mHandler.removeCallbacks(mRunnable);
        }
    }



        //Saving current instance state
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(TIME_ON_CLOCK, seconds);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
//        mStarted = true;
    }


    //Need to save current seconds in mPreferences
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    //Need to restore current sharedPreferences
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");

        mHandler.removeCallbacks(mRunnable);
        mStarted = false;
    }


    public void punchIn(View view) {

    }

    public void Save(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, TimeDatabase.class);
        intent.putExtra(TIME_ON_CLOCK, seconds);
        startActivity(intent);

    }
}



//         mStarted = !mStarted;
//        if (mStarted) {
//            mHandler.removeCallbacks(mRunnable);
//        } else {
//            mRunnable.run();
//        }
//    }


//        timeOnClick = System.currentTimeMillis();
//        mRunnable.run();

//        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = mPreferences.edit();
//        editor.putLong("time",seconds);
//        editor.apply();