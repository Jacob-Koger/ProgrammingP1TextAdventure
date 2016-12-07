package com.jacob.koger023.programminggametextadventure;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class QuestionActivity extends AppCompatActivity implements OnClickListener {

    Button firstResponseButton;
    Button secondResponseButton;
    Button thirdResponseButton;
    Button fourthResponseButton;
    TextView questionTextView;
    TextView directionTextView;
    TextView itemTextView;
    ConstraintLayout constraintLayout;
    String playerName;
    String beginDirection = "Direction: ";
    String youDied = "You died...";
    String direction;
    String items = "";
    String TAG = "Question Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        SharedPreferences sp = getSharedPreferences("Character", MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor sharedPreferences = sp.edit();
        items = "Items: Backpack";
        playerName = sp.getString("name", "");
        sharedPreferences.putString("Items", items);
        constraintLayout = (ConstraintLayout) findViewById(R.id.activity_question);
        questionTextView = (TextView) findViewById(R.id.question_textview);
        directionTextView = (TextView) findViewById(R.id.direction_text_view);
        itemTextView = (TextView) findViewById(R.id.item_text_view);
        itemTextView.setTypeface(null, Typeface.ITALIC);
        firstResponseButton = (Button) findViewById(R.id.first_response_button);
        secondResponseButton = (Button) findViewById(R.id.second_response_button);
        thirdResponseButton = (Button) findViewById(R.id.third_response_button);
        fourthResponseButton = (Button) findViewById(R.id.fourth_response_button);
        firstResponseButton.setOnClickListener(this);
        secondResponseButton.setOnClickListener(this);
        thirdResponseButton.setOnClickListener(this);
        fourthResponseButton.setOnClickListener(this);
        direction = "North";
        firstQuestion();
    }

    private void firstQuestion() {
        Log.d(TAG, playerName);
        if (!Objects.equals(playerName, "Brent")) {
            String question = "Hello, " + playerName + ". You are in a forest, with no idea how you got there you look to the sky to see it is either dusk or dawn, what do you do?";
            String firstResponse = "Walk forward";
            String secondResponse = "Check backpack";
            String thirdResponse = "Walk left";
            String fourthResponse = "Walk right";
            String tag1 = "firstWalk";
            String tag2 = "checkBackpack";
            String tag3 = "left";
            String tag4 = "right";
            setText(question, firstResponse, secondResponse, thirdResponse, fourthResponse, direction, items, tag1, tag2, tag3, tag4);

        } else {

            String question = "Hello, " + playerName + ". You are not allowed to live in this universe due to a large Hadron Collider exploding and destroying humanity";
            String firstResponse = "Leave";
            String secondResponse = "Leave";
            String thirdResponse = "Leave";
            String fourthResponse = "Leave";
            String tag1 = "brent";
            setText(question, firstResponse, secondResponse, thirdResponse, fourthResponse,
                    direction, items, tag1, tag1, tag1, tag1);
        }
    }

    public void firstWalkForward() {

        String question = "You walk forward for what feels like forever, you start to notice light fading away, you are hungry, what do you do?";
        String firstResponse = "Continue walking";
        String secondResponse = "Check backpack";
        String thirdResponse = "Walk Left";
        String fourthResponse = "Walk Right";
        String tag1 = "secondWalk";
        String tag2 = "checkBackpack";
        String tag3 = "left2";
        String tag4 = "right2";
        setText(question, firstResponse, secondResponse, thirdResponse, fourthResponse, direction, items, tag1, tag2, tag3, tag4);
    }


    public void secondwalkForward() {
        String question = "You walk until you no longer can, you start hearing a howling, the moon is shining high, you sit down by the nearest tree, what now? ";
        String firstResponse = "Check backpack";
        String secondResponse = "Scan around you";
        String thirdResponse = "Try to sleep";
        String fourthResponse = "Try to stand";
    }

    public void checkBackpack() {
        String question = "You look in your backpack to find a flashlight, it feels heavy you assume it has batteries do you try to use it";
        String firstResponse = "Yes";
        String secondResponse = "No, save it";
        thirdResponseButton.setVisibility(View.INVISIBLE);
        fourthResponseButton.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void setText(String question, String first, String second, String third, String fourth, String direction, String items,
                         String tag1, String tag2, String tag3, String tag4) {
        questionTextView.setText(question);
        firstResponseButton.setText(first);
        secondResponseButton.setText(second);
        thirdResponseButton.setText(third);
        fourthResponseButton.setText(fourth);
        directionTextView.setText(beginDirection + direction);
        itemTextView.setText(items);
        firstResponseButton.setTag(tag1);
        secondResponseButton.setTag(tag2);
        thirdResponseButton.setTag(tag3);
        fourthResponseButton.setTag(tag4);
    }

    @Override
    public void onClick(View view) {
        String tag = (String) view.getTag();
        if (Objects.equals(tag, "brent")){
            throw new BrentException("Can't have brent");
        }
        if (Objects.equals(tag, "firstWalk")){
            firstWalkForward();
        }
    }
}

class BrentException extends RuntimeException {
    BrentException(String message) {
        super(message);
    }
}