package com.example.quizapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int precounter = 0, maxcounter = 4;
    private String[] keys = {"B", "I", "R", "D", "X"};
    private String textAnswer = "BIRD";
    private TextView textscreen, textquestion, layouttitle;
    private Animation bigsmallforth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bigsmallforth= AnimationUtils.loadAnimation(this,R.anim.smallbigforth);

        keys = shuffleArray(keys);

        for (String key : keys) {
            addView(((LinearLayout) findViewById(R.id.layoutparent)), key, ((EditText) findViewById(R.id.edittextanswer)));
        }
    }
    private String[] shuffleArray(String[] ar) {

        Random rdm = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rdm.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;


    }

    private void addView(LinearLayout viewById, String key, final EditText viewById1) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.leftMargin = 20;
        final TextView textView = new TextView(this);
        textView.setLayoutParams(layoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bgpink));
        textView.setTextColor(this.getResources().getColor(R.color.colorPurple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(key);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);




        textscreen = (TextView) findViewById(R.id.textscreen);
        textquestion = (TextView) findViewById(R.id.textquestion);
        layouttitle = (TextView) findViewById(R.id.layouttitle);




        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (precounter < maxcounter) {
                    if (precounter == 0)
                        viewById1.setText("");
                    viewById1.setText(viewById1.getText().toString()+textView.getText().toString());

                    textView.startAnimation(bigsmallforth);
                    textView.animate().alpha(0).setDuration(300);
                    precounter++;


                    if (precounter == maxcounter) {
                        validate();
                    }
                }
            }
        });
        viewById.addView(textView);
    }

    private void validate() {
        precounter=0;
        EditText editText=(EditText)findViewById(R.id.edittextanswer);
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.layoutparent);

        if(editText.getText().toString().equals(textAnswer)){
            Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_SHORT).show();
            editText.setText("");
        }
        else {
            Toast.makeText(MainActivity.this,"Wrong",Toast.LENGTH_SHORT).show();
            editText.setText("");

        }

    }



}
