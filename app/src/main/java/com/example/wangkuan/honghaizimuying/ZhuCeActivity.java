package com.example.wangkuan.honghaizimuying;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangkuan.honghaizimuying.util.ZhengZeBiaoDaShi;

public class ZhuCeActivity extends AppCompatActivity {

    private ImageView fanHui;//返回按钮
    private EditText shouJiHao;//手机号
    private TextView xieYi;//协议
    private Button xiaYiBu;//下一步

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        fanHui = (ImageView) findViewById(R.id.zhuCe_fanhui);
        shouJiHao = (EditText) findViewById(R.id.zhuCe_shouJiHao);
        xieYi = (TextView) findViewById(R.id.zhuCe_xieYi);
        xiaYiBu = (Button) findViewById(R.id.zhuCe_xiaYiBu);


        //给EditText加点击事件
        shouJiHao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                //判断背景色
                if (!shouJiHao.getText().toString().trim().isEmpty()) {
                    //设置背景
                    xiaYiBu.setBackgroundColor(Color.parseColor("#ffff99"));
                } else {
                    xiaYiBu.setBackgroundColor(Color.parseColor("#cccccc"));
                }
                //给输入的手机号增加空格
                if (charSequence == null || charSequence.length() == 0)
                    return;
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < charSequence.length(); i++) {
                    if (i != 3 && i != 8 && charSequence.charAt(i) == ' ') {
                        continue;
                    } else {
                        stringBuilder.append(charSequence.charAt(i));
                        if ((stringBuilder.length() == 4 || stringBuilder.length() == 9)
                                && stringBuilder.charAt(stringBuilder.length() - 1) != ' ') {
                            stringBuilder.insert(stringBuilder.length() - 1, ' ');
                        }
                    }
                }
                if (!stringBuilder.toString().equals(charSequence.toString())) {
                    int index = start + 1;
                    if (stringBuilder.charAt(start) == ' ') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }
                    shouJiHao.setText(stringBuilder.toString());
                    shouJiHao.setSelection(index);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        //点击下一步判断手机号是不是正确的
        xiaYiBu.setOnClickListener(new View.OnClickListener() {

            private String replace;

            @Override
            public void onClick(View view) {
                String s = shouJiHao.getText().toString().trim();
                //把里面的空格替换成null
                replace = s.replace(" ", "");

                if (!replace.isEmpty()) {
                    boolean result = ZhengZeBiaoDaShi.getResult("^(0|86|17951)?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$", replace);
                    if (result) {
                        Toast.makeText(ZhuCeActivity.this, "输入成功", Toast.LENGTH_SHORT).show();
                        Log.i("bbbbbbbbbbbbbbbbb", replace);
                        Intent intent = new Intent(ZhuCeActivity.this, YanZhengMaActivity.class);
                        intent.putExtra("aaa", replace);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ZhuCeActivity.this, "手机号错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ZhuCeActivity.this, "手机号不能为空哦！！！", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //设置协议的字体，，，
        String trim = xieYi.getText().toString().trim();
        SpannableString msp = new SpannableString(trim);
        msp.setSpan(new UnderlineSpan(), 2, 10,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        msp.setSpan(new BackgroundColorSpan(Color.YELLOW), 2, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ClickableSpan rightClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(ZhuCeActivity.this, "我是苏宁易购会员章程", Toast.LENGTH_SHORT).show();
            }
        };
        msp.setSpan(rightClickableSpan, 2, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msp.setSpan(new UnderlineSpan(), 11, 16,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //设置点击事件给文本
        ClickableSpan rightClickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(ZhuCeActivity.this, "我是易付宝协议", Toast.LENGTH_SHORT).show();
            }
        };
        msp.setSpan(rightClickableSpan1, 11, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msp.setSpan(new BackgroundColorSpan(Color.YELLOW), 11, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        xieYi.setText(msp);


        //返回按钮
        fanHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
