package com.merdeev.protocol;

import android.util.Log;

public class Exchange {

    static synchronized void incoming (String msg) {
        try {
            int i = msg.indexOf("=");
            if (i > 0) {
                String service = msg.substring(0, i);
                String data = msg.substring(i + 1);
                while (data.charAt(0) == '0') {
                    data = data.substring(1);
                }
                switch (service) {
                    case MainActivity.VOLUME:
                        if (SplashActivity.init) {
                            SplashActivity.vol_init = true;
                            SplashActivity.vol = Integer.valueOf(data);
                        }
                        else MainActivity.sbVolume.setProgress(Integer.valueOf(data));
                        break;

                    case MainActivity.FREQUENCY:
                        if (SplashActivity.init) {
                            SplashActivity.freq_init = true;
                            SplashActivity.freq = data;
                        }
                        else MainActivity.tvFrequency.setText(data);
                        break;

                    case MainActivity.LOWBAND:
                        if (SplashActivity.init) {
                            SplashActivity.low_init = true;
                            SplashActivity.low = Integer.valueOf(data);
                        }
                        else MainActivity.sbLowBand.setProgress(Integer.valueOf(data));
                        break;

                    case MainActivity.MIDBAND:
                        if (SplashActivity.init) {
                            SplashActivity.mid_init = true;
                            SplashActivity.mid = Integer.valueOf(data);
                        }
                        else MainActivity.sbMidBand.setProgress(Integer.valueOf(data));
                        break;

                    case MainActivity.HIGHBAND:
                        if (SplashActivity.init) {
                            SplashActivity.high_init = true;
                            SplashActivity.high = Integer.valueOf(data);
                        }
                        else MainActivity.sbHighBand.setProgress(Integer.valueOf(data));
                        break;
                }
            }

        } catch (NumberFormatException e) {}
    }

    static void set_percent(String service, int data) {
        String d = String.valueOf(data);
        if (data>=0 && data<=9) d = "00".concat(d);
        else if (data>=10 && data<=99) d = "0".concat(d);
        d = service + "=" + d;
        Log.d("Debug", System.currentTimeMillis() + " " + d);
    }

    static void set_mhz(String service, float data) {
        String d = String.valueOf(data);
        int i = d.indexOf(".");
        for (int j=0; j<3-i; j++) d = "0".concat(d);
        d = service + "=" + d.substring(0, 5);
        Log.d("Debug", System.currentTimeMillis() + " " + d);
    }

    static void set_mhz(String service, String dir) {
        String d = service + "=" + dir;
        Log.d("Debug", System.currentTimeMillis() + " " + d);
    }
}
