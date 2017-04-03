package com.shamsid.gopetting;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by shamsheR on 03/04/17.
 */

public class GoPettingApplication extends Application {
  @Override public void onCreate () {
    super.onCreate ();
    Fresco.initialize (this);

    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
        .setDefaultFontPath("fonts/roboto_light.ttf")
        .setFontAttrId(R.attr.fontPath)
        .build()
    );

  }
}
