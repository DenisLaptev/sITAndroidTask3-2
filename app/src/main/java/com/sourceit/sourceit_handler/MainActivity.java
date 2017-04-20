package com.sourceit.sourceit_handler;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int ID = 1;
    int count = 0;

    int[] colors = new int[]{
            Color.RED,
            Color.YELLOW,
            Color.GREEN,
            Color.BLUE
    };

    TextView tv;

    boolean isIncrease = true;

    Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == ID) {

                if (isIncrease == true) {

                    getWindow().getDecorView().setBackgroundColor(colors[count]);
                    tv.setText(String.valueOf(count + 1));
                    count++;
                    if (count == colors.length) {
                        isIncrease = false;
                        handler.sendEmptyMessage(ID);
                    } else {

                        handler.sendEmptyMessageDelayed(ID, 1000);
                    }

                } else {

                    count--;
                    getWindow().getDecorView().setBackgroundColor(colors[count]);
                    tv.setText(String.valueOf(count + 1));

                    if (count == 0) {
                        isIncrease = true;
                        handler.sendEmptyMessage(ID);
                    } else {
                        handler.sendEmptyMessageDelayed(ID, 1000);
                    }
                }
            }
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessage(ID);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(ID);
    }

}
