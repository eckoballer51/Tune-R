package com.greenmist.watchapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import butterknife.InjectView;
import com.greenmist.watchapp.view.CircleProgressView;

public class MainActivity extends Activity {

    @InjectView(R.id.pitch_bar)
    CircleProgressView progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                progressBar = (CircleProgressView) stub.findViewById(R.id.pitch_bar);

                new Thread() {
                    public void run() {
                        float p = 0f;
                        while (p <= 101f) {
                            progressBar.setProgress((int) p);
                            p += 0.00003;
                        }
                    }
                }.start();
            }
        });
    }

}
