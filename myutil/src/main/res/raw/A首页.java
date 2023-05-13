package com.example.a20220603.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.a20220603.Load;
import com.example.a20220603.R;
import com.example.a20220603.bean.Lbt;
import com.example.a20220603.bean.Msg;
import com.example.a20220603.bean.Yplb;
import com.example.a20220603.bean.Zbyy;
import com.example.a20220603.ui.home.Ada;
import com.example.a20220603.ui.home.HomeViewModel;
import com.example.a20220603.zbase.Cal;
import com.example.a20220603.zbase.MyRecyclerView;
import com.lljjcoder.style.citylist.CityListSelectActivity;
import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.lljjcoder.style.citylist.widget.CleanableEditView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class A首页 extends Fragment {

    private HomeViewModel homeViewModel;
    private LinearLayout gone应用功能;
    private TextView textView2;
    private LinearLayout gone当前热映;
    private TextView CITY;
    private LinearLayout gone即将上映;
    private CleanableEditView cityInputText;
    private LinearLayout gone周边影院;
    private Banner banner;
    private MyRecyclerView 应用功能;
    private MyRecyclerView 当前热映;
    private MyRecyclerView 即将上映;
    private MyRecyclerView 周边影院;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initView(root);
        city();
        sear();
        lbt();
        tjfw();
        热映影片();
        即将上映();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("urlasdasdasd/---------movie/theatre/list")
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Ada<Zbyy>() {
            @Override
            protected void bix(Zbyy bean) {
                List<Zbyy.RowsBean> data = bean.rows;
                周边影院.setAdapter(1, data.size(), R.layout.item_kdy, new Cal() {
                    @Override
                    public void bix(View v, int position) {
                        Zbyy.RowsBean bean = data.get(position);
                        ImageView icon;
                        TextView title;
                        TextView content;
                        RatingBar date;

                        icon = v.findViewById(R.id.icon);
                        title = v.findViewById(R.id.title);
                        content = v.findViewById(R.id.content);
                        date = v.findViewById(R.id.date);


                        Load.load(icon, bean.cover);
                        title.setText(bean.name);
                        content.setText("距离:" + bean.distance + "米\n" + bean.province + " " + bean.city + " " + bean.area + " " + bean.address + " ");
                        date.setRating(bean.score);
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Load.djyy = bean.id;
                                YyxqyMainActivity.start(getContext());
                            }
                        });
                    }
                });
            }
        });
        return root;
    }

    private void 即将上映() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("urlasdasdasd/---------movie/press/press/list?type=12")
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Ada<Yplb>() {
            @Override
            protected void bix(Yplb bean) {
                List<Yplb.RowsBean> data = bean.rows;
                即将上映.setAdapter(data.size(), data.size(), R.layout.rmyp, new Cal() {
                    @Override
                    public void bix(View v, int position) {
                        Yplb.RowsBean bean = data.get(position);
                        ImageView icon;
                        TextView title;
                        TextView buttonPanel;

                        icon = v.findViewById(R.id.icon);
                        title = v.findViewById(R.id.title);
                        buttonPanel = v.findViewById(R.id.buttonPanel);
                        Load.load(icon, bean.cover);
                        title.setText(bean.title);
                        buttonPanel.setText("想看");
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Load.xqy = "想看";
                                Load.djid = bean.id;
                                XqyMainActivity.start(getContext());
                            }
                        });
                        buttonPanel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 收藏按钮
                                OkHttpClient client = new OkHttpClient().newBuilder()
                                        .build();
                                Request request = new Request.Builder()
                                        .url("urlasdasdasd/---------movie/film/like/" + bean.id)
                                        .method("GET", null)
                                        .addHeader("Authorization", Load.token())
                                        .build();
                                client.newCall(request).enqueue(new Ada<Msg>() {
                                    @Override
                                    protected void bix(Msg bean) {
                                        Load.show(bean.msg);
                                        if (bean.code == 200) {
                                            buttonPanel.setVisibility(View.GONE);
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    private void 热映影片() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("urlasdasdasd/---------movie/press/press/list?type=2")
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Ada<Yplb>() {
            @Override
            protected void bix(Yplb bean) {
                List<Yplb.RowsBean> data = bean.rows;
                当前热映.setAdapter(data.size(), data.size(), R.layout.rmyp, new Cal() {
                    @Override
                    public void bix(View v, int position) {
                        Yplb.RowsBean bean = data.get(position);
                        ImageView icon;
                        TextView title;
                        TextView buttonPanel;

                        icon = v.findViewById(R.id.icon);
                        title = v.findViewById(R.id.title);
                        buttonPanel = v.findViewById(R.id.buttonPanel);
                        Load.load(icon, bean.cover);
                        title.setText(bean.title);
                        buttonPanel.setText("购票");
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Load.xqy = "购票";
                                Load.djid = bean.id;

                                XqyMainActivity.start(getContext());
                            }
                        });
                        buttonPanel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Load.xqy = "购票";
                                Load.djid = bean.id;
                                GpMainActivity.start(getContext());
                            }
                        });
                    }
                });
            }
        });
    }

    private void tjfw() {
        应用功能.setAdapter(4, 4, R.layout.qbfw, new Cal() {
            @Override
            public void bix(View v, int position) {
                ImageView icon;
                TextView title;

                icon = v.findViewById(R.id.icon);
                title = v.findViewById(R.id.title);
                if (position == 0) {
                    Load.load(icon, R.drawable.s1);
                    title.setText("推荐");

                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TjMainActivity.start(getContext());
                        }
                    });
                }
                if (position == 1) {

                    Load.load(icon, R.drawable.s2);
                    title.setText("预告");
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            YgMainActivity.start(getContext());
                        }
                    });
                }
                if (position == 2) {

                    Load.load(icon, R.drawable.s3);
                    title.setText("影评");
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            YpMainActivity.start(getContext());
                        }
                    });
                }
                if (position == 3) {
                    Load.load(icon, R.drawable.s1);
                    title.setText("星闻");
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            XwMainActivity.start(getContext());
                        }
                    });
                }


            }
        });
    }

    private void lbt() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("urlasdasdasd/---------movie/rotation/list")
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Ada<Lbt>() {
            @Override
            protected void bix(Lbt bean) {
                List<Lbt.RowsBean> data = bean.rows;
                ArrayList<String> imageUrls = new ArrayList<>();
                ArrayList<String> titles = new ArrayList<>();
                for (Lbt.RowsBean datum : data) {
                    imageUrls.add(datum.advImg);
                    titles.add(datum.advTitle);
                }
                banner.setImageLoader(new Load())
                        .setImages(imageUrls)
                        .setBannerTitles(titles)
                        .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                        .setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int i) {
                                Load.djid = data.get(i).targetId;
                                XqyMainActivity.start(getContext());
                            }
                        })
                        .start();
            }
        });
    }

    private void sear() {
        cityInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == 3) {
                    String s = v.getText().toString();
                    Load.ssxq = s;
                    SearMainActivity.start(getContext());
                    v.setText("");
                }
                return true;
            }
        });
    }

    private void city() {
        CityListLoader.getInstance().loadCityData(getContext());
        ActivityResultLauncher<Intent> result = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                try {
                    CityInfoBean cityinfo = result.getData().getParcelableExtra("cityinfo");
                    CITY.setText(cityinfo.getName());
                } catch (Exception e) {
                    CITY.setText("大连");
                }
            }
        });
        CITY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.launch(new Intent(requireContext(), CityListSelectActivity.class));
            }
        });
    }

    private void initView(View V) {
        textView2 = V.findViewById(R.id.textView2);
        CITY = V.findViewById(R.id.CITY);
        cityInputText = V.findViewById(R.id.cityInputText);
        banner = V.findViewById(R.id.banner);
        gone应用功能 = V.findViewById(R.id.gone应用功能);
        应用功能 = V.findViewById(R.id.应用功能);
        gone当前热映 = V.findViewById(R.id.gone当前热映);
        当前热映 = V.findViewById(R.id.当前热映);
        gone即将上映 = V.findViewById(R.id.gone即将上映);
        即将上映 = V.findViewById(R.id.即将上映);
        gone周边影院 = V.findViewById(R.id.gone周边影院);
        周边影院 = V.findViewById(R.id.周边影院);
    }
}