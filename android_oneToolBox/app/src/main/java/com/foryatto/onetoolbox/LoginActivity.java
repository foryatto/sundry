package com.foryatto.onetoolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xuexiang.xui.widget.actionbar.TitleBar;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 初始化标题栏
        titleBarInit();
        init();
    }

    // 初始化标题栏
    private void titleBarInit(){
        TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar_login);
        // 设置返回按钮，点击返回到主界面
        titleBar.setLeftClickListener(v->{
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    // 初始化，获取账号密码，进行登录/注册
    protected void init(){

        // 获取组件
        View loginBtn = findViewById(R.id.loginConfirmBtn);
        View signUpBtn = findViewById(R.id.signUpBtn);
        EditText usernameText = (EditText)findViewById(R.id.input_username);
        EditText passwordText = (EditText)findViewById(R.id.input_password);

        // 按钮监听
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                // 验证信息完整性
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "请填写完整信息!", Toast.LENGTH_LONG).show();
                }
                else{
                    // 判断是否登录成功，成功则跳转到主界面
                    if("admin".equals(username) && "123456".equals(password)){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "用户名或密码错误，请重试!", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"账号功能暂不开放，敬请期待！",Toast.LENGTH_LONG).show();
            }
        });

    }

}