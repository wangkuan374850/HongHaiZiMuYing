package com.example.wangkuan.honghaizimuying.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.wangkuan.honghaizimuying.R;
import com.example.wangkuan.honghaizimuying.bean.GouWuCheBean;
import com.example.wangkuan.honghaizimuying.db.ZengShanGaiCha;
import com.example.wangkuan.honghaizimuying.util.OrderInfoUtil2_0;
import com.example.wangkuan.honghaizimuying.zhifu.PayResult;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by wangkuan on 2016/11/8.
 */
public class FragMent3 extends Fragment {

    private TextView bianJi;//编辑按钮
    private ListView lv;//listview
    private CheckBox quanXuanCheckBox;
    private TextView quanXuan;
    private Button jieSuan;
    private TextView qian;
    private TextView geShu;
    private TextView heJi;
    private boolean kk = false;//记录点击编辑的状态
    private boolean kk1 = false;//记录点击编辑的状态
    private ArrayList<GouWuCheBean> ls = new ArrayList<GouWuCheBean>();
    private ZengShanGaiCha zsgc;
    private ArrayList<GouWuCheBean> chaXun;
    private int zongjia;
    private ShiPeiQi spq;
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
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

                default:
                    break;
            }
        }

        ;
    };
    private Button shanChu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.ragment3, container, false);
        bianJi = (TextView) inflate.findViewById(R.id.fragment3_bianji);
        lv = (ListView) inflate.findViewById(R.id.fragment3_listview);
        quanXuanCheckBox = (CheckBox) inflate.findViewById(R.id.fragment3_CheckBox);
        quanXuan = (TextView) inflate.findViewById(R.id.fragment3_quanxuan);
        geShu = (TextView) inflate.findViewById(R.id.fragment3_geshu);
        jieSuan = (Button) inflate.findViewById(R.id.fragment3_jiesuan);
        qian = (TextView) inflate.findViewById(R.id.fragment3_qian);
        heJi = (TextView) inflate.findViewById(R.id.fragment3_heji);
        shanChu = (Button) inflate.findViewById(R.id.fragment3_shanchu);
        return inflate;
    }

    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //


        //编辑按钮
        bianJiAanNiu();
        //调用数据库
        zsgc = new ZengShanGaiCha(getActivity());
        chaXun = zsgc.getChaXun();//数据库数据
        if (spq == null) {
            spq = new ShiPeiQi();
            lv.setAdapter(spq);
        } else {
            spq.notifyDataSetChanged();
        }

    }

    class ShiPeiQi extends BaseAdapter {

        @Override
        public int getCount() {
            return chaXun.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int mm, View view, ViewGroup viewGroup) {
            View inflate = View.inflate(getActivity(), R.layout.gouwuche_shangpin, null);
            TextView tv1 = (TextView) inflate.findViewById(R.id.gouwuche_tv1);
            TextView tv2 = (TextView) inflate.findViewById(R.id.gouwuche_tv2);
            final CheckBox cb = (CheckBox) inflate.findViewById(R.id.gouwuche_CheckBox);
            tv1.setText(chaXun.get(mm).getName());
            tv2.setText(chaXun.get(mm).getTuPian() + "");
            cb.setChecked(chaXun.get(mm).isXuanZhong());
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chaXun.get(mm).setXuanZhong(cb.isChecked());
                    //得到状态
                    boolean checked = cb.isChecked();
                    if (checked) {
                        chaXun.get(mm).setXuanZhong(true);

                        zongjia = zongjia + chaXun.get(mm).getTuPian();
                    } else {
                        chaXun.get(mm).setXuanZhong(false);
                        zongjia = zongjia - chaXun.get(mm).getTuPian();
                    }
                    qian.setText(zongjia + "");
                }
            });
//点击删除,这个是在适配器械的
            shanChu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (chaXun.get(mm).isXuanZhong() == true) {
                        zsgc.getShanChu(chaXun.get(mm).getTuPian());
                    }
                    spq.notifyDataSetChanged();
                }
            });

            return inflate;
        }
    }


    private void bianJiAanNiu() {
        bianJi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kk == false) {
                    geShu.setVisibility(View.VISIBLE);
                    qian.setVisibility(View.GONE);
                    heJi.setVisibility(View.GONE);
                    jieSuan.setVisibility(View.GONE);
                    shanChu.setVisibility(View.VISIBLE);
                    bianJi.setText("完成");
                    kk = true;
                } else {
                    geShu.setVisibility(View.GONE);
                    qian.setVisibility(View.VISIBLE);
                    heJi.setVisibility(View.VISIBLE);
                    jieSuan.setVisibility(View.VISIBLE);
                    shanChu.setVisibility(View.GONE);
                    bianJi.setText("编辑");
                    kk = false;
                }

            }
        });

        //点击全选
        quanXuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (kk1 == false) {
                    quanXuan.setText("取消");
                    quanXuanCheckBox.setChecked(true);
                    for (int i = 0; i < chaXun.size(); i++) {
                        chaXun.get(i).setXuanZhong(true);
                        zongjia = zongjia + chaXun.get(i).getTuPian();
                    }
                    qian.setText(zongjia + "");
                    spq.notifyDataSetChanged();
                    kk1 = true;
                } else {
                    quanXuan.setText("全选");
                    quanXuanCheckBox.setChecked(false);
                    for (int i = 0; i < chaXun.size(); i++) {
                        chaXun.get(i).setXuanZhong(false);
                        zongjia = 0;
                    }

                    qian.setText(0 + "");
                    spq.notifyDataSetChanged();
                    kk1 = false;
                }


            }
        });

        //结算按钮
        jieSuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "购买成功啦！！！", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(APPID) || TextUtils.isEmpty(RSA_PRIVATE)) {
                    new AlertDialog.Builder(getActivity()).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {

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
                        PayTask alipay = new PayTask(getActivity());
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
}
