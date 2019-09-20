package com.example.maverikapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maverikapp.R;
import com.example.maverikapp.utils.Constants;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.pojo_response.posts.DisplayPostDetailsResponse;
import com.example.maverikapp.pojo_response.posts.PostResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.MyViewHolder> {

    private List<DisplayPostDetailsResponse> faList;
    private Context faContext;
    private OnItemClickListener onItemClickListener;
    private SharedPreferences hfSharedPreferences;
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
        holder.faLikesCount.setText(model.getP_likes()+" likes");
        holder.faUser.setText(model.getP_college().getCollege_name());

        if(model.getP_like_status().equals("yes")){
            holder.faLike.setImageResource(R.drawable.ic_like_red);
        }else {
            holder.faLike.setImageResource(R.drawable.ic_like_black);
        }

        /*
        Share Functionality Button
         */

        holder.faShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                faContext.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        /*
        Like Functionality Button
         */
        holder.faLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                hfSharedPreferences = faContext.getSharedPreferences(Constants.USER_DETAILS,MODE_PRIVATE);
                user_id = hfSharedPreferences.getString(Constants.USER_ID,"");

                Toast.makeText(faContext, "User Id : "+user_id , Toast.LENGTH_SHORT).show();

                final Call<PostResponse> likeModelCall = RetrofitClient
                        .getInstance()
                        .getApi()
                        .getLikePost(user_id,model.getP_id());
                likeModelCall.enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {

                        PostResponse postLike = response.body();
                        int likes = Integer.parseInt(String.valueOf(holder.faLikesCount.getText()));
                        if (postLike != null){
                            if(postLike.getStatus() == 1){
                                holder.faLike.setImageResource(R.drawable.ic_like_red);
                                holder.faLikesCount.setText(String.valueOf(likes+1));
                                Toast.makeText(faContext,postLike.getMessage()+"LL" ,Toast.LENGTH_SHORT).show();
                            }
                            else{
                                holder.faLike.setImageResource(R.drawable.ic_like_black);
                                holder.faLikesCount.setText(String.valueOf(likes-1));
                                Toast.makeText(faContext,postLike.getMessage()+"DD",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Log.e("msgM",postLike.getMessage()+" "+postLike.getResult());
                            Toast.makeText(faContext,"Something has gone wrong !",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        Toast.makeText(faContext, "Network Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("maa",t.getMessage());
                    }
                });

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
        ImageView faImg,faShareButton;
        ImageButton faLike;
        ProgressBar faProgressBar;
        CardView faCardView;
        OnItemClickListener onItemClickListener;

        MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            faCardView = itemView.findViewById(R.id.root_view);
            faName = itemView.findViewById(R.id.inf_title);
            faTime = itemView.findViewById(R.id.inf_time);
            faUser = itemView.findViewById(R.id.inf_user_name);
            faLike = itemView.findViewById(R.id.inf_like_button);
            faLikesCount = itemView.findViewById(R.id.inf_like_count);
            faImg = itemView.findViewById(R.id.inf_feed_img);
            faShareButton = itemView.findViewById(R.id.inf_share);
            faProgressBar = itemView.findViewById(R.id.inf_progress_bar);


            this.onItemClickListener = onItemClickListener;
        }


        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());

        }
    }
}
