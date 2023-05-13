package com.example.a20220603.ui.home;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.a20220603.Init;
import com.example.a20220603.Load;
import com.example.a20220603.LoginMainActivity2;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class Ada<T> implements Callback {
    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {

    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        String all = response.body().string().replaceAll("", "");
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        final T json = new Gson().fromJson(all, type);
        Log.e("数据", all);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (all.contains("系统资源")) {
                    Load.show("登录失效了，请重新登录");
                    LoginMainActivity2.start(Init.context);
                } else {
                    bix(json);
                }

            }
        });
    }

    protected abstract void bix(T bean);

}
