package cn.bisondev.tobewechat.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * 拼音检索工具类
 * Created by Bison on 2017/5/13.
 */

public class PinyinUtils {
    /**
     * 获取拼音的首字母（大写）
     * @param str
     * @return
     */
    public static String getFirstLetter(final String str){
        if (TextUtils.isEmpty(str)) return "#";
        String c = str.substring(0, 1);
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c).matches()){
            return c.toUpperCase();
        }
        return "#";
    }
}
