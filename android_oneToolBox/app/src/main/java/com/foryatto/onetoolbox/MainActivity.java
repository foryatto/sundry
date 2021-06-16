package com.foryatto.onetoolbox;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.foryatto.onetoolbox.pojo.Baike;
import com.foryatto.onetoolbox.pojo.IpLocation;
import com.foryatto.onetoolbox.pojo.NcmRemark;
import com.foryatto.onetoolbox.pojo.Poet;
import com.foryatto.onetoolbox.pojo.TelNumber;
import com.foryatto.onetoolbox.pojo.Translation;
import com.foryatto.onetoolbox.pojo.Trash;
import com.foryatto.onetoolbox.pojo.Yiyan;
import com.foryatto.onetoolbox.utils.HttpUtil;
import com.google.gson.Gson;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleBarInit();
        helpfulToolsInit();
        wordToolsInit();
        picToolsInit();
    }

    // 导航栏的实现
    private void titleBarInit() {
        TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar_main);
        titleBar.setLeftClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }).addAction(new TitleBar.ImageAction(R.drawable.ic_navigation_more) {
            @Override
            public void performAction(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    // 实用类功能实现
    private void helpfulToolsInit() {
        // 天气查询 功能实现
        SuperButton btWeather = (SuperButton) findViewById(R.id.bt_h_weather);
        btWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url", "file:///android_asset/weather.html");
                intent.putExtra("mode", 0);
                startActivity(intent);
            }
        });

        // App下载 功能实现
        SuperButton btAppDown = (SuperButton) findViewById(R.id.bt_h_appdown);
        btAppDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url", "file:///android_asset/app.html");
                intent.putExtra("mode", 0);
                startActivity(intent);
            }
        });

        // 垃圾分类 功能实现
        SuperButton btTrash = (SuperButton) findViewById(R.id.bt_h_trash);
        btTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(MainActivity.this)
                        .iconRes(R.drawable.ic_expand_web)
                        .title("垃圾分类查询")
                        .content("请输入要查找的关键词：")
                        .inputType(
                                InputType.TYPE_CLASS_TEXT)
                        .input(
                                "",
                                "",
                                false,
                                ((dialog, input) -> {
//                                    String query = input.toString();
//                                    Toast.makeText(MainActivity.this, "输入框测试1： "+query, Toast.LENGTH_SHORT).show();
                                }))
                        .inputRange(1, 100)
                        .positiveText("查询")
                        .negativeText("取消")
                        .onPositive((dialog, which) -> {
                            String query = dialog.getInputEditText().getText().toString();
                            HttpUtil.sendRequestWithOkHttp("https://api.muxiaoguo.cn/api/lajifl?m=" + query, "get", null, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            new MaterialDialog.Builder(MainActivity.this)
                                                    .iconRes(R.drawable.icon_warning)
                                                    .title("垃圾分类查询")
                                                    .content("获取失败，请稍后重试！")
                                                    .positiveText("确定")
                                                    .show();
                                        }
                                    });
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String requestData = response.body().string();
                                    Gson gson = new Gson();
                                    Trash trash = gson.fromJson(requestData, Trash.class);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (trash.getCode().equals("200")) {
                                                Trash.DataBean dataBean = trash.getData();
                                                Intent intent = new Intent(MainActivity.this, TextShowActivity.class);
                                                intent.putExtra("content", "\n关键词: " + query + "\n\n类型：" + dataBean.getType() + "\n\n说明：" + dataBean.getDescription());
                                                startActivity(intent);
                                            } else {
                                                new MaterialDialog.Builder(MainActivity.this)
                                                        .iconRes(R.drawable.icon_warning)
                                                        .title("垃圾分类查询")
                                                        .content("获取失败，请稍后重试！")
                                                        .positiveText("确定")
                                                        .show();
                                            }
                                        }
                                    });
                                }
                            });
                        })
                        .cancelable(false)
                        .show();
            }
        });

        // IP查询 功能实现
        SuperButton btIp = (SuperButton) findViewById(R.id.bt_h_ip);
        btIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(MainActivity.this)
                        .iconRes(R.drawable.ic_expand_web)
                        .title("IP位置查询")
                        .content("请输入要查找的IP：")
                        .inputType(
                                InputType.TYPE_CLASS_TEXT)
                        .input(
                                "",
                                "",
                                false,
                                ((dialog, input) -> {
//                                    String query = input.toString();
//                                    Toast.makeText(MainActivity.this, "输入框测试1： "+query, Toast.LENGTH_SHORT).show();
                                }))
                        .inputRange(1, 100)
                        .positiveText("查询")
                        .negativeText("取消")
                        .onPositive((dialog, which) -> {
                            String query = dialog.getInputEditText().getText().toString();
                            HttpUtil.sendRequestWithOkHttp("https://api.muxiaoguo.cn/api/ip?ip="+query+"&type=b", "get", null, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            new MaterialDialog.Builder(MainActivity.this)
                                                    .iconRes(R.drawable.icon_warning)
                                                    .title("IP位置查询")
                                                    .content("获取失败，请稍后重试！")
                                                    .positiveText("确定")
                                                    .show();
                                        }
                                    });
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String requestData = response.body().string();
                                    Gson gson = new Gson();
                                    IpLocation ipLocation = gson.fromJson(requestData, IpLocation.class);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (ipLocation.getCode().equals("200")) {
                                                IpLocation.DataBean dataBean = ipLocation.getData();
                                                Intent intent = new Intent(MainActivity.this, TextShowActivity.class);
                                                intent.putExtra("content", "\nIP: " + query + "\n\n位置：" + dataBean.getGeographical_location());
                                                startActivity(intent);
                                            } else {
                                                new MaterialDialog.Builder(MainActivity.this)
                                                        .iconRes(R.drawable.icon_warning)
                                                        .title("IP位置查询")
                                                        .content("获取失败，请稍后重试！")
                                                        .positiveText("确定")
                                                        .show();
                                            }
                                        }
                                    });
                                }
                            });
                        })
                        .cancelable(false)
                        .show();
            }
        });

        // 手机号归属地 功能实现
        SuperButton btTel = (SuperButton) findViewById(R.id.bt_h_tel);
        btTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(MainActivity.this)
                        .iconRes(R.drawable.ic_expand_web)
                        .title("手机号归属地查询")
                        .content("请输入要查找的手机号：")
                        .inputType(
                                InputType.TYPE_CLASS_TEXT)
                        .input(
                                "",
                                "",
                                false,
                                ((dialog, input) -> {
//                                    String query = input.toString();
//                                    Toast.makeText(MainActivity.this, "输入框测试1： "+query, Toast.LENGTH_SHORT).show();
                                }))
                        .inputRange(1, 100)
                        .positiveText("查询")
                        .negativeText("取消")
                        .onPositive((dialog, which) -> {
                            String query = dialog.getInputEditText().getText().toString();
                            HttpUtil.sendRequestWithOkHttp("https://api.muxiaoguo.cn/api/mobile?phone="+query, "get", null, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            new MaterialDialog.Builder(MainActivity.this)
                                                    .iconRes(R.drawable.icon_warning)
                                                    .title("手机号归属地查询")
                                                    .content("获取失败，请稍后重试！")
                                                    .positiveText("确定")
                                                    .show();
                                        }
                                    });
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String requestData = response.body().string();
                                    Gson gson = new Gson();
                                    TelNumber telNumber = gson.fromJson(requestData, TelNumber.class);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (telNumber.getCode().equals("200")) {
                                                TelNumber.DataBean dataBean = telNumber.getData();
                                                Intent intent = new Intent(MainActivity.this, TextShowActivity.class);
                                                intent.putExtra("content", "\n手机号: " + query + "\n\n归属地：" + dataBean.getProvince()+dataBean.getCity());
                                                startActivity(intent);
                                            } else {
                                                new MaterialDialog.Builder(MainActivity.this)
                                                        .iconRes(R.drawable.icon_warning)
                                                        .title("手机号归属地查询")
                                                        .content("获取失败，请稍后重试！")
                                                        .positiveText("确定")
                                                        .show();
                                            }
                                        }
                                    });
                                }
                            });
                        })
                        .cancelable(false)
                        .show();
            }
        });

        // 翻译 功能实现
        SuperButton btTrans = (SuperButton) findViewById(R.id.bt_h_trans);
        btTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(MainActivity.this)
                        .iconRes(R.drawable.ic_expand_web)
                        .title("翻译")
                        .content("请输入要翻译的内容：")
                        .inputType(
                                InputType.TYPE_CLASS_TEXT)
                        .input(
                                "",
                                "",
                                false,
                                ((dialog, input) -> {
//                                    String query = input.toString();
//                                    Toast.makeText(MainActivity.this, "输入框测试1： "+query, Toast.LENGTH_SHORT).show();
                                }))
                        .inputRange(1, 100)
                        .positiveText("查询")
                        .negativeText("取消")
                        .onPositive((dialog, which) -> {
                            String query = dialog.getInputEditText().getText().toString();
                            HttpUtil.sendRequestWithOkHttp("https://api.muxiaoguo.cn/api/Tn_tencent?text="+query, "get", null, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            new MaterialDialog.Builder(MainActivity.this)
                                                    .iconRes(R.drawable.icon_warning)
                                                    .title("翻译")
                                                    .content("获取失败，请稍后重试！")
                                                    .positiveText("确定")
                                                    .show();
                                        }
                                    });
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String requestData = response.body().string();
                                    Gson gson = new Gson();
                                    Translation translation = gson.fromJson(requestData, Translation.class);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (translation.getCode().equals("200")) {
                                                Translation.DataBean dataBean = translation.getData();
                                                Intent intent = new Intent(MainActivity.this, TextShowActivity.class);
                                                intent.putExtra("content", "\n原文: " + query + "\n\n翻译：" + dataBean.getTranslation());
                                                startActivity(intent);
                                            } else {
                                                new MaterialDialog.Builder(MainActivity.this)
                                                        .iconRes(R.drawable.icon_warning)
                                                        .title("翻译")
                                                        .content("获取失败，请稍后重试！")
                                                        .positiveText("确定")
                                                        .show();
                                            }
                                        }
                                    });
                                }
                            });
                        })
                        .cancelable(false)
                        .show();
            }
        });

        // QQ信息 功能实现
        SuperButton btQQ = (SuperButton) findViewById(R.id.bt_h_qq);
        btQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url", "file:///android_asset/qqInfo.html");
                intent.putExtra("mode", 0);
                startActivity(intent);
            }
        });

        // 匿名邮件 功能实现
        SuperButton btEmail = (SuperButton) findViewById(R.id.bt_h_email);
        btEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url", "file:///android_asset/email.html");
                intent.putExtra("mode", 0);
                startActivity(intent);
            }
        });

    }

    // 文字类工具的实现
    private void wordToolsInit() {
        // 一言 功能实现
        SuperButton btYiyan = (SuperButton) findViewById(R.id.bt_w_yiyan);
        btYiyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 不管是使用HttpURLConnection还是OkHttp，最终的回调接口都还是在子线程中运行的，因此我们不可以在这里执行任何的UI操作，除非借助runOnUiThread()方法来进行线程转换。
                HttpUtil.sendRequestWithOkHttp("https://api.muxiaoguo.cn/api/yiyan", "get", null, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new MaterialDialog.Builder(MainActivity.this)
                                        .iconRes(R.drawable.icon_warning)
                                        .title("一言")
                                        .content("获取失败，请稍后重试！")
                                        .positiveText("确定")
                                        .show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String requestData = response.body().string();
                        Gson gson = new Gson();
                        Yiyan yiyan = gson.fromJson(requestData, Yiyan.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (yiyan.getCode().equals("200")) {
                                    Yiyan.DataBean dataBean = yiyan.getData();
                                    new MaterialDialog.Builder(MainActivity.this)
                                            .iconRes(R.drawable.icon_love)
                                            .title("一言")
                                            .content(dataBean.getConstant() + "\n\n" + "来自: " + dataBean.getSource())
                                            .positiveText("确定")
                                            .show();
                                } else {
                                    new MaterialDialog.Builder(MainActivity.this)
                                            .iconRes(R.drawable.icon_warning)
                                            .title("一言")
                                            .content("获取失败，请稍后重试！")
                                            .positiveText("确定")
                                            .show();
                                }
                            }
                        });
                    }
                });
            }
        });

        // 网易云音乐热评功能实现。。。
        SuperButton btNcmRemark = (SuperButton) findViewById(R.id.bt_w_ncm);
        btNcmRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 不管是使用HttpURLConnection还是OkHttp，最终的回调接口都还是在子线程中运行的，因此我们不可以在这里执行任何的UI操作，除非借助runOnUiThread()方法来进行线程转换。
                HttpUtil.sendRequestWithOkHttp("https://api.muxiaoguo.cn/api/163reping", "get", null, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new MaterialDialog.Builder(MainActivity.this)
                                        .iconRes(R.drawable.icon_warning)
                                        .title("网易云音乐")
                                        .content("获取失败，请稍后重试！")
                                        .positiveText("确定")
                                        .show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String requestData = response.body().string();
                        Gson gson = new Gson();
                        NcmRemark ncmRemark = gson.fromJson(requestData, NcmRemark.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (ncmRemark.getCode().equals("200")) {
                                    NcmRemark.DataBean dataBean = ncmRemark.getData();
                                    new MaterialDialog.Builder(MainActivity.this)
                                            .iconRes(R.drawable.icon_love)
                                            .title("网易云音乐")
                                            .content(dataBean.getContent() + "\n\n" + "用户: " + dataBean.getNickname() + " 在听" + dataBean.getSongName() + "这首歌时留下了此评论！")
                                            .positiveText("确定")
                                            .show();
                                } else {
                                    new MaterialDialog.Builder(MainActivity.this)
                                            .iconRes(R.drawable.icon_warning)
                                            .title("网易云音乐")
                                            .content("获取失败，请稍后重试！")
                                            .positiveText("确定")
                                            .show();
                                }
                            }
                        });
                    }
                });
            }
        });

        // 今日古诗词 功能实现
        SuperButton btPoet = (SuperButton) findViewById(R.id.bt_w_poet);
        btPoet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 不管是使用HttpURLConnection还是OkHttp，最终的回调接口都还是在子线程中运行的，因此我们不可以在这里执行任何的UI操作，除非借助runOnUiThread()方法来进行线程转换。
                HttpUtil.sendRequestWithOkHttp("https://api.muxiaoguo.cn/api/Gushici", "get", null, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new MaterialDialog.Builder(MainActivity.this)
                                        .iconRes(R.drawable.icon_warning)
                                        .title("今日古诗词")
                                        .content("获取失败，请稍后重试！")
                                        .positiveText("确定")
                                        .show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String requestData = response.body().string();
                        Gson gson = new Gson();
                        Poet poet = gson.fromJson(requestData, Poet.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (poet.getCode().equals("200")) {
                                    Poet.DataBean dataBean = poet.getData();
                                    new MaterialDialog.Builder(MainActivity.this)
                                            .iconRes(R.drawable.icon_love)
                                            .title("今日古诗词")
                                            .content(dataBean.getPoem_title() + "\n\n" + dataBean.getPoetry())
                                            .positiveText("确定")
                                            .show();
                                } else {
                                    new MaterialDialog.Builder(MainActivity.this)
                                            .iconRes(R.drawable.icon_warning)
                                            .title("今日古诗词")
                                            .content("获取失败，请稍后重试！")
                                            .positiveText("确定")
                                            .show();
                                }
                            }
                        });
                    }
                });
            }
        });

        // 历史上的今天 功能实现
        SuperButton btToday = (SuperButton) findViewById(R.id.bt_w_today);
        btToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url", "file:///android_asset/today.html");
                intent.putExtra("mode", 0);
                startActivity(intent);
            }
        });

        // 百科 功能实现
        SuperButton btBaike = (SuperButton) findViewById(R.id.bt_w_baike);
        btBaike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(MainActivity.this)
                        .iconRes(R.drawable.ic_expand_web)
                        .title("百科查询")
                        .content("请输入要查找的关键词：")
                        .inputType(
                                InputType.TYPE_CLASS_TEXT)
                        .input(
                                "",
                                "",
                                false,
                                ((dialog, input) -> {
//                                    String query = input.toString();
//                                    Toast.makeText(MainActivity.this, "输入框测试1： "+query, Toast.LENGTH_SHORT).show();
                                }))
                        .inputRange(1, 100)
                        .positiveText("查询")
                        .negativeText("取消")
                        .onPositive((dialog, which) -> {
                            String query = dialog.getInputEditText().getText().toString();
                            HttpUtil.sendRequestWithOkHttp("https://api.muxiaoguo.cn/api/Baike?type=Baidu&word=" + query, "get", null, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            new MaterialDialog.Builder(MainActivity.this)
                                                    .iconRes(R.drawable.icon_warning)
                                                    .title("百科查询")
                                                    .content("获取失败，请稍后重试！")
                                                    .positiveText("确定")
                                                    .show();
                                        }
                                    });
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String requestData = response.body().string();
                                    Gson gson = new Gson();
                                    Baike baike = gson.fromJson(requestData, Baike.class);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (baike.getCode() == 200) {
                                                Baike.DataBean dataBean = baike.getData();
                                                Intent intent = new Intent(MainActivity.this, TextShowActivity.class);
                                                intent.putExtra("content", "\n关键词: " + query + "\n\n" + dataBean.getContent() + "......");
                                                startActivity(intent);
                                            } else {
                                                new MaterialDialog.Builder(MainActivity.this)
                                                        .iconRes(R.drawable.icon_warning)
                                                        .title("百科查询")
                                                        .content("获取失败，请稍后重试！")
                                                        .positiveText("确定")
                                                        .show();
                                            }
                                        }
                                    });
                                }
                            });
                        })
                        .cancelable(false)
                        .show();
            }
        });
    }

    // 图片相关 功能实现函数
    private void picToolsInit() {
        // Bing美图 功能实现
        SuperButton btBing = (SuperButton) findViewById(R.id.bt_p_bing);
        btBing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url", "file:///android_asset/bing.html");
                intent.putExtra("mode", 0);
                startActivity(intent);
            }
        });

        // 随机壁纸 功能实现
        SuperButton btRandom = (SuperButton) findViewById(R.id.bt_p_random);
        btRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url", "file:///android_asset/random_img.html");
                intent.putExtra("mode", 0);
                startActivity(intent);
            }
        });

        // 表情包搜索 功能实现
        SuperButton btMeme = (SuperButton) findViewById(R.id.bt_p_meme);
        btMeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url", "file:///android_asset/meme_search.html");
                intent.putExtra("mode", 0);
                startActivity(intent);
            }
        });

    }


}