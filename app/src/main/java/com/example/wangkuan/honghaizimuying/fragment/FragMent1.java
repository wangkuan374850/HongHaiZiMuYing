package com.example.wangkuan.honghaizimuying.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.wangkuan.honghaizimuying.DengLuActivity;
import com.example.wangkuan.honghaizimuying.R;
import com.example.wangkuan.honghaizimuying.SaoMiaoActivity;
import com.example.wangkuan.honghaizimuying.ShangPinXiangQingActivity;
import com.example.wangkuan.honghaizimuying.SouSuoActivity;
import com.example.wangkuan.honghaizimuying.adapter.Fragment1GridViewAdapter;
import com.example.wangkuan.honghaizimuying.adapter.Framgnt1Adapter;
import com.example.wangkuan.honghaizimuying.adapter.MyShiYuanMiaoSha;
import com.example.wangkuan.honghaizimuying.adapter.MyZhuTiTeMai;
import com.example.wangkuan.honghaizimuying.bean.ZhuYeBean;
import com.example.wangkuan.honghaizimuying.util.ImageLoaderUtils;
import com.example.wangkuan.honghaizimuying.util.TiaoZhuanUtil;
import com.example.wangkuan.honghaizimuying.view.MyGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.solidfire.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by wangkuan on 2016/11/8.
 */
public class FragMent1 extends Fragment implements EasyPermissions.PermissionCallbacks {
    //请求返回码的标记
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    //初始化imagerlouder
    private DisplayImageOptions options = ImageLoaderUtils.initOptions();
    private MyGridView gridView;
    private ViewPager vp;

    private ZhuYeBean zhuYe;
    ;//数据的类
    //初始化okhttp
    private OkHttpClient ok = new OkHttpClient.Builder().build();
    private LinearLayout linearLayout;//线性布局
    private ImageView shiYi;//标题十一的
    private ImageView zhuTui;//主推的标题
    private ImageView shiYi_tu1;//标题shiyi下面的图片
    private ImageView shiYi_tu2;//标题shiyi下面的图片
    private ImageView shiYi_tu3;//标题shiyi下面的图片
    private ImageView shiYi_tu4;//标题shiyi下面的图片
    private ImageView shiYi_tu5;//标题shiyi下面的图片
    private ImageView shiYi_tu6;//标题shiyi下面的图片
    private ImageView pinTuan_biaoTi;//辣妈拼团
    private ImageView pinTuan_tu1;//辣妈拼团_tu
    private ImageView pinTuan_tu2;//辣妈拼团_tu
    private ImageView pinTuan_tu3;//辣妈拼团_tu
    private ImageView pinTuan_tu4;//辣妈拼团_tu
    private ImageView pinTuan_tu5;//辣妈拼团_tu

    private ImageView chaKanGengDuo;//查看更多
    private ImageView muYingZhuanQu;//母婴专区
    private ImageView muYingZhuanQu_tu1;//母婴专区图片
    private ImageView muYingZhuanQu_tu2;//母婴专区图片
    private ImageView muYingZhuanQu_tu3;//母婴专区图片
    private ImageView muYingZhuanQu_tu4;//母婴专区图片
    private ImageView muYingZhuanQu_tu5;//母婴专区图片
    private ImageView muYingZhuanQu_tu6;//母婴专区图片
    private ImageView zhuTiTeMai;//主题特卖
    private ImageView zhuTiTeMai_daTu1;//主题特卖图片
    private LinearLayout zhuTiTeMai_huaDong1;//主题特卖图片
    private ImageView zhuTiTeMai_daTu2;//主题特卖图片
    private LinearLayout zhuTiTeMai_huaDong2;//主题特卖图片
    private ImageView zhuTiTeMai_daTu3;//主题特卖图片
    private LinearLayout zhuTiTeMai_huaDong3;//主题特卖图片
    private ImageView zhuTiTeMai_daTu4;//主题特卖图片
    private LinearLayout zhuTiTeMai_huaDong4;//主题特卖图片
    private ImageView saoMa;
    private RelativeLayout souSuo;
    private ImageView xiaoXi;
    private LinearLayout yuanDian;
    private String path = "http://image1.suning.cn/";
    //更新ui
    Handler hd = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                zhuYe = (ZhuYeBean) msg.obj;


                //主页最上面的viewapaget的操作
                Framgnt1Adapter framgnt1Adapter = new Framgnt1Adapter(zhuYe, getActivity(), options);
                vp.setAdapter(framgnt1Adapter);
                framgnt1Adapter.setOnItemClickListener(new Framgnt1Adapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, String data) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), data);
                        //  Log.i("aaaaaaaaaaaaaaaaaaaa",data);
                    }
                });


                //设置第一个gridview的适配器
                gridView.setAdapter(new Fragment1GridViewAdapter(zhuYe, getActivity(), options));
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(1).tag.get(i).linkUrl);
                    }
                });
                //十元秒杀
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(2).tag.get(0).picUrl, zhuTui, options);
                gd.setAdapter(new MyShiYuanMiaoSha(getActivity(), zhuYe, options, 2));
                gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        startActivity(new Intent(getActivity(), ShangPinXiangQingActivity.class));
                    }
                });

                //傲娇品牌
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(4).tag.get(0).picUrl, shiYi, options);
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(5).tag.get(0).picUrl, shiYi_tu1, options);
                shiYi_tu1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(5).tag.get(0).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(5).tag.get(1).picUrl, shiYi_tu2, options);
                shiYi_tu2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(5).tag.get(1).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(6).tag.get(0).picUrl, shiYi_tu3, options);
                shiYi_tu3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(6).tag.get(0).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(6).tag.get(1).picUrl, shiYi_tu4, options);
                shiYi_tu4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(6).tag.get(1).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(7).tag.get(0).picUrl, shiYi_tu5, options);
                shiYi_tu5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(7).tag.get(0).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(7).tag.get(1).picUrl, shiYi_tu6, options);
                shiYi_tu6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(7).tag.get(1).linkUrl);
                    }
                });
                //拼团标题
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(23).tag.get(0).picUrl, pinTuan_biaoTi, options);

                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(24).tag.get(0).picUrl, pinTuan_tu1, options);
                pinTuan_tu1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(24).tag.get(0).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(26).tag.get(0).picUrl, pinTuan_tu2, options);
                pinTuan_tu2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(26).tag.get(0).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(28).tag.get(0).picUrl, pinTuan_tu3, options);
                pinTuan_tu3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(28).tag.get(0).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(30).tag.get(0).picUrl, pinTuan_tu4, options);
                pinTuan_tu4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(30).tag.get(0).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(32).tag.get(0).picUrl, pinTuan_tu5, options);
                pinTuan_tu5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(32).tag.get(0).linkUrl);
                    }
                });

                //查看更多
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(33).tag.get(0).picUrl, chaKanGengDuo, options);
                chaKanGengDuo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(33).tag.get(0).linkUrl);
                    }
                });
                //母婴专区
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(9).tag.get(0).picUrl, muYingZhuanQu, options);

                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(10).tag.get(0).picUrl, muYingZhuanQu_tu1, options);
                muYingZhuanQu_tu1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(10).tag.get(0).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(10).tag.get(1).picUrl, muYingZhuanQu_tu2, options);
                muYingZhuanQu_tu2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(10).tag.get(1).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(11).tag.get(0).picUrl, muYingZhuanQu_tu3, options);
                muYingZhuanQu_tu3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(11).tag.get(0).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(11).tag.get(1).picUrl, muYingZhuanQu_tu4, options);
                muYingZhuanQu_tu4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(11).tag.get(1).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(11).tag.get(2).picUrl, muYingZhuanQu_tu5, options);
                muYingZhuanQu_tu5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(11).tag.get(2).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(11).tag.get(3).picUrl, muYingZhuanQu_tu6, options);
                muYingZhuanQu_tu6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(11).tag.get(3).linkUrl);
                    }
                });
                //主题特卖
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(13).tag.get(0).picUrl, zhuTiTeMai, options);
                //主题特卖大图片
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(14).tag.get(0).picUrl, zhuTiTeMai_daTu1, options);
                //点击事件
                zhuTiTeMai_daTu1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(14).tag.get(0).linkUrl);
                    }
                });
                gd1.setAdapter(new MyZhuTiTeMai(getActivity(), zhuYe, options, 15));
                //点击事件
                gd1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(15).tag.get(i).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(16).tag.get(0).picUrl, zhuTiTeMai_daTu2, options);
                //点击事件
                zhuTiTeMai_daTu2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(16).tag.get(0).linkUrl);
                    }
                });
                gd2.setAdapter(new MyZhuTiTeMai(getActivity(), zhuYe, options, 17));
                //点击事件
                gd2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(17).tag.get(i).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(18).tag.get(0).picUrl, zhuTiTeMai_daTu3, options);
                //点击事件
                zhuTiTeMai_daTu3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(18).tag.get(0).linkUrl);
                    }
                });
                gd3.setAdapter(new MyZhuTiTeMai(getActivity(), zhuYe, options, 19));
                //点击事件
                gd3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(19).tag.get(i).linkUrl);
                    }
                });
                ImageLoader.getInstance().displayImage(path + zhuYe.data.get(20).tag.get(0).picUrl, zhuTiTeMai_daTu4, options);
                //点击事件
                zhuTiTeMai_daTu4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(20).tag.get(0).linkUrl);
                    }
                });
                gd4.setAdapter(new MyZhuTiTeMai(getActivity(), zhuYe, options, 21));
                //点击事件
                gd4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TiaoZhuanUtil.tiaoZhuan(getActivity(), zhuYe.data.get(21).tag.get(i).linkUrl);
                    }
                });

            } else if (msg.what == 2) {

                int it = (int) msg.obj;
                vp.setCurrentItem(++it);

            }

        }


    };
    private MyGridView gd;
    private MyGridView gd1;
    private MyGridView gd2;
    private MyGridView gd3;
    private MyGridView gd4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.ragment1, container, false);
        //找控件
        kongJian(inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //解析数据
        shuJu();
        //自动轮播
        lunBo();

    }

    private void lunBo() {
        Timer t = new Timer();
        TimerTask tsk = new TimerTask() {
            @Override
            public void run() {
                int item = vp.getCurrentItem();
                Message ms = new Message();
                ms.obj = item;
                ms.what = 2;
                hd.sendMessage(ms);
            }
        };
        t.schedule(tsk, 4000, 4000);
        //原点
        for (int i = 0; i < 4; i++) {
            ImageView yuan = new ImageView(getActivity());
            if (i == 0) {
                yuan.setImageResource(R.drawable.shen);
            } else {
                yuan.setImageResource(R.drawable.qian);
            }
            LinearLayoutCompat.LayoutParams l = new LinearLayoutCompat.LayoutParams(20, 20);
            l.setMargins(5, 2, 5, 5);
            yuanDian.addView(yuan, l);
        }
        //监听viewpaget
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < 4; i++) {
                    //得到包含的孩子
                    ImageView childAt = (ImageView) yuanDian.getChildAt(i);
                    if (i == position % 4) {
                        childAt.setImageResource(R.drawable.shen);
                    } else {
                        childAt.setImageResource(R.drawable.qian);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    private void shuJu() {
        Request build = new Request.Builder().url("http://mock.eoapi.cn/success/jSWAxchCQfuhI6SDlIgBKYbawjM3QIga").build();
        ok.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getActivity(), "请求失败啦！！！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();

                Gson gs = new Gson();
                ZhuYeBean zhuYeBean = gs.fromJson(string, ZhuYeBean.class);
                Message msg = new Message();
                msg.obj = zhuYeBean;
                msg.what = 1;
                hd.sendMessage(msg);

            }
        });
    }

    private void kongJian(View inflate) {

        gd = (MyGridView) inflate.findViewById(R.id.aaaa);
        gd1 = (MyGridView) inflate.findViewById(R.id.bbbb);
        gd2 = (MyGridView) inflate.findViewById(R.id.cccc);
        gd3 = (MyGridView) inflate.findViewById(R.id.dddd);
        gd4 = (MyGridView) inflate.findViewById(R.id.eeee);
        yuanDian = (LinearLayout) inflate.findViewById(R.id.fragment1_yuandian);
        xiaoXi = (ImageView) inflate.findViewById(R.id.fragment1_xiaoxi);
        //消息的点击事件
        xiaoXi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DengLuActivity.class));
            }
        });
        //搜索按钮
        souSuo = (RelativeLayout) inflate.findViewById(R.id.fragment1_sousuo);
        souSuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SouSuoActivity.class));
            }
        });

        //扫描按钮
        saoMa = (ImageView) inflate.findViewById(R.id.fragment1_saomiao);
        saoMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SaoMiaoActivity.class));
            }
        });
        gridView = (MyGridView) inflate.findViewById(R.id.fragment1_gridview);
        vp = (ViewPager) inflate.findViewById(R.id.fragment1_viewpager);

        linearLayout = (LinearLayout) inflate.findViewById(R.id.fragment1_horizontalscrollview);
        shiYi = (ImageView) inflate.findViewById(R.id.fragment1_biaoti_shiyi);
        zhuTui = (ImageView) inflate.findViewById(R.id.fragment1_biaoti_zhutui);
        shiYi_tu1 = (ImageView) inflate.findViewById(R.id.fragment1_shiyi_1);
        shiYi_tu2 = (ImageView) inflate.findViewById(R.id.fragment1_shiyi_2);
        shiYi_tu3 = (ImageView) inflate.findViewById(R.id.fragment1_shiyi_3);
        shiYi_tu4 = (ImageView) inflate.findViewById(R.id.fragment1_shiyi_4);
        shiYi_tu5 = (ImageView) inflate.findViewById(R.id.fragment1_shiyi_5);
        shiYi_tu6 = (ImageView) inflate.findViewById(R.id.fragment1_shiyi_6);
        pinTuan_biaoTi = (ImageView) inflate.findViewById(R.id.fragment1_biaoti_pintuan);
        pinTuan_tu1 = (ImageView) inflate.findViewById(R.id.fragment1_biaoti_pintuan_1);
        pinTuan_tu2 = (ImageView) inflate.findViewById(R.id.fragment1_biaoti_pintuan_2);
        pinTuan_tu3 = (ImageView) inflate.findViewById(R.id.fragment1_biaoti_pintuan_3);
        pinTuan_tu4 = (ImageView) inflate.findViewById(R.id.fragment1_biaoti_pintuan_4);
        pinTuan_tu5 = (ImageView) inflate.findViewById(R.id.fragment1_biaoti_pintuan_5);


        chaKanGengDuo = (ImageView) inflate.findViewById(R.id.fragment1_chakangengduo);
        muYingZhuanQu = (ImageView) inflate.findViewById(R.id.fragment1_muYingZhuanQ);
        muYingZhuanQu_tu1 = (ImageView) inflate.findViewById(R.id.fragment1_muYingZhuanQ_tu1);
        muYingZhuanQu_tu2 = (ImageView) inflate.findViewById(R.id.fragment1_muYingZhuanQ_tu2);
        muYingZhuanQu_tu3 = (ImageView) inflate.findViewById(R.id.fragment1_muYingZhuanQ_tu3);
        muYingZhuanQu_tu4 = (ImageView) inflate.findViewById(R.id.fragment1_muYingZhuanQ_tu4);
        muYingZhuanQu_tu5 = (ImageView) inflate.findViewById(R.id.fragment1_muYingZhuanQ_tu5);
        muYingZhuanQu_tu6 = (ImageView) inflate.findViewById(R.id.fragment1_muYingZhuanQ_tu6);
        zhuTiTeMai = (ImageView) inflate.findViewById(R.id.fragment1_zhuTiTeMai);
        zhuTiTeMai_daTu1 = (ImageView) inflate.findViewById(R.id.fragment1_zhuTiTeMai_DaTu_1);
        zhuTiTeMai_huaDong1 = (LinearLayout) inflate.findViewById(R.id.fragment1_zhuTiTeMai_HuaDong_1);

        zhuTiTeMai_daTu2 = (ImageView) inflate.findViewById(R.id.fragment1_zhuTiTeMai_DaTu_2);
        zhuTiTeMai_huaDong2 = (LinearLayout) inflate.findViewById(R.id.fragment1_zhuTiTeMai_HuaDong_2);

        zhuTiTeMai_daTu3 = (ImageView) inflate.findViewById(R.id.fragment1_zhuTiTeMai_DaTu_3);
        zhuTiTeMai_huaDong3 = (LinearLayout) inflate.findViewById(R.id.fragment1_zhuTiTeMai_HuaDong_3);

        zhuTiTeMai_daTu4 = (ImageView) inflate.findViewById(R.id.fragment1_zhuTiTeMai_DaTu_4);
        zhuTiTeMai_huaDong4 = (LinearLayout) inflate.findViewById(R.id.fragment1_zhuTiTeMai_HuaDong_4);


    }

    //这是6.0版本后，动态添加权限，下面这几个方法是一起的
    @Override
    public void onStart() {//在生命周期里面
        super.onStart();
        requestCodeQrcodePermissions();//启动的时候调用这个主要的方法，
    }

    //得到请求权限结果
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    //拒绝
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQrcodePermissions() {
        String[] perms = {Manifest.permission.CAMERA};
        if (!EasyPermissions.hasPermissions(getActivity(), perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }

}
