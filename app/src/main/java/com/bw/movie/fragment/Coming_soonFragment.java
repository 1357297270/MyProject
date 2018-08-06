package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.FindComingSoonMovieAdapter;
import com.bw.movie.adapter.FindComingSoonMovieStoperAdapter;
import com.bw.movie.bean.Coming_soon_Bean;
import com.bw.movie.bean.EventMessageBoolean;
import com.bw.movie.model.FindComingSoonMovieListModel;
import com.bw.movie.presenter.FindComingSoonMovieListImp;
import com.bw.movie.view.FindComingSoonMovieListView;
import com.bw.movie.view.FindMoviesDetailActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/8/3.
 * 即将上映
 */

public class Coming_soonFragment extends Fragment implements FindComingSoonMovieListView {

    @BindView(R.id.coming_soon_recycle)
    RecyclerView mComingSoonRecycle;
    private View view;
    private Unbinder unbinder;
    private boolean isFlag = false;
    private FindComingSoonMovieListImp findComingSoonMovieListImp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coming_soonment, container, false);
        findComingSoonMovieListImp = new FindComingSoonMovieListImp(this);
        findComingSoonMovieListImp.findComingSoonMovieList("18", "15320748258726", 1, 10);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage(EventMessageBoolean messageBoolean) {
        boolean flag = messageBoolean.isFlag();
        this.isFlag = flag;
        findComingSoonMovieListImp.findComingSoonMovieList("18", "15320748258726", 1, 10);
    }
    @Override
    public void showComingSoonMovieList(List<Coming_soon_Bean.ResultBean> list) {
        if(isFlag){
            FindComingSoonMovieAdapter findComingSoonMovieAdapter = new FindComingSoonMovieAdapter(list, getActivity());
            mComingSoonRecycle.setAdapter(findComingSoonMovieAdapter);
            mComingSoonRecycle.setLayoutManager( new LinearLayoutManager( getActivity(), LinearLayoutManager.VERTICAL, false) );
        findComingSoonMovieAdapter.setItemClickListener(new FindComingSoonMovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), FindMoviesDetailActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "点击了我", Toast.LENGTH_SHORT).show();
            }
        });
        }else{
            FindComingSoonMovieStoperAdapter findComingSoonMovieStoperAdapter = new FindComingSoonMovieStoperAdapter(list, getActivity());
            mComingSoonRecycle.setAdapter(findComingSoonMovieStoperAdapter);
            mComingSoonRecycle.setLayoutManager( new LinearLayoutManager( getActivity(), LinearLayoutManager.HORIZONTAL, false) );
            findComingSoonMovieStoperAdapter.setItemClickListener(new FindComingSoonMovieAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(getActivity(), FindMoviesDetailActivity.class);
                    startActivity(intent);

                }
            });
        }
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
