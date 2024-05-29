package com.set.two;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.set.two.models.Contact;
import com.set.two.utils.DatabaseHandler;
import com.set.two.utils.TextValidator;

public class TaskOne extends Activity {
    DatabaseHandler db;
    EditText name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_one);
        db = new DatabaseHandler(this);
        Button insertButton = findViewById(R.id.insert);
        Button deleteButton = findViewById(R.id.delete);
//        Button updateButton= findViewById(R.id.update);
//        Button selectButton= findViewById(R.id.select_db);
//        Button clearButton= findViewById(R.id.clr);
//        name = findViewById(R.id._name);
//        phone= findViewById(R.id._phone);
        insertButton.setOnClickListener(this::insert);
        deleteButton.setOnClickListener(this::delete);
//        updateButton.setOnClickListener(this::update);
//        selectButton.setOnClickListener(this::select);
//        clearButton.setOnClickListener(this::clear);
        phone.addTextChangedListener(new TextValidator(phone) {
            @Override
            public void validate(TextView textView, String text) {
                /* Insert your validation rules here */

            }
        });
    }

    public void insert(View v) {
        long insVal = db.addContact(new Contact(name.getText().toString(), Long.parseLong(phone.getText().toString())));
        Toast.makeText(getApplicationContext(), "Inserted successfully " + insVal, Toast.LENGTH_SHORT).show();
    }

    public void select(View v) {
        Contact temp = db.getContactUsingPhNo(Long.parseLong(phone.getText().toString()));
        if (temp.getName() != null) {
            name.setText(temp.getName());
            phone.setText(String.valueOf(temp.getPhoneNumber()));
            Toast.makeText(getApplicationContext(), "Selected successfully",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Record not found",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View v) {
        int updVal = db.updateContact(new Contact(name.getText().toString(), Long.parseLong(phone.getText().toString())));
        Toast.makeText(getApplicationContext(), "Updated successfully " + updVal, Toast.LENGTH_SHORT).show();
    }

    public void delete(View v) {
        int delVal = db.deleteContact(Long.parseLong(phone.getText().toString()));
        Toast.makeText(getApplicationContext(), "Deleted successfully " + delVal, Toast.LENGTH_SHORT).show();
    }

    public void clear(View v) {
        name.setText("");
        phone.setText("");
    }
}