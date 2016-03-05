package vc.faber.starter.Model;

/**
 * Created by bruno on 04/03/16.
 */
public class Message {
  private String text;
  private String userEmail;
  private String userPhoto;

  public Message() {}

  public Message(String text, String userEmail, String userPhoto) {
    this.text = text;
    this.userEmail = userEmail;
    this.userPhoto = userPhoto;
  }

  public String getText() {
    return text;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public String getUserPhoto() {
    return userPhoto;
  }
}
