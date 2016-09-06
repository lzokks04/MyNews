package com.lzokks04.mynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzokks04.mynews.R;
import com.lzokks04.mynews.bean.NewsBean;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Liu on 2016/9/6.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyHolder> {

    private Context mContext;
    private NewsBean newsBean;
    private OnItemClickListener mOnItemClickLitener;

    public MyRecyclerViewAdapter(Context mContext, NewsBean newsBean) {
        this.mContext = mContext;
        this.newsBean = newsBean;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickLitener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.item_newslist, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        Picasso.with(mContext).load(newsBean.getResult().getData().get(position).getThumbnail_pic_s()).into(holder.ivIcon);
        holder.tvTitle.setText(newsBean.getResult().getData().get(position).getTitle());
        holder.tvTime.setText(newsBean.getResult().getData().get(position).getDate());

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return newsBean.getResult().getData().size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int postion);
    }

}
