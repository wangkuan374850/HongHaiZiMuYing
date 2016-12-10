package com.example.wangkuan.honghaizimuying;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangkuan.honghaizimuying.util.SuiJiShu;
import com.example.wangkuan.honghaizimuying.util.ZhengZeBiaoDaShi;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class YanZhengMaActivity extends AppCompatActivity {

    private ImageView fanHui;//返回按钮
    private TextView shouJi;//床来的手机号
    private EditText yanZhengMa;//
    private TextView miaoShu;//
    private EditText miMa;//
    private Button tiJiao;//
    private int daoJiShi = 60;
    private LinearLayout beiJing;
    private Timer t;
    private String shoujihao;
    private CheckBox shiFou;
    private OkHttpClient ok = new OkHttpClient();
    private String fourRandom;
    private String fourRandom1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yan_zheng_ma);
        fanHui = (ImageView) findViewById(R.id.shezhimima_fanhui);
        shouJi = (TextView) findViewById(R.id.shezhimima_shoujihao);
        yanZhengMa = (EditText) findViewById(R.id.shezhimima_yanZhengMa);
        miaoShu = (TextView) findViewById(R.id.shezhimima_miao);
        miMa = (EditText) findViewById(R.id.zhuCe_mima);
        tiJiao = (Button) findViewById(R.id.shezhimima_tijiao);
        beiJing = (LinearLayout) findViewById(R.id.shezhimima_beijing);
        shiFou = (CheckBox) findViewById(R.id.zhuCe_CheckBox);

        //注册订阅该事件
        EventBus.getDefault().register(this);
        //得到手机号

        Intent intent = getIntent();
        shoujihao = intent.getStringExtra("aaa");
        Log.i("aaaaaaaaaaaaaaaaaa", shoujihao);
        shouJi.setText("验证码已发送到" + shoujihao);
        //设置返回
        fanHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b = new AlertDialog.Builder(YanZhengMaActivity.this);
                b.setTitle("没注册，确定退出吗？");
                b.setPositiveButton("继续", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                b.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                b.show();
            }
        });
        //倒计时
        daoJiShi();
        //设置密码
        sheZhiMiMa();
        //显示密码
        xianShiMiMa();
        //bianse
        bianSe();
        //验证码
        huoQuYanZhengMa();

    }

    private void huoQuYanZhengMa() {

        FormBody.Builder fb = new FormBody.Builder();
        fb.add("apikey", "e813e4b63ba335ff0758e130e79cc4db");
        fb.add("mobile", shoujihao);
        fourRandom1 = SuiJiShu.getFourRandom();//生成的随机数
        fb.add("text", "【八维集团】您的验证码是" + fourRandom1);
        RequestBody rb = fb.build();
        Request request = new
                Request.Builder().url("https://sms.yunpian.com/v2/sms/single_send.json").post(rb).build();
        ok.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();//取出存在boty里面的数据
                EventBus.getDefault().post(string);//发布消息,直接把json串发布出去
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MainThread) //在ui线程执行
    public void getShuJu(String event) {
        Toast.makeText(getApplicationContext(), event, Toast.LENGTH_SHORT).show();
        Log.i("HHHHHHHHHHH", event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册，也就是取消该事件
    }

    private void bianSe() {

        miMa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //判断背景色
                if (!miMa.getText().toString().trim().isEmpty()) {
                    //设置背景
                    tiJiao.setBackgroundColor(Color.parseColor("#ffff99"));

                } else {
                    tiJiao.setBackgroundColor(Color.parseColor("#cccccc"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void xianShiMiMa() {

        shiFou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = shiFou.isChecked();
                if (checked) {
                    miMa.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    miMa.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    private void sheZhiMiMa() {
        //提交按钮
        tiJiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //得到输入的字符
                String trim = miMa.getText().toString().trim();
                String trim1 = yanZhengMa.getText().toString().trim();

                if (!trim.isEmpty() && !trim1.isEmpty()) {//里面有参数
                    if (trim1.equals(fourRandom1)) {
                        Toast.makeText(YanZhengMaActivity.this, "验证码输入成功", Toast.LENGTH_SHORT).show();
                        //正则判断
                        boolean result = ZhengZeBiaoDaShi.getResult("^[a-zA-Z][a-zA-Z0-9_]{4,15}$", trim);
                        if (result) {
                            Toast.makeText(YanZhengMaActivity.this, "输入成功", Toast.LENGTH_SHORT).show();
                            //进入登录页面
                            Intent intent = new Intent(YanZhengMaActivity.this, DengLuActivity.class);
                            intent.putExtra("zh", shoujihao);
                            intent.putExtra("mm", trim);
                            startActivity(intent);
                        } else {
                            Toast.makeText(YanZhengMaActivity.this, "密码格式不对", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(YanZhengMaActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

    }

    private void daoJiShi() {
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (daoJiShi > 0) {
                            daoJiShi--;
                            miaoShu.setText(daoJiShi + "秒");
                        } else {
                            t.cancel();
                            miaoShu.setBackgroundColor(Color.parseColor("#ffff99")); //设置背景
                            miaoShu.setText("重新发送");
                            miaoShu.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    huoQuYanZhengMa();
                                    daoJiShi = 60;
                                    miaoShu.setBackgroundColor(Color.parseColor("#cccccc")); //设置背景
                                    daoJiShi();
                                }
                            });
                        }
                    }
                });
            }
        }, 1000, 1000);
    }
}
