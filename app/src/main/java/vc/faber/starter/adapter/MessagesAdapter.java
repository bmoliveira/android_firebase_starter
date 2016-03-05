package vc.faber.starter.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import vc.faber.starter.Model.Message;
import vc.faber.starter.R;

public class MessagesAdapter extends FirebaseListAdapter<Message> {

  public MessagesAdapter(Activity activity, Firebase ref) {
    super(activity, Message.class, R.layout.message_item, ref);
  }

  @Override
  protected void populateView(View view, Message message, int i) {
    ImageView photoImage = (ImageView) view.findViewById(R.id.message_image);
    TextView username = (TextView) view.findViewById(R.id.message_username);
    TextView messageContent = (TextView) view.findViewById(R.id.message_message);

    username.setText(message.getUserEmail());
    messageContent.setText(message.getText());

    Picasso.with(mActivity)
        .load(message.getUserPhoto())
        .transform(new CropCircleTransformation())
        .into(photoImage);
  }
}
