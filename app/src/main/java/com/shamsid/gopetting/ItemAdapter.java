package com.shamsid.gopetting;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.shamsid.gopetting.models.Datum;
import java.util.List;

/**
 * Created by shamsheR on 03/04/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

  private List<Datum> mDataList ;
  private Context mContext;

  public ItemAdapter(List<Datum> dataList, Context context){
    mDataList = dataList;
    this.mContext = context;
  }

  @Override
  public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from (parent.getContext ());
    View view = layoutInflater.inflate (R.layout.single_item,null);
    ViewHolder viewHolder = new ViewHolder (view);
    return viewHolder;

  }

  @Override
  public void onBindViewHolder (ViewHolder holder, int position) {
    holder.thumbnailImage.setImageURI (mDataList.get (position).getIcon ());
    holder.name.setText (mDataList.get (position).getName ());
    holder.startDate.setText ("start date : "+mDataList.get (position).getStartDate ());
    holder.endDate.setText ("end date : "+mDataList.get (position).getEndDate ());

    holder.thumbnailImage.setOnClickListener (new View.OnClickListener () {
      @Override public void onClick (View v) {

        Intent myIntent = new Intent (mContext,EventDetailActvity.class);
        String data = new Gson ().toJson(mDataList.get (position));
        myIntent.putExtra ("data",data);
        mContext.startActivity (myIntent);

      }
    });

  }

  @Override
  public int getItemCount () {
    return mDataList.size ();
  }

   class ViewHolder extends RecyclerView.ViewHolder{

     SimpleDraweeView thumbnailImage;
     TextView name , startDate ,endDate;

    public ViewHolder (View itemView) {
      super (itemView);
      thumbnailImage = (SimpleDraweeView) itemView.findViewById (R.id.img_thumnail);
      name = (TextView) itemView.findViewById (R.id.tv_name);
      startDate = (TextView) itemView.findViewById (R.id.tv_start_date);
      endDate = (TextView) itemView.findViewById (R.id.tv_end_date);
    }

  }
}
