package co.codemaestro.punchclock_beta_v001;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.Chronometer;
import android.widget.TextView;



public class TimeDatabase extends AppCompatActivity {

    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        textView = findViewById(R.id.textView);
//        Chronometer chronometer = findViewById(R.id.chronometer);
//        long startTime = SystemClock.elapsedRealtime();
//        chronometer.start();
//        chronometer.setBase(startTime);
//        textView.setText(chronometer.getText());
//
////        if (chronometer == null) {
////            // If the start date is not defined, it's a new ViewModel so set it.
////            long startTime = SystemClock.elapsedRealtime();
////            chronometer.setBase(startTime);
////            textView.setText(chronometer.getText());
////        } else {
////
////    }
    }
}
