package com.example.a20220603;

import android.content.Context;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.a20220603.bean.Userq1;
import com.example.a20220603.bean.Ygp;
import com.example.a20220603.ui.JdxcMainActivity;
import com.example.a20220603.ui.SspMainActivity;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class Load extends ImageLoader {

    public static String ssxq;
    public static int djid;
    public static String xqy;
    public static int djyy;
    public static Userq1.UserBean user;
    public static Bitmap result;
    public static Ygp.RowsBean bfyg;
    public static boolean hdbm=false;
    public static ArrayList<JdxcMainActivity.S1> jdxc=new ArrayList<JdxcMainActivity.S1>(){{
        add(new JdxcMainActivity.S1("建议5G时代“党建文化+直播”主题党日我们这样过", "9月3日中午12时30分， YCx创显科教抗战胜利75周年纪念日主题党日活动“新时代云讲习所”在腾讯直播全网首发展示， 这是一场融合了“VR+MR直播+4D实景上云+社群运营+智慧党建+内容策划”的生动有趣的党建日活动，我们用技术展示并回顾历程，用实际行动自我勉励“吾辈应不忘初心，牢记使命”！\n" +
                "从开播的200个人到26000人，在手机屏前以创新的体验重温了党员走过的路，感受到党赋予人民的力量！\n", "2020-01-01"));
        add(new JdxcMainActivity.S1("建议5G时代“党建文化+直播”主题党日我们这样过", "9月3日中午12时30分， YCx创显科教抗战胜利75周年纪念日主题党日活动“新时代云讲习所”在腾讯直播全网首发展示， 这是一场融合了“VR+MR直播+4D实景上云+社群运营+智慧党建+内容策划”的生动有趣的党建日活动，我们用技术展示并回顾历程，用实际行动自我勉励“吾辈应不忘初心，牢记使命”！\n" +
                "从开播的200个人到26000人，在手机屏前以创新的体验重温了党员走过的路，感受到党赋予人民的力量！\n", "2020-01-01"));
        add(new JdxcMainActivity.S1("建议5G时代“党建文化+直播”主题党日我们这样过", "9月3日中午12时30分， YCx创显科教抗战胜利75周年纪念日主题党日活动“新时代云讲习所”在腾讯直播全网首发展示， 这是一场融合了“VR+MR直播+4D实景上云+社群运营+智慧党建+内容策划”的生动有趣的党建日活动，我们用技术展示并回顾历程，用实际行动自我勉励“吾辈应不忘初心，牢记使命”！\n" +
                "从开播的200个人到26000人，在手机屏前以创新的体验重温了党员走过的路，感受到党赋予人民的力量！\n", "2020-01-01"));

    }};
public static ArrayList<SspMainActivity.SSS1> ssp=new ArrayList<SspMainActivity.SSS1>(){{


    }};
    public static SspMainActivity.SSS1 djssp;

    public static String token() {
        return "Bearer " + PreferenceManager.getDefaultSharedPreferences(Init.context).getString("token", "");
    }

    public static void show(String msg) {
        ToastUtils.showShortToast(Init.context, msg);
    }

    public static void load(ImageView imageView, Object o) {
        int roundingRadius = 5;
        load(imageView, o, roundingRadius);
    }

    public static void load(ImageView imageView, Object o, int roundingRadius) {
        Glide.with(imageView)
                .load(o)
                .transforms(new MultiTransformation<Bitmap>(new CenterCrop(), new RoundedCorners(roundingRadius)))
                .into(imageView);
    }

    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        load(imageView, o);
    }
}
