package edu.washington.zubinc.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mashape.unirest.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] ingredients = new String[]{"apples", "flour", "sugar"};
        JSONQuery myQuery = new JSONQuery(ingredients);
        myQuery.setUrl();
        myQuery.execute();
    }
}
