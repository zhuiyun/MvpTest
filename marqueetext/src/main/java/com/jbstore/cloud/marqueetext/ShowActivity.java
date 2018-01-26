package com.jbstore.cloud.marqueetext;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class ShowActivity extends AppCompatActivity {
    int color;
    String text;
    MarQueeTextView textView;
    int color1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_show);
        textView = findViewById(R.id.text2);
        color = getIntent().getIntExtra("textColor", Color.BLACK);
        color1 = getIntent().getIntExtra("textColor1", Color.BLACK);
        text = getIntent().getStringExtra("text");
        textView.setText(text);
        textView.setBackgroundColor(color1);
        textView.setTextColor(color);

    }
}
