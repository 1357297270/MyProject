package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.Popular_Bean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2018/8/3.
 */

public class FindHotMovieStoperAdapter extends RecyclerView.Adapter<FindHotMovieStoperAdapter.ViewHolder> {
    List<Popular_Bean.ResultBean> list;
    Context context;

    public FindHotMovieStoperAdapter(List<Popular_Bean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FindHotMovieStoperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.hotmovie_stoper_item, null);
        ViewHolder viewHolderstoper = new ViewHolder(inflate);
        return viewHolderstoper;
    }

    @Override
    public void onBindViewHolder(@NonNull FindHotMovieStoperAdapter.ViewHolder holder, final int position) {
        holder.title_name.setText(list.get(position).getName());
       holder.title_tit.setText(list.get(position).getSummary());
       // holder.title_tit.setSingleLine(false);
        final String picUrl = list.get(position).getImageUrl();
        final Uri uri = Uri.parse(picUrl);
        holder.img.setImageURI(uri);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener!=null){
                    mItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title_name;
        private final SimpleDraweeView img;
        private final TextView title_tit;


        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.my_image_view);
            title_name = itemView.findViewById(R.id.title_name);
            title_tit = itemView.findViewById(R.id.title_tit);

        }

    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}