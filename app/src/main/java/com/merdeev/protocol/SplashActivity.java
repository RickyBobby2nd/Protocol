package com.merdeev.protocol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;


public class SplashActivity extends AppCompatActivity {

    /** Данные из ресурсов */
    static int vol;
    static String freq;
    static int low;
    static int mid;
    static int high;

    static boolean init = true;
    static boolean vol_init = false;
    static boolean freq_init = false;
    static boolean low_init = false;
    static boolean mid_init = false;
    static boolean high_init = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Exchange();

        MainActivity.sPref = getPreferences(MODE_PRIVATE);
        MainActivity.editor = MainActivity.sPref.edit();

        vol = Integer.valueOf(MainActivity.sPref.getString(MainActivity.VOLUME, "50"));
        freq = MainActivity.sPref.getString(MainActivity.FREQUENCY, "87.0");
        low = Integer.valueOf(MainActivity.sPref.getString(MainActivity.LOWBAND, "50"));
        mid = Integer.valueOf(MainActivity.sPref.getString(MainActivity.MIDBAND, "50"));
        high = Integer.valueOf(MainActivity.sPref.getString(MainActivity.HIGHBAND, "50"));

        final Thread thread_timeout = new Thread() {
            @Override
            public void run() {
                super.run();


                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
        };
        thread_timeout.start();

        Thread thread_init = new Thread() {
            @Override
            public void run() {
                super.run();

                Exchange.set_percent(MainActivity.VOLUME, vol);
                while (!vol_init) {
                    if (!thread_timeout.isAlive()) return;
                }

                try {
                    Exchange.set_mhz(MainActivity.FREQUENCY, freq);
                } catch (NumberFormatException e) {
                }
                while (!freq_init) {
                    if (!thread_timeout.isAlive()) return;
                }

                Exchange.set_percent(MainActivity.LOWBAND, low);
                while (!low_init) {
                    if (!thread_timeout.isAlive()) return;
                }

                Exchange.set_percent(MainActivity.MIDBAND, mid);
                while (!mid_init) {
                    if (!thread_timeout.isAlive()) return;
                }

                Exchange.set_percent(MainActivity.HIGHBAND, high);
                while (!high_init) {
                    if (!thread_timeout.isAlive()) return;
                }
            }
        };
        thread_init.start();



        while (thread_init.isAlive() && thread_timeout.isAlive()) {}

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra(MainActivity.VOLUME, vol);
        intent.putExtra(MainActivity.FREQUENCY, freq);
        intent.putExtra(MainActivity.LOWBAND, low);
        intent.putExtra(MainActivity.MIDBAND, mid);
        intent.putExtra(MainActivity.HIGHBAND, high);

        startActivity(intent);

        init = false;

        finish();
    }
}
