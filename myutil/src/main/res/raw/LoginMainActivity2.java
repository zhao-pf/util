package com.example.a20220603;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20220603.bean.Msg;
import com.example.a20220603.ui.home.Ada;
import com.example.a20220603.zbase.BaseActivity;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class LoginMainActivity2 extends BaseActivity implements View.OnClickListener {

    private TextView textView2;
    private TextInputEditText zh;
    private TextInputEditText xmm;
    private TextView commit;
    private TextView zczh;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginMainActivity2.class);
        context.startActivity(starter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main2);
        initView();

    }

    @Override
    protected String bix() {
        return "登录";
    }

    private void initView() {
        textView2 = findViewById(R.id.textView2);
        zh = findViewById(R.id.zh);
        xmm = findViewById(R.id.xmm);
        commit = findViewById(R.id.commit);
        zczh = findViewById(R.id.zczh);

        commit.setOnClickListener(this);
        zczh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit:
                submit();
                break;
            case R.id.zczh:
                RegMainActivity2.start(this);
                break;
        }
    }

    private void submit() {
        // validate
        String zhString = zh.getText().toString().trim();
        if (TextUtils.isEmpty(zhString)) {
            Toast.makeText(this, "账号", Toast.LENGTH_SHORT).show();
            return;
        }

        String xmmString = xmm.getText().toString().trim();
        if (TextUtils.isEmpty(xmmString)) {
            Toast.makeText(this, "密码", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n\"username\":\"" + zhString + "\",\r\n\"password\":\"" + xmmString + "\"\r\n}");
        Request request = new Request.Builder()
                .url("http://124.93.196.45:10001/---------login")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Ada<Msg>() {
            @Override
            protected void bix(Msg bean) {
                Load.show(bean.msg);
                if (bean.code == 200) {
                    PreferenceManager.getDefaultSharedPreferences(LoginMainActivity2.this).edit().putString("token", bean.token).apply();
//                    MainActivity.start(LoginMainActivity2.this);
                    onBackPressed();
                }
            }
        });

    }
}
