package com.example.songxixuan.xixuan_subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * MainActivity is use to control main layout of app
 */

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Subscription> arrayAdapter;
    /**
     * MoneyCalculator is used to calculate total monthly charge of very subscription.
     *
     */
    public double MoneyCalculator(){
        double Money = 0.0;


        for (Subscription sub : Data.getInstance().getSubscriptions()){
            Money = Money + sub.getCharge();
        }

        return Money;
    }

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

            /**
             * onItemClick will act after list of view being clicked. it will pass a key and value to edit page
             * to let edit page know we are editing now
             *  also it will give index of clicked subscription to edit page.
             */
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
        listView.setAdapter(arrayAdapter);
        TextView display = findViewById(R.id.MoneyOutput);
        display.setText(String.valueOf(MoneyCalculator()));

    }
    /**
     * onButtonAddClicked will act after adding button being clicked. it will pass a key and value to edit page
     * to let edit page know we are adding new subscription now.
     */
    public void onButtonAddClicked(View view){
        Intent intent = new Intent(this,EditActivity.class);
        intent.putExtra("usage","add");
        startActivity(intent);
    }
}
