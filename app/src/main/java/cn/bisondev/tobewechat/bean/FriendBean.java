package cn.bisondev.tobewechat.bean;

import android.support.annotation.NonNull;

/**
 * 通讯录中朋友的bean类
 * Created by Bison on 2017/5/13.
 */

public class FriendBean implements Comparable<String>{

    //item类型
    private int type = 1;   //1就是默认通讯录好友item，0就是默认置顶item,2就是分隔的item
    //头像地址
    private String avactorUrl;
    //头像的资源Id
    private int avactorResId;
    //名字
    private String name;

    public FriendBean(String avactorUrl, String name) {
        this.avactorUrl = avactorUrl;
        this.name = name;
    }

    public FriendBean(int avactorResId, String name) {
        this.avactorResId = avactorResId;
        this.name = name;
        this.type = 0;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAvactorUrl() {
        return avactorUrl;
    }

    public void setAvactorUrl(String avactorUrl) {
        this.avactorUrl = avactorUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvactorResId() {
        return avactorResId;
    }

    public void setAvactorResId(int avactorResId) {
        this.avactorResId = avactorResId;
    }

    @Override
    public int compareTo(@NonNull String o) {
        return 0;
    }
}
