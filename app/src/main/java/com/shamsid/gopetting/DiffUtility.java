package com.shamsid.gopetting;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import com.shamsid.gopetting.models.Datum;
import java.util.List;

/**
 * Created by shamsheR on 03/04/17.
 */

public class DiffUtility extends DiffUtil.Callback {

  private List<Datum> oldList;
  private List<Datum> newList;

  public DiffUtility(List<Datum> oldList,List<Datum> newList){
    this.oldList = oldList;
    this.newList = newList;
  }

  @Override
  public int getOldListSize () {
    return oldList.size ();
  }

  @Override
  public int getNewListSize () {
    return newList.size ();
  }

  @Override
  public boolean areItemsTheSame (int oldItemPosition, int newItemPosition) {
    return oldList.get (oldItemPosition).getName ().equalsIgnoreCase (newList.get (newItemPosition).getName ());
  }

  @Override
  public boolean areContentsTheSame (int oldItemPosition, int newItemPosition) {
    final Datum oldData= oldList.get (oldItemPosition);
    final Datum newData = newList.get (newItemPosition);

    return oldData.getName ().equalsIgnoreCase (newData.getName ());
  }

  @Nullable
  @Override
  public Object getChangePayload(int oldItemPosition, int newItemPosition) {
    // Implement method if you're going to use ItemAnimator
    return super.getChangePayload(oldItemPosition, newItemPosition);
  }
}
