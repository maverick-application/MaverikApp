package com.example.maverikapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.maverikapp.R;
import com.example.maverikapp.data_models.Post;
import com.example.maverikapp.data_models.PostDetails;
import com.example.maverikapp.utils.Utils;

import java.util.List;

public class FeedsAdapter  extends RecyclerView.Adapter<FeedsAdapter.MyViewHolder> {

    private List<PostDetails> faList;
    private Context faContext;
    private OnItemClickListener onItemClickListener;

    public FeedsAdapter(List<PostDetails> faList, Context faContext) {
        this.faList = faList;
        this.faContext = faContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_feeeds, parent, false);
        return new MyViewHolder(view,onItemClickListener);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holders, int position) {

        final  MyViewHolder holder = holders;
        PostDetails model =faList.get(position);

        RequestOptions faRequestOptions = new RequestOptions();
        faRequestOptions.placeholder(Utils.getRandomDrawbleColor());
        faRequestOptions.error(Utils.getRandomDrawbleColor());
        faRequestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        faRequestOptions.centerCrop();

        Glide.with(faContext)
                .load(model.getP_img())
                .apply(faRequestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.faProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.faProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.faImg);

        holder.faName.setText(model.getP_name());
        holder.faTime.setText(model.getP_time());
        holder.faLikes.setText(model.getP_likes());
        holder.faUser.setText(model.getP_id());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface  OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView faName,faDesc,faTime,faUser,faLikes;
        ImageView faImg;
        ProgressBar faProgressBar;
        OnItemClickListener onItemClickListener;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            faName = itemView.findViewById(R.id.inf_title);
            faTime = itemView.findViewById(R.id.inf_time);
            faUser = itemView.findViewById(R.id.inf_user_name);
            faLikes = itemView.findViewById(R.id.inf_like_count);
            faProgressBar = itemView.findViewById(R.id.inf_progress_bar);

            this.onItemClickListener = this.onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v,getAdapterPosition());

        }
    }
}
