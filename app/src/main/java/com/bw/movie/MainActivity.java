package com.bw.movie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bw.movie.bean.EventMessageBoolean;
import com.bw.movie.fragment.CityFragment;
import com.bw.movie.fragment.Coming_soonFragment;
import com.bw.movie.fragment.Cut_Fragment;
import com.bw.movie.fragment.Now_showingFragment;
import com.bw.movie.fragment.Popular_videoFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    //城市
    @BindView(R.id.city)
    RadioButton mCity;
    //热门影片
    @BindView(R.id.popular_video)
    RadioButton mPopularVideo;
    //正在上映
    @BindView(R.id.now_showing)
    RadioButton mNowShowing;
    //即将上映
    @BindView(R.id.coming_soon)
    RadioButton mComingSoon;
    //切换
    @BindView(R.id.cut)
    ImageView mCut;
    @BindView(R.id.group)
    RadioGroup mGroup;
    @BindView(R.id.fragment)
    FrameLayout mFragment;
    private boolean isFlag = true;
    private Fragment currentFragment = new Fragment();
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        showFragment(new Popular_videoFragment());
    }


    @OnClick({R.id.city, R.id.popular_video, R.id.now_showing, R.id.coming_soon, R.id.cut})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.city://城市
                // getSupportFragmentManager().beginTransaction().replace( R.id.fragment,new CityFragment() ).commit();
                Toast.makeText(this, "这是城市定位页面，正在开发中。。", Toast.LENGTH_SHORT).show();
                break;
            case R.id.popular_video://热门
                showFragment(new Popular_videoFragment());
                break;
            case R.id.now_showing://正在
                showFragment(new Now_showingFragment());
                break;
            case R.id.coming_soon://即将
                showFragment(new Coming_soonFragment());
                break;
            case R.id.cut://切换视图
                /*getSupportFragmentManager().beginTransaction().replace( R.id.fragment,new Cut_Fragment() ).commit();*/
                if (isFlag) {
                    isFlag = false;
                    mCut.setImageDrawable(getResources().getDrawable(R.drawable.kind_liner));
        //iv.setImageDrawable(getResources().getDrawable(R.drawable.icon));
                    EventMessageBoolean eventMessageBoolean = new EventMessageBoolean();
                    eventMessageBoolean.setFlag(true);
                    EventBus.getDefault().post(eventMessageBoolean);

                } else {
                    isFlag = true;
                    EventMessageBoolean eventMessageBoolean = new EventMessageBoolean();
                    eventMessageBoolean.setFlag(false);
                    EventBus.getDefault().post(eventMessageBoolean);
                    mCut.setImageDrawable(getResources().getDrawable(R.drawable.kind_grid));

                }

                break;
        }
    }

    //展示Fragment
    private void showFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.fragment, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }

        }
    }

}



