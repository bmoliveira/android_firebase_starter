package vc.faber.starter.widget;

import android.app.Application;

import com.firebase.client.Firebase;

public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Firebase.setAndroidContext(this);
  }
}
