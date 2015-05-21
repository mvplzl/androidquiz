package com.example.lzl.androidquiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mLastButton;
    private TextView mQuestionTextView;

    private TrueFalse[] mTrueFalseBank = new TrueFalse[]{
        new TrueFalse(R.string.question1, true),
        new TrueFalse(R.string.question2, false),
        new TrueFalse(R.string.question3, false),
        new TrueFalse(R.string.question4, true),
        new TrueFalse(R.string.question5, true)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initViews();
        updateQuestion();
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mTrueFalseBank.length;
                updateQuestion();
            }
        });
        mLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 4) % mTrueFalseBank.length;
                updateQuestion();
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mTrueFalseBank.length;
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        int question = mTrueFalseBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }


    private void initViews() {
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mLastButton = (ImageButton) findViewById(R.id.last_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
    }

    private void checkAnswer(boolean userPressedAnswer){
        boolean mTrueQuestion = mTrueFalseBank[mCurrentIndex].isTrueQuestion();
        int resId = 0;
        if(userPressedAnswer == mTrueQuestion){
            resId = R.string.true_toast;
        }else{
            resId = R.string.false_toast;
        }
        Toast.makeText(QuizActivity.this, resId, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }
}
