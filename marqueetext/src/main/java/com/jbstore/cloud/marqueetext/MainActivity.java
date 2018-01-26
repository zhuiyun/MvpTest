package com.jbstore.cloud.marqueetext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.qqtheme.framework.picker.ColorPicker;

public class MainActivity extends AppCompatActivity {
    ColorPicker colorPicker;
    ColorPicker colorPicker1;
    int color = 0xDD00DD;
    EditText editText;
    int color1=0xDD00DD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorPicker = new ColorPicker(this);
        colorPicker1 = new ColorPicker(this);
        editText = findViewById(R.id.edit);
        colorPicker.setInitColor(0xDD00DD);
        colorPicker1.setInitColor(0xDD00DD);

        findViewById(R.id.selectColor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPicker.show();
                colorPicker.setOnColorPickListener(new ColorPicker.OnColorPickListener() {
                    @Override
                    public void onColorPicked(int pickedColor) {
                        if (pickedColor != -1) {
                            color = pickedColor;
                            ((TextView) findViewById(R.id.selectColor)).setTextColor(pickedColor);
                        }
                    }
                });
            }
        });
        findViewById(R.id.selectColor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("gao", "onClick: ");
                colorPicker1.show();
                colorPicker1.setOnColorPickListener(new ColorPicker.OnColorPickListener() {
                    @Override
                    public void onColorPicked(int pickedColor) {
                        if (pickedColor != -1) {
                            color1 = pickedColor;
                            ((TextView) findViewById(R.id.selectColor1)).setBackgroundColor(pickedColor);
                        }
                    }
                });
            }
        });

        findViewById(R.id.showText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("gao", "onClick: "+color+"---"+color1);
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    Toast.makeText(MainActivity.this,"请输入要显示的字",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(MainActivity.this,ShowActivity.class).putExtra("textColor",color).putExtra("text",editText.getText().toString()).putExtra("textColor1",color1));
                }
            }
        });
    }
}
