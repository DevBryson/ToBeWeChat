package cn.bisondev.tobewechat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bisondev.tobewechat.fragment.ContactFragment;
import cn.bisondev.tobewechat.fragment.FindFragment;
import cn.bisondev.tobewechat.fragment.ProfileFragment;
import cn.bisondev.tobewechat.fragment.WechatFragment;

/**
 * Created by Bison on 2017/5/13.
 */

public class MainActivity extends AppCompatActivity {

    private LinearLayout mTabWechat;
    private LinearLayout mTabContact;
    private LinearLayout mTabFind;
    private LinearLayout mTabProfile;

    private ImageView mIvWechat;
    private ImageView mIvContact;
    private ImageView mIvFind;
    private ImageView mIvProfile;

    private TextView mTvWechat;
    private TextView mTvContact;
    private TextView mTvFind;
    private TextView mTvProfile;

    private ViewPager mViewPager;
    private List<Fragment> mList;
    private int mPosition = 0;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewPager();
        initTab();
        initListener();
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.fragmentViewPager);

        mList = new ArrayList<>();
        mList.add(new WechatFragment());
        mList.add(new ContactFragment());
        mList.add(new FindFragment());
        mList.add(new ProfileFragment());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if(positionOffset > 0) {
//                    Log.e(TAG, "onPageScrolled: " + position);
//                    switch (mPosition) {
//                        case 0:
//                        case 1:
//                            if(position == 0) {
//                                mIvWechat.setAlpha(1 - positionOffset);
//                            }
//                            mIvContact.setAlpha(positionOffset);
//                            break;
//                        case 2:
//                        case 3:
//                            if(position == 2) {
//                                mIvFind.setAlpha(1 - positionOffset);
//                            }
//                            mIvProfile.setAlpha(positionOffset);
//                            break;
//                    }
//                }
            }

            @Override
            public void onPageSelected(int position) {
                changeColor(position);
//                Log.e(TAG, "onPageSelected: " + "调用了" + position);
//                mPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTab() {
        mTabWechat = (LinearLayout) findViewById(R.id.tab_wechat);
        mTabContact = (LinearLayout) findViewById(R.id.tab_contact);
        mTabFind = (LinearLayout) findViewById(R.id.tab_find);
        mTabProfile = (LinearLayout) findViewById(R.id.tab_profile);

        mIvWechat = (ImageView) findViewById(R.id.iv_wechat);
        mIvContact = (ImageView) findViewById(R.id.iv_contact);
        mIvFind = (ImageView) findViewById(R.id.iv_find);
        mIvProfile = (ImageView) findViewById(R.id.iv_profile);

        mTvWechat = (TextView) findViewById(R.id.tv_wechat);
        mTvContact = (TextView) findViewById(R.id.tv_contact);
        mTvFind = (TextView) findViewById(R.id.tv_find);
        mTvProfile = (TextView) findViewById(R.id.tv_profile);

        mPosition = 0;
    }

    private void initListener() {
        mTabWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(0 != mPosition) {
                    changeColor(0);
                    mViewPager.setCurrentItem(0);
                }
            }
        });

        mTabContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(1 != mPosition) {
                    changeColor(1);
                    mViewPager.setCurrentItem(1);
                }
            }
        });

        mTabFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(2 != mPosition) {
                    changeColor(2);
                    mViewPager.setCurrentItem(2);
                }
            }
        });

        mTabProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(3 != mPosition) {
                    changeColor(3);
                    mViewPager.setCurrentItem(3);
                }
            }
        });
    }

    private void changeColor(int newPosition) {
        if(newPosition == mPosition) {
            return;
        }

        switch (newPosition) {
            case 0:
                mIvWechat.setImageResource(R.drawable.wechat_pressed);
                mTvWechat.setTextColor(getResources().getColor(R.color.bottombarPressed));
                break;
            case 1:
                mIvContact.setImageResource(R.drawable.contact_list_pressed);
                mTvContact.setTextColor(getResources().getColor(R.color.bottombarPressed));
                break;
            case 2:
                mIvFind.setImageResource(R.drawable.find_pressed);
                mTvFind.setTextColor(getResources().getColor(R.color.bottombarPressed));
                break;
            case 3:
                mIvProfile.setImageResource(R.drawable.profile_pressed);
                mTvProfile.setTextColor(getResources().getColor(R.color.bottombarPressed));
                break;
        }

        switch (mPosition) {
            case 0:
                mIvWechat.setImageResource(R.drawable.wechat_normal);
                mTvWechat.setTextColor(getResources().getColor(R.color.bottombarNormal));
                break;
            case 1:
                mIvContact.setImageResource(R.drawable.contact_list_normal);
                mTvContact.setTextColor(getResources().getColor(R.color.bottombarNormal));
                break;
            case 2:
                mIvFind.setImageResource(R.drawable.find_normal);
                mTvFind.setTextColor(getResources().getColor(R.color.bottombarNormal));
                break;
            case 3:
                mIvProfile.setImageResource(R.drawable.profile_normal);
                mTvProfile.setTextColor(getResources().getColor(R.color.bottombarNormal));
                break;
        }
        mPosition = newPosition;
    }
}
