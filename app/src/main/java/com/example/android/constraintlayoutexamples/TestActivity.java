package com.example.android.constraintlayoutexamples;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestActivity extends AppCompatActivity {

    public static void launch(Context context) {
        Intent starter = new Intent(context, TestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
