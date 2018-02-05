package com.example.songxixuan.xixuan_subbook;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Attributes: usage: I use this value as a key, to detect between add new subscription and edit.
 *                      since i use the same layout to edit and add.
 *             index: to get index of subscription in Subscription array.
 */

public class EditActivity extends AppCompatActivity {
    private String usage;
    private Subscription subscription;
    private EditText name;
    private EditText date;
    private EditText charge;
    private EditText comment;
    private  int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        /**
         * usage is a key, it contains value. after getting intent, it also gets usage.
         */
        Intent intent = getIntent();
        usage = intent.getStringExtra("usage");
        name = findViewById(R.id.NameInput);
        date = findViewById(R.id.TimeInput);
        charge = findViewById(R.id.MoneyInput);
        comment = findViewById(R.id.CommentInput);
        final Activity that = this;

        /**
         *after getting usage, usage contains value, if value is "add". it means now we need to do
         * adding new subscription. if value is "edit" . it means now need to do editing or
         *  deleting job
         *
         *
         */

        if (usage.equals("add")){
            subscription = new Subscription();

            /**
             * if we are doing adding job, delete button shall be invisible.
             */
            findViewById(R.id.delete_btn).setVisibility(View.GONE);
        } else if (usage.equals("edit")) {
            index = intent.getIntExtra("index",-1);
            subscription = Data.getInstance().getSubscriptions().get(index);
        }


        name.setText(subscription.getName());
        date.setText(subscription.getDateStr());
        charge.setText(Double.toString(subscription.getCharge()));
        comment.setText(subscription.getComment());


        /**
         * I use DatePickerDialog to get date input. DatePickerDialog can avoid incorrect date format
         *
         * Origin code from https://stackoverflow.com/questions/39916178/how-to-show-datepickerdialog-on-button-click
         */
        date.setFocusable(false);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                mcurrentTime.setTime(subscription.getDate());

                int year = mcurrentTime.get(Calendar.YEAR);
                int mouth = mcurrentTime.get(Calendar.MONTH);
                int dayOfMonth = mcurrentTime.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePickerDialog;
                mDatePickerDialog = new DatePickerDialog(that, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date_str = Integer.toString(year)+"-" +Integer.toString(month+1)+"-"+Integer.toString(dayOfMonth);
                        date.setText(date_str);
                    }
                },year,mouth,dayOfMonth);
                mDatePickerDialog.show();

            }
        });

    }

    /**
     * following codes are used to act when confirm button clicked.
     */

    public void onConfirmClicked(View view){
        /**
         *  following codes are used to avoid empty input of name and charge
         */
        if (name.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Name cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        if (charge.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Charge cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }


        subscription.setName(name.getText().toString());
        subscription.setDate(date.getText().toString());
        subscription.setCharge(Double.parseDouble(charge.getText().toString()));
        subscription.setComment(comment.getText().toString());


        if (usage.equals("add")){
            Data.getInstance().getSubscriptions().add(subscription);
        }
        Data.saveInFile(this);
        finish();
    }

    /**
     * following codes are used to act when delete button clicked.
     */

    public void onDeleteClicked(View view) {

        Data.getInstance().getSubscriptions().remove(index);
        Data.saveInFile(this);
        finish();
    }
}
