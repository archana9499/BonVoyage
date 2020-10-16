package com.example.bonvoyage;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("sNQY0NZzM0Qy5Cv6FpqZwOPciSs3nxlepMekgeDw")
                // if defined
                .clientKey("6C6SGWfcZM19xtG0p541cLXQancOQx9Uw9NYLVeP")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
