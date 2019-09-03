package com.example.maverikapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maverikapp.R;
import com.example.maverikapp.utils.Constants;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.pojo_response.posts.DisplayPostDetailsResponse;
import com.example.maverikapp.pojo_response.posts.PostLikeModel;
import com.example.maverikapp.ui.home.FullViewPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.MyViewHolder> {

    private List<DisplayPostDetailsResponse> faList;
    private Context faContext;
    private OnItemClickListener onItemClickListener;
    private SharedPreferences hfSharedPerferences;
    private  String user_id;

    public FeedsAdapter(List<DisplayPostDetailsResponse> faList, Context faContext) {
        this.faList = faList;
        this.faContext = faContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_feeeds, parent, false);
        faContext = parent.getContext();
        return new MyViewHolder(view, onItemClickListener);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holders, final int position) {

        final MyViewHolder holder = holders;
        final DisplayPostDetailsResponse model = faList.get(position);

//        RequestOptions faRequestOptions = new RequestOptions();
//        faRequestOptions.placeholder(Utils.getRandomDrawbleColor());
//        faRequestOptions.error(Utils.getRandomDrawbleColor());
//        faRequestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
//        faRequestOptions.centerCrop();
//
//
//        Glide.with(faContext)
//                .load(model.getP_img())
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        holder.faProgressBar.setVisibility(View.GONE);
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        holder.faProgressBar.setVisibility(View.GONE);
//                        return false;
//                    }
//                })
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(holder.faImg);

        holder.faName.setText(model.getP_title());
        holder.faTime.setText(model.getP_time());
        holder.faLikesCount.setText(model.getP_likes());
        holder.faUser.setText(model.getP_id());

        if(model.getP_like_status().equals("yes")){
            holder.faLike.setImageResource(R.drawable.ic_like_red);
        }else{
            holder.faLike.setImageResource(R.drawable.ic_like_black);
        }

        holder.faLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    hfSharedPerferences = faContext.getSharedPreferences(Constants.USER_DETAILS,MODE_PRIVATE);
                }catch (Exception e){
                    Toast.makeText(faContext,"Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                user_id = hfSharedPerferences.getString(Constants.UNIQUE_ID,"");

                final Call<PostLikeModel> likeModelCall = RetrofitClient
                        .getInstance()
                        .getApi()
                        .getLikePost(user_id,model.getP_id());
                likeModelCall.enqueue(new Callback<PostLikeModel>() {
                    @Override
                    public void onResponse(Call<PostLikeModel> call, Response<PostLikeModel> response) {
                        if(response.body().getMessage().equals("yes")){
                            holder.faLike.setImageResource(R.drawable.ic_like_red);
                        }
                        else
                            holder.faLike.setImageResource(R.drawable.ic_like_black);
                    }

                    @Override
                    public void onFailure(Call<PostLikeModel> call, Throwable t) {
                        Toast.makeText(faContext, "Network Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("maa",t.getMessage());
                    }
                });

            }
        });
        holder.faImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent na = new Intent(faContext, FullViewPost.class);
                na.putExtra("title", model.getP_title());
                na.putExtra("desc", model.getP_desc());
                na.putExtra("img", model.getP_img());
                na.putExtra("like_count", model.getP_likes());
                na.putExtra("like",model.getP_like_status());
                na.putExtra("time", model.getP_time());
            }
        });


    }

    @Override
    public int getItemCount() {
        return faList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView faName, faDesc, faTime, faUser, faLikesCount;
        ImageView faImg,faLike;
        ProgressBar faProgressBar;
        CardView faCardView;
        OnItemClickListener onItemClickListener;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            faCardView = itemView.findViewById(R.id.root_view);
            faName = itemView.findViewById(R.id.inf_title);
            faTime = itemView.findViewById(R.id.inf_time);
            faUser = itemView.findViewById(R.id.inf_user_name);
            faLike = itemView.findViewById(R.id.inf_like);
            faLikesCount = itemView.findViewById(R.id.inf_like_count);
            faImg = itemView.findViewById(R.id.inf_feed_img);
            faProgressBar = itemView.findViewById(R.id.inf_progress_bar);

            this.onItemClickListener = onItemClickListener;
        }


        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());

        }
    }
}
