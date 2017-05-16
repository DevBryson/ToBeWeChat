package cn.bisondev.tobewechat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.bisondev.tobewechat.adapter.ChatWindowAdapter;
import cn.bisondev.tobewechat.bean.ChatWindowBean;
import cn.bisondev.tobewechat.widget.MyItemDecoration;
import cn.bisondev.tobewechat.R;

/**
 * Created by Bison on 2017/5/12.
 */

public class WechatFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<ChatWindowBean> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_wechat,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mList = new ArrayList<>();

        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new MyItemDecoration(getContext(), MyItemDecoration.HORIZONTAL_LIST));

        String[] avactorList = getResources().getStringArray(R.array.contactAvactorUrl);

        ChatWindowBean bean;
        for(int i = 0; i < avactorList.length; i++){
            bean = new ChatWindowBean();

            bean.setAvactor(avactorList[i]);
            bean.setChatName("对话"+i);
            bean.setChatContent("测试对话内容");
            bean.setChatTime("23:00");
            mList.add(bean);
        }

        mRecyclerView.setAdapter(new ChatWindowAdapter(mList, getContext()));
    }
}
