package com.example.android.testyourgeneralknowledge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import static android.R.attr.button;
import static android.R.attr.duration;
import static android.R.attr.id;
import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {

    int score;
    String correctResult, wrongResult;
    TextView correctResultView, wrongResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // gets the TextView in which to display the correct answers to the quiz questions.
        correctResultView = (TextView) findViewById(R.id.correctResults);
        correctResult = correctResultView.getText().toString();
        wrongResultView = (TextView) findViewById(R.id.wrongResults);
        wrongResult = correctResultView.getText().toString();

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("savedScore");
            correctResult = savedInstanceState.getString("savedCorrect");
            wrongResult = savedInstanceState.getString("savedWrong");

            correctResultView.setText(correctResult);
            wrongResultView.setText(wrongResult);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("savedScore", score);
        savedInstanceState.putString("savedCorrect", correctResult);
        savedInstanceState.putString("savedWrong", wrongResult);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * checks if a checkBox has been tapped.
     * function will be used for many CheckBoxes, thus for many IDs
     *
     * @param idName the id of the CheckBox whose value is to be verified
     * @return returns true if the CheckBox is checked and false otherwise
     */
    public boolean getOptions(int idName) {
        CheckBox c = (CheckBox) findViewById(idName);
        return c.isChecked();
    }

    /**
     * checks if the options the user picked are correct
     * compares each option we got after calling getOptions(id) with the correct answers a, b, c or d
     *
     * @param ida id of the first checkbox in the group
     * @param a   correct answer for first checkbox in the group
     * @param idb id of the second checkbox in the group
     * @param b   correct answer for second checkbox in the group
     * @param idc id of the third checkbox in the group
     * @param c   correct answer for third checkbox in the group
     * @param idd id of the fourth checkbox in the group
     * @param d   correct answer for fourth checkbox in the group
     * @return returns true if the user answered correctly and false otherwise
     */
    public boolean checkBox(int ida, boolean a, int idb, boolean b, int idc, boolean c, int idd, boolean d) {
        boolean option1, option2, option3, option4;
        option1 = getOptions(ida);
        option2 = getOptions(idb);
        option3 = getOptions(idc);
        option4 = getOptions(idd);
        return option1 == a && option2 == b && option3 == c && option4 == d;
    }

    public boolean checkRadio(int id, String solution) {
        RadioGroup fruitGroup = (RadioGroup) findViewById(id);
        if (fruitGroup.getCheckedRadioButtonId() == -1) {
            return false;
        }
        int selectedID = fruitGroup.getCheckedRadioButtonId();
        RadioButton button = (RadioButton) findViewById(selectedID);
        String text = button.getText().toString().toLowerCase();
        return Objects.equals(text, solution);
    }

    public boolean checkText(int id, String solution) {
        String text = ((TextView) findViewById(id)).getText().toString().toLowerCase();
        return Objects.equals(text, solution);
    }

    public void checkAnswer(boolean ans, String i, String solution) {
        if (ans) {
            correctResult += i + ". Correct\n";
            score++;
        } else {
            wrongResult += i + ". Incorrect(S: " + solution + ")\n";
        }
    }

    /**
     * gathers all the answers from the previous questions and displays the results on the screen
     */
    public void submit(View view) {
        score = 0;
        correctResult = "CORRECT ANSWERS\n";
        wrongResult = "INCORRECT ANSWERS\n";
        boolean answer1 = checkBox(R.id.physics, true, R.id.peace, true, R.id.maths, false, R.id.economics, true);
        checkAnswer(answer1, "1", "physics, peace, economics");

        boolean answer2 = checkRadio(R.id.fruit, "peach");
        checkAnswer(answer2, "2", "peach");

        boolean answer3 = checkText(R.id.calf, "calf");
        checkAnswer(answer3, "3", "calf");

        boolean answer4 = checkRadio(R.id.bigbang, "15 billion years ago");
        checkAnswer(answer4, "4", "15 billion years ago");

        boolean answer5 = checkBox(R.id.geometry, true, R.id.algebra, false, R.id.trigonometry, false, R.id.analisys, false);
        checkAnswer(answer5, "5", "geometry");

        boolean answer6 = checkText(R.id.carbon, "carbon");
        checkAnswer(answer6, "6", "carbon");

        boolean answer7 = checkText(R.id.samurai, "samurai");
        checkAnswer(answer7, "7", "samurai");

        boolean answer8 = checkRadio(R.id.light, "8 minutes");
        checkAnswer(answer8, "8", "8 minutes");

        boolean answer9 = checkRadio(R.id.atom, "4");
        checkAnswer(answer9, "9", "4");

        boolean answer10 = checkText(R.id.nitrogen, "nitrogen");
        checkAnswer(answer10, "10", "nitrogen");

        correctResultView.setText(correctResult);
        wrongResultView.setText(wrongResult);

        Toast toast = Toast.makeText(getApplicationContext(), "You scored " + score + " out of 10!", Toast.LENGTH_SHORT);
        toast.show();
    }
}