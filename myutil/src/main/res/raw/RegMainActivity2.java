package com.example.a20220603;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class RegMainActivity2 extends BaseActivity implements View.OnClickListener {

    private TextView textView2;
    private TextInputEditText zh;
    private TextInputEditText xmm;
    private TextInputEditText sjh;
    private TextInputEditText sfz;
    private TextView commit;

    public static void start(Context context) {
        Intent starter = new Intent(context, RegMainActivity2.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_main2);
        initView();

    }

    @Override
    protected String bix() {
        return "注册";
    }

    private void initView() {
        textView2 = findViewById(R.id.textView2);
        zh = findViewById(R.id.zh);
        xmm = findViewById(R.id.xmm);
        sjh = findViewById(R.id.sjh);
        sfz = findViewById(R.id.sfz);
        commit = findViewById(R.id.commit);

        commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit:
                submit();
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

        String sjhString = sjh.getText().toString().trim();
        if (TextUtils.isEmpty(sjhString) || sjhString.length() != 11) {
            Toast.makeText(this, "手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String sfzString = sfz.getText().toString().trim();
        if (TextUtils.isEmpty(sfzString) || sfzString.length() != 18) {
            Toast.makeText(this, "身份证", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "    \"userName\": \"" + zhString + "\",\n" +
                "    \"nickName\": \"" + zhString + "\",\n" +
                "    \"password\": \"" + xmmString + "\",\n" +
                "    \"phonenumber\": \"" + sjhString + "\",\n" +
                "    \"sex\": \"0\",\n" +
                "    \"idCard\": \"" + sfzString + "\"\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://124.93.196.45:10001/---------register")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Ada<Msg>() {

            @Override
            protected void bix(Msg bean) {
                Load.show(bean.msg);
                if (bean.code == 200) {
                    onBackPressed();
                }
            }
        });
        // TODO validate success, do something


    }
}