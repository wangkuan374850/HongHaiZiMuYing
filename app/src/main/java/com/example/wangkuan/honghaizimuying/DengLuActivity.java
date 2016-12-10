package com.example.wangkuan.honghaizimuying;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangkuan.honghaizimuying.util.ZhengZeBiaoDaShi;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.util.Map;
import java.util.Set;

public class DengLuActivity extends AppCompatActivity {

    private ImageView fanHui;//返回按钮
    private EditText zhangHao;//账号输入
    private EditText miMa;//密码输入
    private CheckBox shiFou;//是否记住密码
    private Button dengLu;//登录按钮
    private TextView zhuChe;
    private ImageButton qq;
    private ImageButton weiXin;
    private ImageButton fenXing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deng_lu);
        //找控件
        fanHui = (ImageView) findViewById(R.id.fragment4_fanhui);
        zhangHao = (EditText) findViewById(R.id.fragment4_zhangHao);
        miMa = (EditText) findViewById(R.id.fragment4_miMa);
        shiFou = (CheckBox) findViewById(R.id.fragment4_checkbox);
        dengLu = (Button) findViewById(R.id.fragment4_dengLu);
        zhuChe = (TextView) findViewById(R.id.fragment4_zhuCe);
        qq = (ImageButton) findViewById(R.id.fragment4_qq);
        weiXin = (ImageButton) findViewById(R.id.fragment4_weixin);
        fenXing = (ImageButton) findViewById(R.id.fragment4_fenxiang);
        //账号判断，长度不少于6不大于18，正则表达式判断手机号

        dengLu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = zhangHao.getText().toString().trim();//得到输入的账号
                boolean result = ZhengZeBiaoDaShi.getResult("^(0|86|17951)?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$", trim);
                if (result) {
                    Toast.makeText(DengLuActivity.this, "输入成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DengLuActivity.this, "输入失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //免费注册
        zhuChe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到注册页面
                startActivity(new Intent(DengLuActivity.this, ZhuCeActivity.class));
            }
        });
        //点击返回按钮
        fanHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //得到意图
        sheZhiZhangHao();
        //输入框监听
        shuRuJianTing();
        //显示密码
        xianShiMiMa();
        //qq登录
        QQdengLu();
        //分享
        QQFenXiang();
    }

    private void QQFenXiang() {

        fenXing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareAction shareAction=new ShareAction(DengLuActivity.this);
                ShareBoardConfig config = new ShareBoardConfig();
                config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR);
                config.setCancelButtonVisibility(true);

                shareAction.open(config);


                shareAction .setPlatform(SHARE_MEDIA.QQ)
                        .withText("hello!北京").withTitle("G噶噶")
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onResult(SHARE_MEDIA share_media) {
                                Toast.makeText(DengLuActivity.this, " 分享成功啦", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        })
                        .share();
            }
        });
    }

    private void QQdengLu() {
        //58463091f29d984027000125
        qq.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      UMShareAPI mShareAPI = UMShareAPI.get(DengLuActivity.this);
                                      mShareAPI.getPlatformInfo(DengLuActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                                                  @Override
                                                  public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> data) {

                                                      Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
                                                      // stub
                                                      Set<String> keySet = data
                                                              .keySet();
                                                      // 得到头像
                                                      String iconurl = new String();
                                                      // 得到昵称
                                                      String screenname = new String();
                                                      for (String string : keySet) {
                                                          if (string
                                                                  .equals("screen_name")) {
                                                              // 获取登录的名字
                                                              screenname = data
                                                                      .get("screen_name");
                                                              Toast.makeText(getApplicationContext(), screenname, Toast.LENGTH_SHORT).show();

                                                          }
                                                          if (string
                                                                  .equals("profile_image_url")) {
                                                              // 获取登录的图片
                                                              iconurl = data
                                                                      .get("profile_image_url");
                                                              Toast.makeText(getApplicationContext(), iconurl, Toast.LENGTH_SHORT).show();

                                                          }
                                                      }
                                                  }

                                                  @Override
                                                  public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                                                  }

                                                  @Override
                                                  public void onCancel(SHARE_MEDIA share_media, int i) {

                                                  }
                                              }

                                      );
                                  }
                              }

        );
        weiXin.setOnClickListener(new View.OnClickListener()

                                  {
                                      @Override
                                      public void onClick(View view) {
                                          UMShareAPI mShareAPI = UMShareAPI.get(DengLuActivity.this);
                                          mShareAPI.doOauthVerify(DengLuActivity.this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
                                              @Override
                                              public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                                                  Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
                                              }

                                              @Override
                                              public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                                                  Toast.makeText(getApplicationContext(), "失败", Toast.LENGTH_SHORT).show();
                                              }

                                              @Override
                                              public void onCancel(SHARE_MEDIA share_media, int i) {
                                                  Toast.makeText(getApplicationContext(), "ddddddddddd", Toast.LENGTH_SHORT).show();
                                              }
                                          });
                                      }
                                  }

        );
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

    private void shuRuJianTing() {
        zhangHao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //判断背景色
                if (!zhangHao.getText().toString().trim().isEmpty()) {
                    //设置背景
                    dengLu.setBackgroundColor(Color.parseColor("#ffff99"));

                } else {
                    dengLu.setBackgroundColor(Color.parseColor("#cccccc"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void sheZhiZhangHao() {
        Intent intent = getIntent();
        String zh = intent.getStringExtra("zh");
        String mm = intent.getStringExtra("mm");
        zhangHao.setText(zh);
        miMa.setText(mm);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //设置背景
        dengLu.setBackgroundColor(Color.parseColor("#ffff99"));
    }

    //扣扣第三方的回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}
