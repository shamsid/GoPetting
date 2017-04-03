package com.shamsid.gopetting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.shamsid.sociallogin.LoginManager;
import com.shamsid.sociallogin.SocialPlatformNotFound;
import com.shamsid.sociallogin.utils.Platforms;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

  private static String TAG = MainActivity.class.getSimpleName ();

  @Override protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_main);

    Button googlePlusLogin = (Button) findViewById (R.id.btn_login_with_google_plus);
    Button facebookLogin = (Button) findViewById (R.id.btn_login_with_facebook);

    googlePlusLogin.setOnClickListener (new View.OnClickListener () {
      @Override public void onClick (View v) {
        loginGooglePlus();
      }
    });

    facebookLogin.setOnClickListener (new View.OnClickListener () {
      @Override public void onClick (View v) {
        loginFacebook();
      }
    });
  }

  private void loginFacebook(){

    try {
      LoginManager.getInstance (this)
          .choose (Platforms.FACEBOOK)
          .login ()
          .subscribe (socialUser -> {

            Intent showIntent = new Intent (MainActivity.this,ShowItemActivity.class);
            startActivity (showIntent);

          }, error -> {
            Log.d (TAG, "error: " + error.getMessage ());
          });
    }catch (SocialPlatformNotFound spf){
      spf.printStackTrace ();
    }

  }

  private void loginGooglePlus(){
    try {
      LoginManager.getInstance (this)
          .choose (Platforms.GOOGLE_PLUS)
          .setClientId (getString (R.string.google_api_key))
          .login ()
          .subscribe (socialUser -> {
            Intent showIntent = new Intent (MainActivity.this,ShowItemActivity.class);
            startActivity (showIntent);

          }, error -> {
            Log.d (TAG, "error: " + error.getMessage ());
          });
    }catch (SocialPlatformNotFound spf){
      spf.printStackTrace ();
    }
  }
  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }
}
