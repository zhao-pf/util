package com.example.a20220603.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a20220603.Load;
import com.example.a20220603.R;
import com.example.a20220603.bean.Ddlb;
import com.example.a20220603.ui.home.Ada;
import com.example.a20220603.zbase.BaseActivity;
import com.example.a20220603.zbase.Cal;
import com.example.a20220603.zbase.MyRecyclerView;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class 订单MainActivity extends BaseActivity {

    private TextView textView;
    private MyRecyclerView ddlb;
    private LinearLayout ddlkb;

    public static void start(Context context) {
        Intent starter = new Intent(context, DdlbMainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddlb_main);
        initView();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("urlasdasd/---------allorder/list")
                .method("GET", null)
                .addHeader("Authorization", Load.token())
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Ada<Ddlb>() {
            @Override
            protected void bix(Ddlb bean) {
                List<Ddlb.RowsBean> data = bean.rows;
                ddlkb.setVisibility(data.size()==0?View.GONE:View.VISIBLE);
                ddlb.setAdapter(1, data.size(), R.layout.ddlb, new Cal() {
                    @Override
                    public void bix(View v, int position) {
                        Ddlb.RowsBean bean = data.get(position);
                        TextView title;
                        TextView date;
                        TextView content;
                        TextView price;

                        title = v.findViewById(R.id.title);
                        date = v.findViewById(R.id.date);
                        content = v.findViewById(R.id.content);
                        price = v.findViewById(R.id.price);
//                        订单号、订单类型、订单生成日期
                        title.setText(bean.orderNo);
                        date.setText(bean.orderTypeName);
                        content.setText("生成日期" + bean.payTime);
                        price.setText("￥" + bean.amount);
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DdxqyMainActivity.start(DdlbMainActivity.this);
                            }
                        });
                    }
                });
            }
        });

    }

    @Override
    protected String bix() {
        return "订单列表";
    }

    private void initView() {
        textView = findViewById(R.id.textView);
        ddlb = findViewById(R.id.ddlb);
        ddlkb = findViewById(R.id.ddlkb);
    }
}