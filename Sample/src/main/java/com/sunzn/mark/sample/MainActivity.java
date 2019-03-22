package com.sunzn.mark.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            data.add("人社部有关负责人表示，这样的安排，<font color='red'>是我国职</font>工基本养老保险制度待遇确定机制决定的。" + i);
        }

        RecyclerView mRecyclerView = findViewById(R.id.rcv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CustomAdapter(data));
    }
}
