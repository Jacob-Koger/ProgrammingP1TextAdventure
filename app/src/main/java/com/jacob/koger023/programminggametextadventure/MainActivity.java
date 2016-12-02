package com.jacob.koger023.programminggametextadventure;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button startGameButton;
    EditText nameEditText;
    Context context;
    CharSequence playerName;
    String TAG = "MainAct";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getBaseContext();
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        startGameButton = (Button) findViewById(R.id.lets_go_button);

        setOnClick();
    }

    public void setOnClick(){
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nameEditText.getText() != null){

                    CharSequence name =  nameEditText.getText();

                    SharedPreferences sp = getSharedPreferences("Character", MODE_PRIVATE);
                    SharedPreferences.Editor sharedPreferences = sp
                            .edit();
                    sharedPreferences.putString("name",  String.valueOf(name)).apply();
                    Log.d(TAG, sp.getString("name", ""));
                    playerName = sp.getString("name", "");
                    Toast.makeText(context, "You are now " + playerName, Toast.LENGTH_LONG )
                          .show();
                    Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                    MainActivity.this.startActivity(intent);
                }

            }
        });
    }
}
