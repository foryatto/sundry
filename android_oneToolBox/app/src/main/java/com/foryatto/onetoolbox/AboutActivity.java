package com.foryatto.onetoolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.grouplist.XUIGroupListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        titleBarInit();
        initViews();

    }

    private void titleBarInit(){
        TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar_about);
        titleBar.setLeftClickListener(v->{
            Intent intent = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    protected void initViews() {
        TextView versionTextView = (TextView)findViewById(R.id.ab_author);
        XUIGroupListView aboutGroupListView = (XUIGroupListView)findViewById(R.id.about_list);
        TextView copyrightTextView = (TextView)findViewById(R.id.ab_copyright);
        versionTextView.setText("作者: Foryatto");

        XUIGroupListView.newSection(this)
                .addItemView(aboutGroupListView.createItemView("关于作者"), v -> {
                    Intent intent = new Intent(this, WebActivity.class);
                    intent.putExtra("url","https://www.foryatto.com");
                    startActivity(intent);
                })
                .addItemView(aboutGroupListView.createItemView("说明文档"), v-> {
                    new MaterialDialog.Builder(this)
                            .iconRes(R.drawable.icon_tip)
                            .title("说明")
                            .content("一个简洁高效的工具箱，集成了许多常用功能！更多功能欢迎自行探索！")
                            .positiveText("确定")
                            .show();
                })
                .addItemView(aboutGroupListView.createItemView("赞助本项目"), v -> {
                    new MaterialDialog.Builder(this)
                            .iconRes(R.drawable.icon_tip)
                            .title("赞助")
                            .content("叮！钱包到账，0.01元！")
                            .positiveText("立即赞助")
                            .show();
                })
                .addItemView(aboutGroupListView.createItemView("问题反馈"), v-> {
                    new MaterialDialog.Builder(this)
                            .iconRes(R.drawable.icon_tip)
                            .title("问题反馈")
                            .content("一杯茶一包烟，一个BUG改一天")
                            .positiveText("确定")
                            .show();
                })
                .addTo(aboutGroupListView);

        copyrightTextView.setText("©2020 - 2021 By Foryatto");
    }

}