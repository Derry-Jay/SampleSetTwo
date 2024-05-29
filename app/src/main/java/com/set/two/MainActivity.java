package com.set.two;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.android.material.bottomsheet.BottomSheetDialog;

//import android.view.Window;
//import android.widget.CompoundButton;
//import android.app.DialogFragment;
//import android.content.DialogInterface;
//import androidx.annotation.Nullable;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.product.listing.R;
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MainActivity extends AppCompatActivity {
    TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.text1);
        t1.setOnClickListener(view -> {


        });
        t2.setOnClickListener(view -> {
        });
    }
}