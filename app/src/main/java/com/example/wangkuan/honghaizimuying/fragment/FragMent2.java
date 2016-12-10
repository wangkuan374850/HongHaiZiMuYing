package com.example.wangkuan.honghaizimuying.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangkuan.honghaizimuying.R;
import com.example.wangkuan.honghaizimuying.adapter.FeiLeiBiaoTiAdapter;
import com.example.wangkuan.honghaizimuying.adapter.FeiLeiNeiRongAdapter;
import com.example.wangkuan.honghaizimuying.bean.FeiLieBean;
import com.example.wangkuan.honghaizimuying.util.ImageLoaderUtils;
import com.example.wangkuan.honghaizimuying.util.ZhuanHuanLiu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.solidfire.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wangkuan on 2016/11/8.
 */
/**
 * autour: 王广宽       
 * date: 2016/11/23 20:48   
 * update: 2016/11/23   
 */
/**
 * autour: 王广宽       
 * date: 2016/11/23 20:49   
 * update: 2016/11/23   
 * explain:
 */
public class FragMent2 extends Fragment {

    private RecyclerView biaoTi;
    private RecyclerView neiRong;
    private TextView tv;
    private FeiLeiBiaoTiAdapter feiLeiBiaoTiAdapter;
    private FeiLieBean feiLieBean;
    private DisplayImageOptions options = ImageLoaderUtils.initOptions();
    private FeiLeiNeiRongAdapter feiLeiNeiRongAdapter;
    private int ye = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.ragment2, container, false);
        biaoTi = (RecyclerView) inflate.findViewById(R.id.fragment2_biaoti);
        biaoTi.setLayoutManager(new LinearLayoutManager(getActivity()));

        neiRong = (RecyclerView) inflate.findViewById(R.id.fragment2_neirong);
        neiRong.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        tv = (TextView) inflate.findViewById(R.id.fragment2_sousuo);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //加载本地文件
        try {
            InputStream open = getResources().getAssets().open("category.txt");
            String s = ZhuanHuanLiu.zhuanHuan(open);
            Gson gs = new Gson();
            feiLieBean = gs.fromJson(s, FeiLieBean.class);
            feiLeiBiaoTiAdapter = new FeiLeiBiaoTiAdapter(feiLieBean, getActivity());
            biaoTi.setAdapter(feiLeiBiaoTiAdapter);
            //调接口
            feiLeiBiaoTiAdapter.setOnItemClickListener(new FeiLeiBiaoTiAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, String data) {
                    Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
                    ye = Integer.parseInt(data);
                    Log.i("aaaaaaaaaaaaa", feiLieBean.rs.get(0).children.get(0).dirName);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        //内容的适配器


        feiLeiNeiRongAdapter = new FeiLeiNeiRongAdapter(feiLieBean, getActivity(), ye, options);
      //  neiRong.setAdapter(feiLeiNeiRongAdapter);


    }

}
