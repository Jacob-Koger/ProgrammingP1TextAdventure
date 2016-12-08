package com.jacob.koger023.programminggametextadventure;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    Boolean tired = false;


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
        boolean isBrentPlaying = false;
        if (playerName.compareToIgnoreCase("Brent") == 0) {
            isBrentPlaying = true;
        }
        if (!isBrentPlaying) {
            String question = "Hello, " + playerName + ". You are in a forest, with no idea how you got there you look to the sky to see it is either dusk or dawn, what do you do?";
            String firstResponse = "Walk forward";
            String secondResponse = "Check backpack";
            String thirdResponse = "Walk left";
            String fourthResponse = "Walk right";
            String tag1 = "firstWalk";
            String tag2 = "checkBackpack";
            String tag3 = "left";
            String tag4 = "right";
            setText(question, firstResponse, secondResponse, thirdResponse, fourthResponse,
                    direction, items, tag1, tag2, tag3, tag4);
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
        setText(question, firstResponse, secondResponse, thirdResponse, fourthResponse,
                direction, items, tag1, tag2, tag3, tag4);
    }


    public void secondWalkForward() {
        String question = "You walk until you no longer can, you start hearing a howling, the moon is shining high, you sit down by the nearest tree, what now? ";
        String firstResponse = "Check backpack";
        String secondResponse = "Scan around you";
        String thirdResponse = "Try to sleep";
        String fourthResponse = "Try to stand";
        String tag1 = "checkBackpack";
        String tag2 = "scan";
        String tag3 = "sleep";
        String tag4 = "failtogetup";
        tired = true;
        setText(question, firstResponse, secondResponse, thirdResponse, fourthResponse,
                direction, items, tag1, tag2, tag3, tag4);
    }

    public void checkBackpack() {
        String question = "You look in your backpack to find a flashlight, it feels heavy you assume it has batteries do you try to use it?";
        String firstResponse = "Yes";
        String secondResponse = "No, save it";
        items = "Items: Backpack, Flashlight";
        thirdResponseButton.setVisibility(View.INVISIBLE);
        fourthResponseButton.setVisibility(View.INVISIBLE);
        String tag1 = "tryFlashlight";
        String tag2 = "saveFlashlight";
        setText(question, firstResponse, secondResponse, null, null,
                direction, items, tag1, tag2, null, null);
    }

    public void tryFlashlight() {
        String question = "There is nothing happened as you click the switch trying to get it to work, what do you do?";
        String firstResponse = "See what is inside";
        String secondResponse = "Assume the batteries are dead";
        String thirdResponse = "Return flashlight to backpack";
        String fourthResponse = "Leave flashlight on ground";
        String tag1 = "checkInsideFlashlight";
        String tag2 = "saveFlashlight";
        String tag3 = "saveFlashlight";
        String tag4 = "leaveFlashlight";
        thirdResponseButton.setVisibility(View.VISIBLE);
        fourthResponseButton.setVisibility(View.VISIBLE);
        setText(question, firstResponse, secondResponse, thirdResponse, fourthResponse,
                direction, items, tag1, tag2, tag3, tag4);
    }

    public void returnFlashlightToBag() {
        thirdResponseButton.setVisibility(View.VISIBLE);
        fourthResponseButton.setVisibility(View.VISIBLE);
        if (tired) {
            String question = "You return the flashlight to your bag, you must sleep";
            String firstResponse = "Sleep";
            String tag1 = "sleep";
            secondResponseButton.setVisibility(View.INVISIBLE);
            thirdResponseButton.setVisibility(View.INVISIBLE);
            fourthResponseButton.setVisibility(View.INVISIBLE);
            setText(question, firstResponse, null, null, null,
                    direction, items, tag1, null, null, null);
        }
        if (!tired) {
            String question = "You get mauled by a wolf, you die";
            firstResponseButton.setVisibility(View.VISIBLE);
            secondResponseButton.setVisibility(View.VISIBLE);
            thirdResponseButton.setVisibility(View.VISIBLE);
            fourthResponseButton.setVisibility(View.VISIBLE);
            String tag1 = "died";
            setText(question, youDied, youDied, youDied, youDied,
                    youDied, youDied, tag1, tag1, tag1, tag1);
        }
    }

    private void dieLeft() {
        String question = "You walk to the left off of a cliff, you die with your carcass in pieces";
        firstResponseButton.setVisibility(View.VISIBLE);
        secondResponseButton.setVisibility(View.VISIBLE);
        thirdResponseButton.setVisibility(View.VISIBLE);
        fourthResponseButton.setVisibility(View.VISIBLE);
        String tag1 = "died";
        setText(question, youDied, youDied, youDied, youDied,
                youDied, youDied, tag1, tag1, tag1, tag1);
    }

    private void dieRight() {
        String question = "You walk into a cave and get lost, you die of starvation";
        firstResponseButton.setVisibility(View.VISIBLE);
        secondResponseButton.setVisibility(View.VISIBLE);
        thirdResponseButton.setVisibility(View.VISIBLE);
        fourthResponseButton.setVisibility(View.VISIBLE);
        String tag1 = "died";
        setText(question, youDied, youDied, youDied, youDied,
                youDied, youDied, tag1, tag1, tag1, tag1);
    }

    private void findDeath() {
        String question = "You find the horseman of death, you die";
        firstResponseButton.setVisibility(View.VISIBLE);
        secondResponseButton.setVisibility(View.VISIBLE);
        thirdResponseButton.setVisibility(View.VISIBLE);
        fourthResponseButton.setVisibility(View.VISIBLE);
        String tag1 = "died";
        setText(question, youDied, youDied, youDied, youDied,
                youDied, youDied, tag1, tag1, tag1, tag1);
    }

    private void sleepPermanently() {
        String question = "You sleep forever, you die";
        firstResponseButton.setVisibility(View.VISIBLE);
        secondResponseButton.setVisibility(View.VISIBLE);
        thirdResponseButton.setVisibility(View.VISIBLE);
        fourthResponseButton.setVisibility(View.VISIBLE);
        String tag1 = "died";
        setText(question, youDied, youDied, youDied, youDied,
                youDied, youDied, tag1, tag1, tag1, tag1);
    }

    private void starve() {
        String question = "You cannot move, you are that tired, you die of starvation";
        firstResponseButton.setVisibility(View.VISIBLE);
        secondResponseButton.setVisibility(View.VISIBLE);
        thirdResponseButton.setVisibility(View.VISIBLE);
        fourthResponseButton.setVisibility(View.VISIBLE);
        String tag1 = "died";
        setText(question, youDied, youDied, youDied, youDied,
                youDied, youDied, tag1, tag1, tag1, tag1);
    }

    @SuppressLint("SetTextI18n")
    private void setText(String question, String first, String second, String third, String fourth,
                         String direction, String items, String tag1, String tag2, String tag3,
                         String tag4) {
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
        if (Objects.equals(tag, "brent")) {
            throw new BrentException("Can't have brent");
        }
        if (Objects.equals(tag, "firstWalk")) {
            firstWalkForward();
        }
        if (Objects.equals(tag, "secondWalk")) {
            secondWalkForward();
        }
        if (Objects.equals(tag, "left")) {
            dieLeft();
        }
        if (Objects.equals(tag, "right")) {
            dieRight();
        }
        if (Objects.equals(tag, "checkBackpack")) {
            checkBackpack();
        }
        if (Objects.equals(tag, "tryFlashlight")) {
            tryFlashlight();
        }
        if (Objects.equals(tag, "saveFlashlight")) {
            returnFlashlightToBag();
        }
        if (Objects.equals(tag, "scan")) {
            findDeath();
        }
        if (Objects.equals(tag, "sleep")) {
            sleepPermanently();
        }
        if (Objects.equals(tag, "failtogetup")) {
            starve();
        }
        if (Objects.equals(tag, "died")) {
        }
        Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
        QuestionActivity.this.startActivity(intent);
    }
}

class BrentException extends RuntimeException {
    BrentException(String message) {
        super(message);
    }
}