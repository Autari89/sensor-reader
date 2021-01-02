package com.sensor.functions;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.sensor.phone_sensors.R;

public class ObjectDetection extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detection_layout);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}
