package com.example.bonvoyage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class ChatUsersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {


    private ListView chatUsersListView;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_users);

        chatUsersListView = findViewById(R.id.chatUsersListView);

        chatUsersListView.setOnItemClickListener(this);
        chatUsersListView.setOnItemLongClickListener(ChatUsersActivity.this);

        arrayList = new ArrayList();
        arrayAdapter = new ArrayAdapter(ChatUsersActivity.this, android.R.layout.simple_expandable_list_item_1,arrayList);

        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());

        //edit below

        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if(e == null){
                    if(users.size() > 0){

                        for(ParseUser user : users){
                            // String name= (String) user.get("name");
                            arrayList.add(user.getUsername());
                            //edit below

                        }

                        chatUsersListView.setAdapter(arrayAdapter);

                    }
                }

            }
        });



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent =  new Intent(ChatUsersActivity.this,ChatActivity.class);
        intent.putExtra("selectedUser",arrayList.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("AddJourney");
        parseQuery.whereEqualTo("user",arrayList.get(position));
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects.size() > 0 && e == null){

                    for(ParseObject plan : objects){
                        String receivedPlan = (String) plan.get("plan");

                       // Toast.makeText(chatUsersActivity2.this, "Plan is "+ receivedPlan, Toast.LENGTH_SHORT)
                              //  .show();

                        final PrettyDialog prettyDialog = new PrettyDialog(ChatUsersActivity.this);

                        prettyDialog.setTitle("About Planned Journey!")
                                .setMessage(receivedPlan);
                        prettyDialog.setIcon(R.drawable.list);
                        prettyDialog.addButton(
                                "OK",					// button text
                                R.color.pdlg_color_white,		// button text color
                                R.color.pdlg_color_gray,		// button background color
                                new PrettyDialogCallback() {		// button OnClick listener
                                    @Override
                                    public void onClick() {
                                        // Do what you gotta do
                                        prettyDialog.dismiss();
                                    }
                                }
                        )
                                .show();

                    }
                }
            }
        });
        return true;
    }
}