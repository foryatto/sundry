package com.foryatto.onetoolbox.utils;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    public static void sendRequestWithOkHttp(String url, String mode, RequestBody requestBody, Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = null;
        if (mode.equals("get")){
            request = new Request.Builder()
                    .url(url)
                    .build();
        }
        else {
            request = new Request.Builder()
                    .url("")
                    .post(requestBody)
                    .build();
        }
        // 获取资源，然后执行回调函数
        // OkHttp在enqueue()方法的内部已经帮我们开好子线程了，然后会在子线程中去执行HTTP请求，并将最终的请求结果回调到okhttp.Callback当中。
        client.newCall(request).enqueue(callback);
    }

}

// POST
//                RequestBody requestBody = new FormBody.Builder()
//                        .add("username", "admin")
//                        .add("password", "123456")
//                        .build();