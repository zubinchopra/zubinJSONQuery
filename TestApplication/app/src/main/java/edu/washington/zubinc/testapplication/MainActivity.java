package edu.washington.zubinc.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.mashape.unirest.http.*;

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
