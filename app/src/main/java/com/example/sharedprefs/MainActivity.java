package com.example.sharedprefs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sharedprefs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    private int count = 0;
    private int mColor;
    SharedPreferences preferences;
    String file_shared = "com.example.sharedprefs.prefs";
    private final String COUNT_KEY = "count";
    private final String COLOR_KEY = "color";

    @Override
    protected void onPause() {
        super.onPause();

        preferences.edit().putInt(COUNT_KEY, count)
                .putInt(COLOR_KEY, mColor)
                .apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        preferences = getSharedPreferences(file_shared, MODE_PRIVATE);

        if (preferences != null) {
            mColor = preferences.getInt(COLOR_KEY, R.color.default_background);
            count = preferences.getInt(COUNT_KEY, 0);
            mainBinding.countTextview.setText(count + "");
            mainBinding.countTextview.setBackgroundColor(mColor);
        }
    }

    public void reset(View view) {
        mColor = getResources().getColor(R.color.default_background);
        mainBinding.countTextview.setBackgroundColor(mColor);
    }

    public void countUp(View view) {
        ++count;
        mainBinding.countTextview.setText("" + count);
    }

    public void changeBackground(View view) {
        switch (view.getId()) {
            case R.id.red_background_button:
                mColor = getResources().getColor(R.color.red_background);
                break;
            case R.id.green_background_button:
                mColor = getResources().getColor(R.color.green_background);
                break;
            case R.id.blue_background_button:
                mColor = getResources().getColor(R.color.blue_background);
                break;
            default:
                mColor = getResources().getColor(R.color.black_bg);

        }
        mainBinding.countTextview.setBackgroundColor(mColor);
    }
}