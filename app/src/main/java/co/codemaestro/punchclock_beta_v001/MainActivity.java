package co.codemaestro.punchclock_beta_v001;

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
import android.os.SystemClock;
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
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeMain = findViewById(R.id.timer_main);
        mHandler = new Handler();


    }

    public void punchIn(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message,
                Toast.LENGTH_SHORT);
        timeOnClick = System.currentTimeMillis();
        mHandler.postDelayed(mRunnable, 1000L);
    }

    public void Save(View view) {
    }

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mStarted) {
                seconds = (System.currentTimeMillis() - timeOnClick) / 1000;
                timeMain.setText(String.format("%02d:%02d:%02d", seconds / 3600, seconds / 60, seconds % 60));
                mHandler.postDelayed(mRunnable, 1000L);
            }
        }
    };



    @Override
    protected void onStart() {
        super.onStart();
        mStarted = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mStarted = false;
        mHandler.removeCallbacks(mRunnable);


    }




//
//    public Runnable runny = new Runnable() {
//
//        public void run() {
//
//            longSeconds = SystemClock.elapsedRealtime() - timeOnClick;
//
//            // Converting millis to total seconds/minutes/hours
//            totalSeconds = (int) (longSeconds / 1000);
//
//            // Converting total seconds/minutes/hours into analog clock
//            seconds = totalSeconds % 60;
//            minutes = totalSeconds / 60 % 60;
//            hours = totalSeconds / 3600;
//
//            /* Starts the count in "Time" textView
//                Will put a zero in front of each number in "hours:minutes:seconds"
//                if it is less then 10, keeping the "00:00:00" format */
//            if (seconds <= 9 && minutes <= 9 && hours <= 9) {
//                timeMain.setText("0" + hours + ":0" + minutes + ":0" + seconds);
//
//            } else if (minutes <= 9 && hours <= 9) {
//                timeMain.setText("0" + hours + ":0" + minutes + ":" + seconds);
//
//            } else if (seconds <= 9 && hours <= 9) {
//                timeMain.setText("0" + hours + ":0" + minutes + ":" + seconds);
//
//            } else {
//                timeMain.setText(hours + ":" + minutes + ":" + seconds);
//            }
//
//            myHandler.postDelayed(this, 0);
//        }
//    };

}
