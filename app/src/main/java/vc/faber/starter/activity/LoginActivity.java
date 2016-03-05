package vc.faber.starter.activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;

import butterknife.Bind;
import butterknife.ButterKnife;
import vc.faber.starter.R;

public class LoginActivity extends FirebaseLoginBaseActivity {

  @Bind(R.id.login_button)
  Button loginButton;

  @Bind(R.id.register_button)
  Button registerButton;

  @Bind(R.id.email_input)
  EditText emailInput;

  @Bind(R.id.password_input)
  EditText passwordInput;

  @Override
  protected void onStart() {
    super.onStart();
    setEnabledAuthProvider(AuthProviderType.PASSWORD);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);

    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showFirebaseLoginPrompt();
      }
    });

    registerButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        registerUser(email, password);
      }
    });
  }

  @Override
  protected void onFirebaseLoggedIn(AuthData authData) {
    super.onFirebaseLoggedIn(authData);
    System.out.println(authData.getAuth().toString());
    Intent intent = new Intent(this, MessagesActivity.class);
    startActivity(intent);
    finish();
  }

  @Override
  protected Firebase getFirebaseRef() {
    return new Firebase("https://fctnewapp.firebaseio.com/");
  }

  @Override
  protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {
  }

  @Override
  protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {
  }

  private void registerUser(final String email, final String password) {
    getFirebaseRef().createUser(email, password, new Firebase.ResultHandler() {
      @Override
      public void onSuccess() {
        getFirebaseRef().authWithPassword(email, password, null);
      }

      @Override
      public void onError(FirebaseError firebaseError) {
        System.out.println("Error " + firebaseError.getMessage());
      }
    });
  }
}
