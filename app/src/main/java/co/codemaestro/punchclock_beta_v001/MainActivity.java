package co.codemaestro.punchclock_beta_v001;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int totalSeconds = 0;
    public Handler mHandler;
    public long longSeconds, timeOnClick; // longSeconds is the milliseconds from sysClock, timeOnClick helps us bring the timer up to date after app termination
    public long seconds = 0, minutes = 0, hours = 0; // integers for listing the time on the editText widgets, totalSeconds is saved on termination
    public int clockedTime, clockedSeconds, clockedMinutes, clockedHour;
    public EditText timeSeconds, timeClockOut; // The editText widgets for the three timers
    public TextView timeMain;
    private boolean mStarted;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String TIME_ON_CLOCK = "co.codemaestro.punchclock_beta_v001.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    private SharedPreferences mPreferences;



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
        Log.d(LOG_TAG, "onCreate");

        timeMain = findViewById(R.id.timer_main);
        mHandler = new Handler();
        String sharedPrefFile =
                "co.codemaestro.punchclock_beta_v001";
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

    }

    //Saving current instance state
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("reply_visible", true);
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
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.apply();
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
        mStarted = false;
        mHandler.removeCallbacks(mRunnable);
    }


    public void punchIn(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message,
                Toast.LENGTH_SHORT);
        toast.show();
        timeOnClick = System.currentTimeMillis();
        mRunnable.run();


    }

    public void Save(View view) {
        Intent intent = new Intent(this, TimeDatabase.class);
        intent.putExtra(TIME_ON_CLOCK, seconds);
        startActivity(intent);
    }


}
