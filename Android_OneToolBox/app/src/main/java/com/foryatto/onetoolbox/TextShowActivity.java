package com.foryatto.onetoolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.xuexiang.xui.widget.actionbar.TitleBar;

public class TextShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_show);

        titleBarInit();
        Intent intent = getIntent();
        showTextView(intent.getStringExtra("content"));

    }

    private void titleBarInit(){
        TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar_textshow);
        titleBar.setLeftClickListener(v->{
            Intent intent = new Intent(TextShowActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void showTextView(String content){
        TextView contentTextView = (TextView)findViewById(R.id.text_show);
        contentTextView.setText(content);
    }

}