package com.example.bonvoyage;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddJourneyActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtAddJourney;

    //viewing others plans
    private Button btnViewPlan;
    private ListView viewPlansListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journey);

        edtAddJourney = findViewById(R.id.edtAddJourney);

        btnViewPlan = findViewById(R.id.btnViewPlan);
        viewPlansListView = findViewById(R.id.viewPlansListView);
        btnViewPlan.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        final ArrayList<HashMap<String, String>> planList = new ArrayList<>();
        final SimpleAdapter adapter = new SimpleAdapter(AddJourneyActivity.this, planList, android.R.layout.simple_list_item_2, new String[]{"planUserName", "planValue"}, new int[]{android.R.id.text1, android.R.id.text2});


        try {
            ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("AddJourney");
            parseQuery.whereExists("user");
            parseQuery.whereNotEqualTo("user",ParseUser.getCurrentUser().getUsername());
            parseQuery.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (objects.size() > 0 && e == null) {
                        for (ParseObject tweetObject : objects) {
                            HashMap<String, String> userTweet = new HashMap<>();
                            userTweet.put("planUserName", tweetObject.getString("user"));
                            userTweet.put("planValue", tweetObject.getString("plan"));
                            planList.add(userTweet);

                        }

                        viewPlansListView.setAdapter(adapter);

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void sharePlan(View view) {

        ParseObject parseObject = new ParseObject("AddJourney");
        parseObject.put("plan",edtAddJourney.getText().toString());
        parseObject.put("user", ParseUser.getCurrentUser().getUsername());

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(AddJourneyActivity.this, "Yay! Shared Your Plan.", Toast.LENGTH_SHORT)
                            .show();
                }else {
                    Toast.makeText(AddJourneyActivity.this, "Oops! Try Again!", Toast.LENGTH_SHORT)
                            .show();
                }
                progressDialog.dismiss();
            }
        });
    }
}