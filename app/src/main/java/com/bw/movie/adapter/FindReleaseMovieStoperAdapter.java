package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.Now_showing_Bean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2018/8/4.
 */

public class FindReleaseMovieStoperAdapter extends RecyclerView.Adapter<FindReleaseMovieStoperAdapter.ViewHolder> {
    List<Now_showing_Bean.ResultBean> list;
    Context context;

    public FindReleaseMovieStoperAdapter(List<Now_showing_Bean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.releasemovie_stoper_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title_name_releasestop.setText(list.get(position).getName());
        holder.title_tit_releasestop.setText(list.get(position).getSummary());
        final String picUrl = list.get(position).getImageUrl();
        final Uri uri = Uri.parse(picUrl);
        holder.my_image_releasestop.setImageURI(uri);
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

   class ViewHolder extends RecyclerView.ViewHolder{
       private final TextView title_name_releasestop;
       private final SimpleDraweeView my_image_releasestop;
       private final TextView title_tit_releasestop;
       public ViewHolder(View itemView) {
           super(itemView);
           my_image_releasestop = itemView.findViewById(R.id.my_image_releasestop);
           title_name_releasestop = itemView.findViewById(R.id.title_name_releasestop);
           title_tit_releasestop = itemView.findViewById(R.id.title_tit_releasestop);
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
