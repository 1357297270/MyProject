package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.MainActivity;
import com.bw.movie.R;
import com.bw.movie.adapter.FindHotMovieAdapter;
import com.bw.movie.adapter.FindHotMovieStoperAdapter;
import com.bw.movie.bean.EventMessageBoolean;
import com.bw.movie.bean.Popular_Bean;
import com.bw.movie.presenter.FindHotMovieListImp;
import com.bw.movie.view.FindHotMovieListView;
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
 * <p>
 * 热门影片
 */

public class Popular_videoFragment extends Fragment implements FindHotMovieListView {
    @BindView(R.id.popular_recycle)
    RecyclerView mPopularRecycle;
    private FindHotMovieListImp findHotMovieListImp;
    private View view;
    private Unbinder unbinder;
    private boolean isFlag = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popularment, container, false);
        EventBus.getDefault().register(this);
        findHotMovieListImp = new FindHotMovieListImp(this);
        unbinder = ButterKnife.bind(this, view);
        findHotMovieListImp.findHotMovieList("18", "15320748258726", 1, 10);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage(EventMessageBoolean messageBoolean) {
        boolean flag = messageBoolean.isFlag();
        this.isFlag = flag;
        findHotMovieListImp.findHotMovieList("18", "15320748258726", 1, 10);
    }

    @Override
    public void showHotMovieList(List<Popular_Bean.ResultBean> list) {
        if (isFlag) {
            FindHotMovieAdapter findHotMovieAdapter = new FindHotMovieAdapter(list, getActivity());
            mPopularRecycle.setAdapter(findHotMovieAdapter);
            mPopularRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
         findHotMovieAdapter.setItemClickListener(new FindHotMovieAdapter.OnItemClickListener() {
             @Override
             public void onItemClick(int position) {
                 Intent intent = new Intent(getActivity(), FindMoviesDetailActivity.class);
                 startActivity(intent);
             }
         });
        } else {
            FindHotMovieStoperAdapter findHotMovieStoperAdapter = new FindHotMovieStoperAdapter(list, getActivity());
            mPopularRecycle.setAdapter(findHotMovieStoperAdapter);
            mPopularRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
         findHotMovieStoperAdapter.setItemClickListener(new FindHotMovieStoperAdapter.OnItemClickListener() {
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
