package com.bw.movie.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;

import junit.framework.Test;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindMoviesDetailActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_movies_detail);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.back)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
