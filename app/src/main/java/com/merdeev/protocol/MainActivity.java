package com.merdeev.protocol;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, SeekBar.OnSeekBarChangeListener {

    static SeekBar sbVolume;
    static  TextView tvFrequency;
    private Button btnSearchUp;
    private Button btnSearchDown;
    private Button btnPresave1;
    private Button btnPresave2;
    private Button btnPresave3;
    private Button btnPresave4;
    private Button btnPresave5;
    private Button btnPresave6;
    static SeekBar sbLowBand;
    static SeekBar sbMidBand;
    static SeekBar sbHighBand;

    static final String VOLUME = "v";
    static final String FREQUENCY = "f";
    private final String PRESAVE1 = "p1";
    private final String PRESAVE2 = "p2";
    private final String PRESAVE3 = "p3";
    private final String PRESAVE4 = "p4";
    private final String PRESAVE5 = "p5";
    private final String PRESAVE6 = "p6";
    static final String LOWBAND = "l";
    static final String MIDBAND = "m";
    static final String HIGHBAND = "h";
    static final String UP = "u";
    static final String DOWN = "d";

    static SharedPreferences sPref;
    static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String presave1 = sPref.getString(PRESAVE1, "---.-");
        String presave2 = sPref.getString(PRESAVE2, "---.-");
        String presave3 = sPref.getString(PRESAVE3, "---.-");
        String presave4 = sPref.getString(PRESAVE4, "---.-");
        String presave5 = sPref.getString(PRESAVE5, "---.-");
        String presave6 = sPref.getString(PRESAVE6, "---.-");
        Intent intent = getIntent();

        int volume = intent.getIntExtra(VOLUME, 50);
        String frequency = intent.getStringExtra(FREQUENCY);
        int lowband = intent.getIntExtra(LOWBAND, 50);
        int midband = intent.getIntExtra(MIDBAND, 50);
        int highband = intent.getIntExtra(HIGHBAND, 50);


        sbVolume = (SeekBar) findViewById(R.id.sbVolume);
        tvFrequency = (TextView) findViewById(R.id.tvFrequency);
        btnSearchDown = (Button) findViewById(R.id.btnSearchDown);
        btnSearchUp = (Button) findViewById(R.id.btnSearchUp);
        btnPresave1 = (Button) findViewById(R.id.btnPresave1);
        btnPresave2 = (Button) findViewById(R.id.btnPresave2);
        btnPresave3 = (Button) findViewById(R.id.btnPresave3);
        btnPresave4 = (Button) findViewById(R.id.btnPresave4);
        btnPresave5 = (Button) findViewById(R.id.btnPresave5);
        btnPresave6 = (Button) findViewById(R.id.btnPresave6);
        sbLowBand = (SeekBar) findViewById(R.id.sbLowBand);
        sbMidBand = (SeekBar) findViewById(R.id.sbMidBand);
        sbHighBand = (SeekBar) findViewById(R.id.sbHighBand);

        sbVolume.setProgress(volume);
        tvFrequency.setText(frequency);
        btnPresave1.setText(presave1);
        btnPresave2.setText(presave2);
        btnPresave3.setText(presave3);
        btnPresave4.setText(presave4);
        btnPresave5.setText(presave5);
        btnPresave6.setText(presave6);
        sbLowBand.setProgress(lowband);
        sbMidBand.setProgress(midband);
        sbHighBand.setProgress(highband);

        sbVolume.setOnSeekBarChangeListener(this);
        btnSearchDown.setOnClickListener(this);
        btnSearchUp.setOnClickListener(this);
        btnPresave1.setOnClickListener(this);
        btnPresave2.setOnClickListener(this);
        btnPresave3.setOnClickListener(this);
        btnPresave4.setOnClickListener(this);
        btnPresave5.setOnClickListener(this);
        btnPresave6.setOnClickListener(this);
        sbLowBand.setOnSeekBarChangeListener(this);
        sbMidBand.setOnSeekBarChangeListener(this);
        sbHighBand.setOnSeekBarChangeListener(this);

        btnSearchDown.setOnLongClickListener(this);
        btnSearchUp.setOnLongClickListener(this);
        btnPresave1.setOnLongClickListener(this);
        btnPresave2.setOnLongClickListener(this);
        btnPresave3.setOnLongClickListener(this);
        btnPresave4.setOnLongClickListener(this);
        btnPresave5.setOnLongClickListener(this);
        btnPresave6.setOnLongClickListener(this);
    }


    @Override
    public void onClick(View view) {
        try {
            float f;
            String s;
            switch (view.getId()) {
                case R.id.btnSearchDown:
                    f = Float.valueOf(String.valueOf(tvFrequency.getText()))*10.0f;
                    if (f > 870.0f) {
                        f = Math.round(f-1)/10.0f;
                        s = String.valueOf(f);
                        tvFrequency.setText(s);
                        editor.putString(FREQUENCY, s);
                        editor.commit();
                        Exchange.set_mhz(FREQUENCY, f);
                    }
                    break;

                case R.id.btnSearchUp:
                    f = Float.valueOf(tvFrequency.getText().toString())*10.0f;
                    if (f < 1080.0f) {
                        f = Math.round(f+1)/10.0f;
                        s = String.valueOf(f);
                        tvFrequency.setText(s);
                        editor.putString(FREQUENCY, s);
                        editor.commit();
                        Exchange.set_mhz(FREQUENCY, f);
                    }
                    break;

                case R.id.btnPresave1:
                    Exchange.set_mhz(FREQUENCY, Float.valueOf(String.valueOf(btnPresave1.getText())));
                    break;

                case R.id.btnPresave2:
                    Exchange.set_mhz(FREQUENCY, Float.valueOf(String.valueOf(btnPresave2.getText())));
                    break;

                case R.id.btnPresave3:
                    Exchange.set_mhz(FREQUENCY, Float.valueOf(String.valueOf(btnPresave3.getText())));
                    break;

                case R.id.btnPresave4:
                    Exchange.set_mhz(FREQUENCY, Float.valueOf(String.valueOf(btnPresave4.getText())));
                    break;

                case R.id.btnPresave5:
                    Exchange.set_mhz(FREQUENCY, Float.valueOf(String.valueOf(btnPresave5.getText())));
                    break;

                case R.id.btnPresave6:
                    Exchange.set_mhz(FREQUENCY, Float.valueOf(String.valueOf(btnPresave6.getText())));
                    break;
            }
        } catch (NumberFormatException e) {}
    }

    @Override
    public boolean onLongClick(View view) {
        String s;
        switch (view.getId()) {
            case R.id.btnSearchDown:
                Exchange.set_mhz(FREQUENCY, DOWN);
                break;

            case R.id.btnSearchUp:
                Exchange.set_mhz(FREQUENCY, UP);
                break;

            case R.id.btnPresave1:
                s = String.valueOf(tvFrequency.getText());
                btnPresave1.setText(s);
                editor.putString(PRESAVE1, s);
                editor.commit();
                break;

            case R.id.btnPresave2:
                s = String.valueOf(tvFrequency.getText());
                btnPresave2.setText(s);
                editor.putString(PRESAVE2, s);
                editor.commit();
                break;

            case R.id.btnPresave3:
                s = String.valueOf(tvFrequency.getText());
                btnPresave3.setText(s);
                editor.putString(PRESAVE3, s);
                editor.commit();
                break;

            case R.id.btnPresave4:
                s = String.valueOf(tvFrequency.getText());
                btnPresave4.setText(s);
                editor.putString(PRESAVE4, s);
                editor.commit();
                break;

            case R.id.btnPresave5:
                s = String.valueOf(tvFrequency.getText());
                btnPresave5.setText(s);
                editor.putString(PRESAVE5, s);
                editor.commit();
                break;

            case R.id.btnPresave6:
                s = String.valueOf(tvFrequency.getText());
                btnPresave6.setText(s);
                editor.putString(PRESAVE6, s);
                editor.commit();
                break;
        }
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int i;
        String s;
        switch (seekBar.getId()) {
            case R.id.sbVolume:
                i = sbVolume.getProgress();
                s = String.valueOf(i);
                editor.putString(VOLUME, s);
                editor.commit();
                Exchange.set_percent(VOLUME, i);
                break;

            case R.id.sbLowBand:
                i = sbLowBand.getProgress();
                s = String.valueOf(i);
                editor.putString(LOWBAND, s);
                editor.commit();
                Exchange.set_percent(LOWBAND, i);
                break;

            case R.id.sbMidBand:
                i = sbMidBand.getProgress();
                s = String.valueOf(i);
                editor.putString(MIDBAND, s);
                editor.commit();
                Exchange.set_percent(MIDBAND, i);
                break;

            case R.id.sbHighBand:
                i = sbHighBand.getProgress();
                s = String.valueOf(i);
                editor.putString(HIGHBAND, s);
                editor.commit();
                Exchange.set_percent(HIGHBAND, i);
                break;
        }
    }
}
