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

import com.bw.movie.R;
import com.bw.movie.adapter.FindHotMovieAdapter;
import com.bw.movie.adapter.FindHotMovieStoperAdapter;
import com.bw.movie.adapter.FindReleaseMovieAdapter;
import com.bw.movie.adapter.FindReleaseMovieStoperAdapter;
import com.bw.movie.bean.EventMessageBoolean;
import com.bw.movie.bean.Now_showing_Bean;
import com.bw.movie.presenter.FindHotMovieListImp;
import com.bw.movie.presenter.FindReleaseMovieListImp;
import com.bw.movie.view.FindMoviesDetailActivity;
import com.bw.movie.view.FindReleaseMovieListView;

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
 * 正在上映
 */

public class Now_showingFragment extends Fragment implements FindReleaseMovieListView {

    @BindView(R.id.now_showing_recycle)
    RecyclerView mNowShowingRecycle;
    private View view;
    private Unbinder unbinder;
    private boolean isFlag = false;
    private FindReleaseMovieListImp findReleaseMovieListImp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.now_showment, container, false);
        findReleaseMovieListImp = new FindReleaseMovieListImp(this);
        findReleaseMovieListImp.findReleaseMovieList("18", "15320748258726", 1, 10);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage(EventMessageBoolean messageBoolean) {
        boolean flag = messageBoolean.isFlag();
        this.isFlag = flag;
        findReleaseMovieListImp.findReleaseMovieList("18", "15320748258726", 1, 10);

    }
    @Override
    public void showReleaseMovieList(List<Now_showing_Bean.ResultBean> list) {
       if (isFlag){
           FindReleaseMovieAdapter findReleaseMovieAdapter = new FindReleaseMovieAdapter(list, getActivity());
           mNowShowingRecycle.setLayoutManager( new LinearLayoutManager( getActivity(), LinearLayoutManager.VERTICAL, false) );
           mNowShowingRecycle.setAdapter(findReleaseMovieAdapter);
   findReleaseMovieAdapter.setItemClickListener(new FindReleaseMovieAdapter.OnItemClickListener() {
       @Override
       public void onItemClick(int position) {
           Intent intent = new Intent(getActivity(), FindMoviesDetailActivity.class);
           startActivity(intent);
       }
   });
       }else {
             FindReleaseMovieStoperAdapter findReleaseMovieStoperAdapter = new FindReleaseMovieStoperAdapter(list, getActivity());
           mNowShowingRecycle.setAdapter(findReleaseMovieStoperAdapter);
           mNowShowingRecycle.setLayoutManager( new LinearLayoutManager( getActivity(), LinearLayoutManager.HORIZONTAL, false) );
   findReleaseMovieStoperAdapter.setItemClickListener(new FindReleaseMovieStoperAdapter.OnItemClickListener() {
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
