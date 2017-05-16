package cn.bisondev.tobewechat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.bisondev.tobewechat.bean.ChatWindowBean;
import cn.bisondev.tobewechat.utils.GlideImageLoader;
import cn.bisondev.tobewechat.R;

/**
 * Created by Bison on 2017/5/12.
 */

public class ChatWindowAdapter extends RecyclerView.Adapter<ChatWindowAdapter.MyViewHolder> {

    private ArrayList<ChatWindowBean> mArrayList;
    private Context mContext;

    public ChatWindowAdapter(ArrayList<ChatWindowBean> arrayList, Context context){
        mArrayList = arrayList;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.chat_item, parent,
                        false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GlideImageLoader.displayImage(mContext, mArrayList.get(position).getAvactor(), holder.ivAvactor);

        holder.tvName.setText(mArrayList.get(position).getChatName());
        holder.tvContent.setText(mArrayList.get(position).getChatContent());
        holder.tvTime.setText(mArrayList.get(position).getChatTime());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView ivAvactor;
        ImageView ivNotify;
        TextView tvName;
        TextView tvContent;
        TextView tvTime;

        public MyViewHolder(View view)
        {
            super(view);
            ivAvactor = (ImageView) view.findViewById(R.id.item_avactor);
            ivNotify = (ImageView) view.findViewById(R.id.chat_notify);
            tvName = (TextView) view.findViewById(R.id.chat_name);
            tvContent = (TextView) view.findViewById(R.id.chat_content);
            tvTime = (TextView) view.findViewById(R.id.chat_time);
        }
    }
}
