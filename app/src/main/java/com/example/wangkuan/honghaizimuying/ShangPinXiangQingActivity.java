package com.example.wangkuan.honghaizimuying;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.wangkuan.honghaizimuying.db.ZengShanGaiCha;
import com.example.wangkuan.honghaizimuying.fragment.PingJia;
import com.example.wangkuan.honghaizimuying.fragment.ShangPin;
import com.example.wangkuan.honghaizimuying.fragment.XingQing;
import com.example.wangkuan.honghaizimuying.util.OrderInfoUtil2_0;
import com.example.wangkuan.honghaizimuying.zhifu.PayResult;

import java.util.ArrayList;
import java.util.Map;

public class ShangPinXiangQingActivity extends AppCompatActivity {

    private ImageView fanHui;
    private TextView shangPin;
    private TextView xiangQing;
    private TextView pingJia;
    private ImageView gouWuChe;
    private ImageView lieBiao;
    private ViewPager viewPger;
    private Button keFu;
    private Button shouCang;
    private Button dianPu;
    private TextView liJi;
    private TextView jiaRu;
    private ArrayList<TextView> ls = new ArrayList<TextView>();
    private TextView liJiGouMai;//立即购买按钮，点击第三方支付

    // 商户PID
    public static final String APPID = "2088901305856832";
    // 商户收款账号
    public static final String SELLER = "8@qdbaiu.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM" +
            "/KCxg/OIj6er2GEig0DOkHqBSzEPHGigMbTXP1k2nrxEHeE59xOOuy" +
            "ovQH/A1LgbuVKyOac3uAN4GXIBEoozRVzDhu5dobeNm48BPcpYSAfvN3K" +
            "/5GLacvJeENqsiBx8KufM/9inlHaDRQV7WhX1Oe2ckat1EkdHwxxQgc" +
            "36NhAgMBAAECgYEAwn3sWpq6cUR65LD8h9MIjopTImTlpFjgz72bhsHD" +
            "ZK6A+eJDXcddrwh7DI34t/0IBqu+QEoOc/f0fIEXS9hMwTvFY59XG7M8" +
            "M6SdeaAbemrGmZ1IdD6YDmpbQFHn2ishaYF0YDZIkBS3WLDFrtk/efaar" +
            "BCpGAVXeEiVQE4LewECQQD5W1rpkq+dHDRzzdtdi1bJ479wun5CfmVDV" +
            "b2CJH7Iz0t8zyp/iEVV2QELnxZMphmoSzKaLXutTTj2OImpzCtRAkEA1" +
            "VMxG6nQq9NkU51H1+I3mlUXRZ0XeFA1GFJ7xWpNRAVhEWlDz2zy9v/g" +
            "X+RFpNC3f5uznycas70Xp78ew43TEQJAZFFqi9mlqTF1sLk67bFnIyX" +
            "rGPEOZrXvC13tNfR0xVkQZ4/46wHp0xXQo9pG4GNaoyhNnVV7EkelCPn" +
            "J+HPZYQJAQh6T9QgQZoGR8hyovPAf3dUL7oa/VIo/urcuJ8VIB5JHQNdI" +
            "rk0NjaNHj1E4iNosVgATj3vWWel9IIArb99QkQJAKvfm78lwnImtg5IM6" +
            "04hdn/Wu1XF8tpxsKLWcnfchMr0bM9rCmKmhAY+wdmqSyPZRiNb1QaaaD" +
            "TqJxLy6AnQ+Q==";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCd6rV3vOE578e6V" +
            "lGEakZpPdsX2QmGdIfi/yHe cg1CIEWzX9wn2LNFGtu1EzYQyKACG/RKeog0pUJEVGfBG30zFdNY2YocYJNdPtA" +
            "DqhJbS0GJm7f8 1vRiLKtOwKjdiz9oMEwxhc/5fysfMbercidRmlCDPU9BNL1UPb9bAx25JwIDAQAB";
    private static final int SDK_PAY_FLAG = 1;

    private TextView jiaRuGouWuChe;

    private int aa = 1;
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(ShangPinXiangQingActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ShangPinXiangQingActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

                default:
                    break;
            }
        }

        ;
    };
    private ZengShanGaiCha zsgc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shang_pin_xiang_qing);

        //初始化数据库
        zsgc = new ZengShanGaiCha(getApplicationContext());

        //找控件
        kongJian();
        //设置ViewaPager
        sheZhiViewPager();
        //点击事件
        dianJiShiJian();


    }

    private void dianJiShiJian() {

        jiaRuGouWuChe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                zsgc.getzeng("尿不湿", (int) (100 + Math.random() * 300));
                Toast.makeText(getApplicationContext(), "添加成功啦！！！", Toast.LENGTH_SHORT).show();
            }
        });


        //把数据放到奥集合中
        ls.add(shangPin);
        ls.add(xiangQing);
        ls.add(pingJia);
        //监听viewpager
        viewPger.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < ls.size(); i++) {
                    if (position == i) {
                        ls.get(i).setTextColor(Color.RED);
                    } else {
                        ls.get(i).setTextColor(Color.BLACK);
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
        shangPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPger.setCurrentItem(0);
            }
        });
        xiangQing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPger.setCurrentItem(1);
            }
        });
        pingJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPger.setCurrentItem(2);
            }
        });

        liJiGouMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShangPinXiangQingActivity.this, "购买成功啦！！！", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(APPID) || TextUtils.isEmpty(RSA_PRIVATE)) {
                    new AlertDialog.Builder(ShangPinXiangQingActivity.this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    //
                                    finish();
                                }
                            }).show();
                    return;
                }

                /**
                 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
                 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
                 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
                 *
                 * orderInfo的获取必须来自服务端；
                 */
                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID);
                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
                String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);
                final String orderInfo = orderParam + "&" + sign;

                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(ShangPinXiangQingActivity.this);
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Log.i("msp", result.toString());

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }

        });


    }

    private void sheZhiViewPager() {
        viewPger.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment f = null;
                switch (position) {
                    case 0:
                        f = new ShangPin();
                        break;
                    case 1:
                        f = new XingQing();
                        break;
                    case 2:
                        f = new PingJia();
                        break;
                }

                return f;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });


    }

    private void kongJian() {
        fanHui = (ImageView) findViewById(R.id.xiangqingye_fanHui);
        shangPin = (TextView) findViewById(R.id.xiangqingye_shangPin);
        xiangQing = (TextView) findViewById(R.id.xiangqingye_xiangQing);
        pingJia = (TextView) findViewById(R.id.xiangqingye_pingJia);
        gouWuChe = (ImageView) findViewById(R.id.xiangqingye_gouWuChe);
        lieBiao = (ImageView) findViewById(R.id.xiangqingye_lieBiao);
        viewPger = (ViewPager) findViewById(R.id.xiangqingye_viewPager);
        keFu = (Button) findViewById(R.id.xiangqingye_keFu);
        shouCang = (Button) findViewById(R.id.xiangqingye_shouCang);
        dianPu = (Button) findViewById(R.id.xiangqingye_dianPu);
        liJi = (TextView) findViewById(R.id.xiangqingye_liJiGouMai);
        jiaRu = (TextView) findViewById(R.id.xiangqingye_jiaRuGouWuChe);
        liJiGouMai = (TextView) findViewById(R.id.xiangqingye_liJiGouMai);
        jiaRuGouWuChe = (TextView) findViewById(R.id.xiangqingye_jiaRuGouWuChe);
    }
}
