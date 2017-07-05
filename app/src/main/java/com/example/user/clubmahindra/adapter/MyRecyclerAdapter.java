package com.example.user.clubmahindra.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.clubmahindra.R;
import com.example.user.clubmahindra.activity.ResortsListActivity;
import com.example.user.clubmahindra.model.ResortModel;
import com.example.user.clubmahindra.model.Resorts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {

    ArrayList mPutImageArrayList;
    Context context;
    OnItemClick onItemClick;
    ResortModel resortModel;
    ArrayList<Resorts> resortsArrayList;
    Resorts resorts;

    public MyRecyclerAdapter(ArrayList putmageArrayList, ResortsListActivity context, ResortModel resortModel)

    {
        mPutImageArrayList = putmageArrayList;
        this.context = context;
        this.resortModel = resortModel;
        onItemClick = context;
        resortsArrayList = resortModel.getResorts();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        resorts = resortsArrayList.get(position);
        String stateName = resorts.getState();
        ArrayList<String> imageList = resorts.getAboutImgURL();
        Picasso.with(context).load(imageList.get(0)).into(holder.setImageView);


        final String resortId = resorts.getResortid();

        holder.stateTextView.setText(stateName);
        holder.cityTextView.setText(resorts.getResortShortname());
        holder.offerTextView.setText((resortsArrayList.get(position).getOffers()) + String.valueOf(" offers"));
        holder.reviewTextView.setText(resorts.getReviewcount() + " Reviews");
        holder.roomTypeTextView.setText(getRoomType(resorts));


        holder.viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.clickOnView(Integer.parseInt(resortId));
            }
        });
    }

    private String getRoomType(Resorts resorts) {
        ArrayList<String> roomTypeArrayList = resorts.getRoomType();
        String allRoomType = "";
        for (int start = 0; start < roomTypeArrayList.size(); start++) {
            allRoomType = allRoomType + roomTypeArrayList.get(start) + String.valueOf(" ");
        }

        return allRoomType;

    }

    @Override
    public int getItemCount() {
        return (null != resortsArrayList ? resortsArrayList.size() : 0);
    }

    public interface OnItemClick {
        void clickOnView(int position);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public ImageView setImageView;
        public ViewGroup viewGroup;
        TextView stateTextView;
        TextView cityTextView;
        TextView offerTextView;
        TextView reviewTextView;
        TextView roomTypeTextView;

        public CustomViewHolder(View view) {
            super(view);
            setImageView = view.findViewById(R.id.listRowImageView);
            stateTextView = view.findViewById(R.id.stateId);
            cityTextView = view.findViewById(R.id.cityTextViewId);
            viewGroup = view.findViewById(R.id.linearLayoutId);
            offerTextView = view.findViewById(R.id.offersTextViewId);
            reviewTextView = view.findViewById(R.id.reviewId);
            roomTypeTextView = view.findViewById(R.id.roomTypeId);
        }
    }
}