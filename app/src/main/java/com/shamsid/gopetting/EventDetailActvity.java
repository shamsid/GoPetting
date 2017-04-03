package com.shamsid.gopetting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.shamsid.gopetting.models.Datum;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EventDetailActvity  extends Activity{

  private SimpleDraweeView mThumnailImage;
  private TextView mEventName,
      mEventType,
      mEventStartDate,
      mEventEndDate;


  @Override protected void onCreate (@Nullable Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_event_detail);

    String jsonData=getIntent().getExtras().getString("data","");
    Log.v ("jsondata",jsonData);
    Datum data = new Gson ().fromJson (jsonData,Datum.class);

    mEventName = (TextView) findViewById (R.id.tv_event_name);
    mEventType = (TextView) findViewById (R.id.tv_event_type);
    mEventStartDate = (TextView) findViewById (R.id.tv_event_start_date);
    mEventEndDate = (TextView) findViewById (R.id.tv_event_end_date);
    mThumnailImage = (SimpleDraweeView) findViewById (R.id.sdv_thumnai);

    mThumnailImage.setImageURI (data.getIcon ());
    mEventName.setText ("Name of Event : " +data.getName ());
    mEventStartDate.setText ("Event Start Date : " +data.getStartDate ());
    mEventEndDate.setText ("End Event Date : "+data.getEndDate ());
    mEventType.setText ("Event Type : "+ data.getObjType ());

  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }
}
