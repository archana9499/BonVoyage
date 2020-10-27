 package com.example.bonvoyage;

 import android.app.ProgressDialog;
 import android.content.Intent;
 import android.graphics.Bitmap;
 import android.graphics.BitmapFactory;
 import android.graphics.Color;
 import android.os.Bundle;
 import android.view.Gravity;
 import android.view.ViewGroup;
 import android.widget.ImageView;
 import android.widget.LinearLayout;
 import android.widget.TextView;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;

 import com.parse.FindCallback;
 import com.parse.GetDataCallback;
 import com.parse.ParseException;
 import com.parse.ParseFile;
 import com.parse.ParseObject;
 import com.parse.ParseQuery;

 import java.util.List;

 public class UsersPosts extends AppCompatActivity {

     private LinearLayout linearLayout;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_users_posts);

         linearLayout = findViewById(R.id.linearLayout);

         Intent receivedIntentObject = getIntent();
         String receivedUserName = receivedIntentObject.getStringExtra("username");
         //Toast.makeText(this, receivedUserName, Toast.LENGTH_SHORT)
            //     .show();


         setTitle(receivedUserName + "'s posts");

         ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Photo");
         parseQuery.whereEqualTo("username", receivedUserName);
         parseQuery.orderByDescending("createdAt");

         ProgressDialog dialog = new ProgressDialog(this);
         dialog.setMessage("Loading");
         dialog.show();

         parseQuery.findInBackground(new FindCallback<ParseObject>() {
             @Override
             public void done(List<ParseObject> objects, ParseException e) {
                 if(objects.size() > 0 && e == null){

                     for(ParseObject post : objects){
                         TextView postCaption = new TextView(UsersPosts.this);
                         postCaption.setText(post.get("img_cap")+ "");

                         ParseFile postPicture = (ParseFile) post.get("picture");
                         postPicture.getDataInBackground(new GetDataCallback() {
                             @Override
                             public void done(byte[] data, ParseException e) {
                                 if(data != null && e == null){
                                     Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                                     ImageView postImageView = new ImageView(UsersPosts.this);
                                     LinearLayout.LayoutParams imageView_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                             ViewGroup.LayoutParams.WRAP_CONTENT);
                                     imageView_params.setMargins(5,5,5,5);
                                     postImageView.setLayoutParams(imageView_params);
                                     postImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                     postImageView.setImageBitmap(bitmap);

                                     LinearLayout.LayoutParams cap_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                             ViewGroup.LayoutParams.WRAP_CONTENT);
                                     cap_params.setMargins(5,5,5,5);
                                     postCaption.setLayoutParams(cap_params);
                                     postCaption.setGravity(Gravity.CENTER);
                                     postCaption.setBackgroundColor(Color.BLACK);
                                     postCaption.setTextColor(Color.WHITE);
                                     postCaption.setTextSize(30f);

                                     linearLayout.addView(postImageView);
                                     linearLayout.addView(postCaption);
                                 }
                             }
                         });
                     }

                 }else{
                     Toast.makeText(UsersPosts.this, receivedUserName + " doesn't have any posts!", Toast.LENGTH_SHORT)
                             .show();
                     finish();
                 }

                 dialog.dismiss();
             }
         });


     }
 }



