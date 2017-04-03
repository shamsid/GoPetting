package com.shamsid.gopetting;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.shamsid.gopetting.models.Datum;
import com.shamsid.gopetting.models.Pojo;
import com.shamsid.gopetting.network.Api;
import com.shamsid.gopetting.network.ServiceGenerator;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ShowItemActivity extends Activity {

  private ProgressDialog mProgressDialog;
  private List<Datum> mDataList = new ArrayList<> ();;
  private ItemAdapter mItemAdapter;
  private  RecyclerView dataRecylerViewList;

  @Override
  protected void onCreate (@Nullable Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_show_item);

    mProgressDialog  = new ProgressDialog (this);
    mProgressDialog.setIndeterminate (true);
    mProgressDialog.setTitle ("Loading data...");
    mProgressDialog.setCancelable (false);
    mProgressDialog.show ();


    dataRecylerViewList  = (RecyclerView) findViewById (R.id.rv_items);

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager (this, OrientationHelper.VERTICAL,true);
    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(dataRecylerViewList.getContext(),
        linearLayoutManager.getOrientation());

    dataRecylerViewList.setHasFixedSize (true);
    dataRecylerViewList.setLayoutManager (linearLayoutManager);
    dataRecylerViewList.addItemDecoration (dividerItemDecoration);

    loadData();
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

  private void loadData(){

    Api api = ServiceGenerator.createService (Api.class);
    Call<Pojo> call = api.getPlayer ("upcomingGuides");

    call.enqueue (new Callback<Pojo> () {
      @Override public void onResponse (Call<Pojo> call, Response<Pojo> response) {
        mDataList.addAll (response.body ().getData ());

        mItemAdapter = new ItemAdapter (mDataList,ShowItemActivity.this);
        dataRecylerViewList.setAdapter (mItemAdapter);

        mProgressDialog.dismiss ();

      }

      @Override
      public void onFailure (Call<Pojo> call, Throwable t) {

      }
    });
  }
}
