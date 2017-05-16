package cn.bisondev.tobewechat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.promeg.pinyinhelper.Pinyin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import cn.bisondev.tobewechat.bean.FriendBean;
import cn.bisondev.tobewechat.utils.GlideImageLoader;
import cn.bisondev.tobewechat.utils.PinyinUtils;
import cn.bisondev.tobewechat.R;

/**
 * 通讯录的填充Adapter
 * Created by Bison on 2017/5/13.
 */

public class FriendsListAdapter extends RecyclerView.Adapter {

    private List<FriendBean> mDatas;
    private HashMap<String, Integer> letterIndexes;
    private String[] sections;
    private List<Integer> headIndex;
    private Context context;

    private static final String TAG = "FriendsListAdapter";

    public FriendsListAdapter(Context context, List<FriendBean> datas) {
        this.context = context;
        this.mDatas = datas;

        if(null == datas) {
            mDatas = new ArrayList<>();
        }

        Collections.sort(mDatas, new Comparator<FriendBean>() {
            @Override
            public int compare(FriendBean o1, FriendBean o2) {
                //按字母对集合进行排序
                String pinyin1 = Pinyin.toPinyin(o1.getName().charAt(0));
                String pinyin2 = Pinyin.toPinyin(o2.getName().charAt(0));

                String firstLetter1 = PinyinUtils.getFirstLetter(pinyin1);
                String firstLetter2 = PinyinUtils.getFirstLetter(pinyin2);

                //把"#"的组放在最后
                if(firstLetter1.equals("#")) {
                    return firstLetter2.compareTo(firstLetter1);
                } else {        //按字母顺序排
                    return firstLetter1.compareTo(firstLetter2);
                }
            }
        });



        int size = datas.size();
        letterIndexes = new HashMap<>();
        sections = new String[size];
        String previousLetter = "";
        headIndex = new ArrayList();

        for(int index = 0; index < size; index ++) {
            String pinyin = Pinyin.toPinyin(mDatas.get(index).getName().charAt(0));
            String currentLetter = PinyinUtils.getFirstLetter(pinyin);

            //只记录第一个
            if (!TextUtils.equals(currentLetter, previousLetter)){
                letterIndexes.put(currentLetter, index + 4);
                sections[index] = currentLetter;
                headIndex.add(index + 4);
            }
            previousLetter = currentLetter;
        }

        //往顶部插入置顶item
        mDatas.add(0, new FriendBean(R.drawable.ic_new_friend, "新的朋友"));
        mDatas.add(1, new FriendBean(R.drawable.ic_group_chat, "群聊"));
        mDatas.add(2, new FriendBean(R.drawable.ic_label, "标签"));
        mDatas.add(3, new FriendBean(R.drawable.ic_public_account, "公众号"));
    }

    @Override
    public int getItemViewType(int position) {
        if(position < 4) {
            return 0;
        }
        if(headIndex.contains(position)) {
            return 1;
        }else {
            return 2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            //类型是1，说明这是第一个元素
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.head_item, parent, false);
                break;
            //否则采用默认的布局
            default:
                view = LayoutInflater.from(context).inflate(R.layout.normal_item, parent, false);
                break;
        }
        return new FriendsHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FriendBean data = mDatas.get(position);
        int type = holder.getItemViewType();

        //填充图片
        ImageView iv = (ImageView) holder.itemView.findViewById(R.id.item_image_view);

        switch (type) {
            //Top
            case 0:
                GlideImageLoader.displayImage(context, data.getAvactorResId(), iv);
                break;
            //headItem
            case 1:
                TextView tvHead = (TextView)holder.itemView.findViewById(R.id.tv_head);
                String first = Pinyin.toPinyin(data.getName().charAt(0));
                String firstLetter = PinyinUtils.getFirstLetter(first);

                tvHead.setText(firstLetter);
                GlideImageLoader.displayImage(context, data.getAvactorUrl(), iv);
                break;
            //NormalItem
            case 2:
                GlideImageLoader.displayImage(context, data.getAvactorUrl(), iv);
                break;
        }

        //填充文字
        TextView tv = (TextView) holder.itemView.findViewById(R.id.item_text_view);
        tv.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 获取字母索引的位置
     * @param letter
     * @return
     */
    public int getLetterPosition(String letter){
        //从HashMap中取出相应的下标值
        Integer integer = letterIndexes.get(letter);
        return integer == null ? -1 : integer;
    }

    class FriendsHolder extends RecyclerView.ViewHolder{

        ImageView ivAvactor;
        TextView tvName;
        TextView tvHead;

        public FriendsHolder(View itemView) {
            super(itemView);
            ivAvactor = (ImageView) itemView.findViewById(R.id.item_image_view);
            tvName = (TextView) itemView.findViewById(R.id.item_text_view);
            tvHead = (TextView) itemView.findViewById(R.id.tv_head);
        }
    }
}
