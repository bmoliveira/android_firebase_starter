package vc.faber.starter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;
import vc.faber.starter.Model.Message;
import vc.faber.starter.R;
import vc.faber.starter.adapter.MessagesAdapter;

public class MessagesActivity extends AppCompatActivity {
  @Bind(R.id.messages_list)
  ListView messagesList;

  @Bind(R.id.message_input)
  EditText messageInput;

  @Bind(R.id.send_button)
  Button sendButton;

  Firebase mFirebaseRef = new Firebase("https://fctnewapp.firebaseio.com/").child("messages");

  MessagesAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_messages);
    ButterKnife.bind(this);
    sendButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        sendMessage();
      }
    });
    setAdapter();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.chat_room_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.logout: {
        mFirebaseRef.unauth();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
      }
    }
    return super.onOptionsItemSelected(item);
  }

  private void setAdapter() {
    adapter = new MessagesAdapter(this, mFirebaseRef);
    messagesList.setAdapter(adapter);
  }

  private void sendMessage() {
    String message = messageInput.getText().toString();
    String userEmail = (String) mFirebaseRef.getAuth().getProviderData().get("email");
    String userPhoto = (String) mFirebaseRef.getAuth().getProviderData().get("profileImageURL");
    Message messageObject = new Message(message, userEmail, userPhoto);
    mFirebaseRef.push().setValue(messageObject);
    messageInput.getText().clear();
  }
}
