package com.set.two;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MainActivity extends AppCompatActivity {

    boolean isNightModeOn;

    public Button buttonsheet,theme;

    public BottomSheetDialog bottomSheetDialog;

    TextView darkMode;

    TextView darkMode2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonsheet = findViewById(R.id.button);
//        theme = findViewById(R.id.theme);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {

            isNightModeOn = false;

        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            isNightModeOn = true;

        }

        buttonsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                        bottomSheetDialog = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetTheme);
                        View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.buttonsheet, null);

                        darkMode = findViewById(R.id.h1);
                        darkMode2 = findViewById(R.id.h5);

                        sheetView.findViewById(R.id.h1).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (isNightModeOn) {
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                                    isNightModeOn = false;
                                } else {
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                                    isNightModeOn = true;
                                }

                            }

                        });

                        sheetView.findViewById(R.id.h5).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (isNightModeOn) {
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                                    isNightModeOn = false;
                                } else {
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                                    isNightModeOn = true;
                                }

                            }
                        });

                        bottomSheetDialog.setContentView(sheetView);
                        bottomSheetDialog.show();


                    }
        });


    }
}



//            theme.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    if (isNightModeOn) {
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                        isNightModeOn = false;
//                    } else {
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                        isNightModeOn = true;
//                    }
//
//                }
//            });






//                setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetTheme);

//                bottomSheetDialog = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetTheme);
//                View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.buttonsheet, null);
//
//                darkMode = findViewById(R.id.h1);
//                darkMode2 = findViewById(R.id.h5);
//
//
//                sheetView.findViewById(R.id.h1).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        if (isNightModeOn) {
//                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                            isNightModeOn = false;
//                        } else {
//                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                            isNightModeOn = true;
//                        }
//
//                    }
//
//                });
//
//                sheetView.findViewById(R.id.h5).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//
//                        if (isNightModeOn) {
//                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                            isNightModeOn = false;
//                        } else {
//                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                            isNightModeOn = true;
//                        }
//
//                    }
//                });
//
//
//                bottomSheetDialog.setContentView(sheetView);
////                bottomSheetDialog.setContentView(darkMode);
//                bottomSheetDialog.show();
//
//            }
//        });


