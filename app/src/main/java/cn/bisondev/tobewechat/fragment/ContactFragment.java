package cn.bisondev.tobewechat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bisondev.tobewechat.bean.FriendBean;
import cn.bisondev.tobewechat.adapter.FriendsListAdapter;
import cn.bisondev.tobewechat.widget.MyItemDecoration;
import cn.bisondev.tobewechat.R;
import cn.bisondev.tobewechat.widget.SideLetterBar;

/**
 * Created by Bison on 2017/5/12.
 */

public class ContactFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SideLetterBar mLetterBar;
    private FriendsListAdapter mAdapter;
    private List<FriendBean> mList;
    private Context mContext;

    private static final String TAG = "ContactFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact,container,false);

        mContext = getContext();

        initData();
        initView(view);
        return view;
    }

    /**
     * 初始化测试数据
     */
    private void initData() {
        mList = new ArrayList<>();

        String[] urls = getResources().getStringArray(R.array.contactAvactorUrl);
        String[] names = getResources().getStringArray(R.array.aontactName);

        for(int i = 0; i < urls.length; i++) {
            mList.add(new FriendBean(urls[i], names[i]));
        }
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.friend_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter = new FriendsListAdapter(getContext(), mList));
        mRecyclerView.addItemDecoration(new MyItemDecoration(getContext(), MyItemDecoration.HORIZONTAL_LIST));

        TextView overlay = (TextView) view.findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) view.findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                switch (letter) {
                    case "↑":
                        mRecyclerView.scrollToPosition(0);
                        break;
                    case "☆":
                        mRecyclerView.scrollToPosition(4);
                        break;
                    default:
                        //通过点击的字母获取相应的索引
                        int position = mAdapter.getLetterPosition(letter);

                        Log.e(TAG, "onLetterChanged: " + position);

                        mRecyclerView.scrollToPosition(position);
                        break;
                }
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
