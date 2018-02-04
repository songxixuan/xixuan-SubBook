package com.example.songxixuan.xixuan_subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Subscription> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Data.loadFromFile(this);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        arrayAdapter = new ArrayAdapter<>(this,R.layout.list_item,Data.getInstance().getSubscriptions());
        final Activity that = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(that,EditActivity.class);
                intent.putExtra("usage","edit");
                intent.putExtra("index",position);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Data.loadFromFile(this);
        listView.setAdapter(arrayAdapter);
    }

    public void onButtonAddClicked(View view){
        Intent intent = new Intent(this,EditActivity.class);
        intent.putExtra("usage","add");
        startActivity(intent);
    }
}
