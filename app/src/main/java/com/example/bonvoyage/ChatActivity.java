package com.example.bonvoyage;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView chatListView;
    private ArrayList<String> chatsList;
    private ArrayAdapter adapter;
    private String selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        selectedUser = getIntent().getStringExtra("selectedUser");


        Toast.makeText(this, "Chat with " + selectedUser, Toast.LENGTH_SHORT)
                .show();


        chatListView = findViewById(R.id.chatListView);

        findViewById(R.id.btnSend).setOnClickListener(this);
        chatsList = new ArrayList<>();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,chatsList);
        chatListView.setAdapter(adapter);

        try {

            ParseQuery<ParseObject> firstUserChatQuery = ParseQuery.getQuery("Chat");
            ParseQuery<ParseObject> secondUserChatQuery = ParseQuery.getQuery("Chat");

            firstUserChatQuery.whereEqualTo("sender", ParseUser.getCurrentUser().getUsername());
            firstUserChatQuery.whereEqualTo("targetRecipient", selectedUser);

            secondUserChatQuery.whereEqualTo("sender", selectedUser);
            secondUserChatQuery.whereEqualTo("targetRecipient", ParseUser.getCurrentUser().getUsername());

            ArrayList<ParseQuery<ParseObject>> allQueries = new ArrayList<>();
            allQueries.add(firstUserChatQuery);
            allQueries.add(secondUserChatQuery);

            ParseQuery<ParseObject> myQuery = ParseQuery.or(allQueries);
            myQuery.orderByAscending("createdAt");

            myQuery.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (objects.size() > 0 && e == null) {
                        for (ParseObject chatObject : objects) {

                            String message = chatObject.get("message") + "";

                            if (chatObject.get("sender").equals(ParseUser.getCurrentUser().getUsername())) {
                                message = ParseUser.getCurrentUser().getUsername() + ": " + message;
                            }
                            if (chatObject.get("sender").equals(selectedUser)) {
                                message = selectedUser + " : " + message;
                            }
                            chatsList.add(message);
                        }
                        adapter.notifyDataSetChanged();

                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        final EditText edtMsg = findViewById(R.id.edtSend);

        ParseObject chat = new ParseObject("Chat");
        chat.put("sender", ParseUser.getCurrentUser().getUsername());
        chat.put("targetRecipient",selectedUser);
        chat.put("message",edtMsg.getText().toString());
        chat.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(ChatActivity.this, "Message from " + ParseUser.getCurrentUser().getUsername() + "send to "
                            + selectedUser, Toast.LENGTH_SHORT)
                            .show();
                    chatsList.add(ParseUser.getCurrentUser().getUsername() + " : " + edtMsg.getText().toString());
                    adapter.notifyDataSetChanged();
                    edtMsg.setText("");
                }
            }
        });


    }
}