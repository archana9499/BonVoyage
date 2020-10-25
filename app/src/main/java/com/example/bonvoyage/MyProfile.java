package com.example.bonvoyage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfile extends Fragment {

    private EditText edtProfileName,edtProfileBio,edtProfileProfession,edtProfileHobbies,edtProfileHistory;
    private Button btnUpdateInfo;


    public MyProfile() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_profile, container, false);

        edtProfileName = (EditText) view.findViewById(R.id.edtProfileName);
        edtProfileBio = (EditText)view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = (EditText)view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies = (EditText)view.findViewById(R.id.edtProfileHobbies);
        edtProfileHistory = (EditText)view.findViewById(R.id.edtProfileHistory);

        ParseUser user = ParseUser.getCurrentUser();

        String profileName = user.getString("profileName");
        String profileBio = user.getString("profileBio");
        String profileProfession = user.getString("profileProfession");
        String profileHobbies = user.getString("profileHobbies");
        String profileHistory = user.getString("profileHistory");

        edtProfileName.setText(profileName);
        edtProfileBio.setText(profileBio);
        edtProfileProfession.setText(profileProfession);
        edtProfileHobbies.setText(profileHobbies);
        edtProfileHistory.setText(profileHistory);

        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //edit below


                //edit above

                updateUser();

                //edit below
                //newUpdateUser();
                //edit above

            }
        });

        return view;
    }

    public void updateUser() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // Other attributes than "email" will remain unchanged!

            //edit below


            //edit above

            currentUser.put("profileName",edtProfileName.getText().toString());
            currentUser.put("profileBio",edtProfileBio.getText().toString());
            currentUser.put("profileProfession",edtProfileProfession.getText().toString());
            currentUser.put("profileHobbies",edtProfileHobbies.getText().toString());
            currentUser.put("profileHistory",edtProfileHistory.getText().toString());

            // Saves the object.
            // Notice that the SaveCallback is totally optional!
            currentUser.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    // Here you can handle errors, if thrown. Otherwise, "e" should be null
                    if(e == null){
                        Toast.makeText(getContext(), "Info Updated", Toast.LENGTH_SHORT)
                                .show();
                    }

                    else {
                        Toast.makeText(getContext(), "Oops! Info Not Updated", Toast.LENGTH_SHORT)
                                .show();

                    }
                }
            });
        }
    }


    //edit below function
    /*public void newUpdateUser(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
// Retrieve the object by id
        query.getInBackground(ParseUser.getCurrentUser().getObjectId()+"", new GetCallback<ParseObject>() {
            public void done(ParseObject player, ParseException e) {
                if (e == null) {
                    // Now let's update it with some new data. In this case, only cheatMode and score
                    // will get sent to the Parse Cloud. playerName hasn't changed.
                    player.put("profileName", edtProfileName.getText().toString());
                    player.put("profileBio", edtProfileBio.getText().toString());
                    player.put("profileProfession", edtProfileProfession.getText().toString());
                    player.put("profileHobbies", edtProfileHobbies.getText().toString());
                    player.put("profileHistory", edtProfileHistory.getText().toString());

                    player.saveInBackground();
                } else {
                    // Failed
                }
            }
        });

    }
    //edit above function
    */

}