package com.example.michael.fastmind;


import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.Random;

/**
 * Made By Michael.
 * A Small game to test your mind, is it good or bad.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * array of answers.
     */
    int[] answers = new int[4];
    /**
     * buttons of the game.
     */
    Button playAgain;
    Button menu;
    Button startGame;
    Button exitGame;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    /**
     * text views
     */
    TextView score;
    TextView timeCounter;
    TextView question;
    TextView result;
    /**
     * counter for correct answers.
     */
    int correctAnswerCount;
    /**
     * counter for questions answered right or wrong.
     */
    int counter;
    /**
     * index of true answer to be placed.
     */
    int trueAnsIndex;


    /**
     * begin game.
     * @param view to be updated.
     */
    public  void showGame(View view){
        startGame.setVisibility(View.INVISIBLE);
        exitGame.setVisibility(View.INVISIBLE);
        question.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        timeCounter.setVisibility(View.VISIBLE);
        answer1.setVisibility(View.VISIBLE);
        answer2.setVisibility(View.VISIBLE);
        answer3.setVisibility(View.VISIBLE);
        answer4.setVisibility(View.VISIBLE);
        result.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        menu.setVisibility(View.INVISIBLE);
        updateGame();
        generateQuestion();
        new CountDownTimer(61000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeCounter.setText(millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                timeCounter.setText("0s");
                if ((correctAnswerCount * 100 / counter) < 30 ) {
                    result.setText("Very bad");
                } else if ((correctAnswerCount * 100 / counter) < 40 ) {
                    result.setText("Are you a kid ?!");
                } else if ((correctAnswerCount * 100 / counter) < 50) {
                    result.setText("Bad");
                } else if ((correctAnswerCount * 100 / counter) < 60) {
                    result.setText("Not that bad ");
                    if (counter < 8) {
                        result.append("But too slow");
                    }
                } else if ((correctAnswerCount * 100 / counter) < 60) {
                    result.setText("Not good ");
                    if (counter < 8) {
                        result.append("But too slow");
                    }
                } else if ((correctAnswerCount * 100 / counter) < 70) {
                    result.setText("Good ");
                    if (counter < 9) {
                        result.append("But too slow");
                    }
                } else if ((correctAnswerCount * 100 / counter) < 80) {
                    result.setText("Very Good ");
                    if (counter < 9) {
                        result.append("But too slow");
                    }
                } else if ((correctAnswerCount * 100 / counter) < 90) {
                    result.setText("Very Good ");
                    if (counter < 10 && counter >= 5) {
                        result.append("But too slow");
                    } else if (counter < 5) {
                        result.append("Between lazy people");
                    }
                } else if ((correctAnswerCount * 100 / counter) < 100) {
                    result.setText("Excellent ");
                    if (counter < 10 && counter >= 5) {
                        result.append("But too slow");
                    } else if (counter < 5) {
                        result.append("Between lazy people");
                    }
                } else {
                    result.setText("Genius ");
                    if (counter < 10 && counter >= 4) {
                        result.append("But too slow");
                    } else if (counter < 4) {
                        result.append("Between lazy people");
                    }
                }
                playAgain.setVisibility(View.VISIBLE);
                menu.setVisibility(View.VISIBLE);
            }


        }.start();

    }

    /**
     * get random integer.
     * @param max no. to be returned.
     * @return a number from 1 to max.
     */
    private int getRandomDigit(int max) {
        Random rand = new Random();
        return rand.nextInt(max) + 1;
    }

    /**
     * when the user press an answer.
     * @param view to be updated.
     */
    public void answerChosen(View view) {
        if (timeCounter.getText().toString().equals("0s")) {
            return;
        }
        if (view.getTag().toString().equals(Integer.toString(trueAnsIndex))) {
            correctAnswerCount++;
            result.setText("Correct");
        } else {
            result.setText("Wrong");
        }
        counter++;
        score.setText(Integer.toString(correctAnswerCount) + "/" + Integer.toString(counter));
        generateQuestion();
    }


    /**
     * update scene to go to menu
     * @param view view updated
     */
    public void backMenu(View view){
        startGame.setVisibility(View.VISIBLE);
        exitGame.setVisibility(View.VISIBLE);
        question.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        timeCounter.setVisibility(View.INVISIBLE);
        answer1.setVisibility(View.INVISIBLE);
        answer2.setVisibility(View.INVISIBLE);
        answer3.setVisibility(View.INVISIBLE);
        answer4.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        menu.setVisibility(View.INVISIBLE);
        updateGame();
    }


    /**
     * update score and result text in order to play again.
     */
    public void updateGame() {
        correctAnswerCount = 0;
        counter = 0;
        result.setText("");
        score.setText("0/0");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame = (Button) findViewById(R.id.Go);
        exitGame = (Button) findViewById(R.id.Exit);
        playAgain = (Button) findViewById(R.id.PlayAgain);
        menu = (Button) findViewById(R.id.Menu);
        question = (TextView) findViewById(R.id.Equation);
        score = (TextView) findViewById(R.id.Score);
        timeCounter = (TextView) findViewById(R.id.Time);
        result = (TextView) findViewById(R.id.Result);
        answer1 = (Button) findViewById(R.id.Answer1);
        answer2 = (Button) findViewById(R.id.Answer2);
        answer3 = (Button) findViewById(R.id.Answer3);
        answer4 = (Button) findViewById(R.id.Answer4);
        this.exitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        this.startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                showGame(view);
            }
        });
        this.playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGame(view);
            }
        });

    }


    /**
     * generates each question
     */
    public void generateQuestion() {
        int x = this.getRandomDigit(50);
        int y = this.getRandomDigit(50);
        question.setText(Integer.toString(x) + " + " + Integer.toString(y));
        int answer = x + y;
        Random rand = new Random();
        trueAnsIndex = rand.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == trueAnsIndex) {
                answers[i] = answer;
            } else {
                do {
                    answers[i] = this.getRandomDigit(100);
                }while (answers[i] == answer);
            }
        }
        answer1.setText(Integer.toString(answers[0]));
        answer2.setText(Integer.toString(answers[1]));
        answer3.setText(Integer.toString(answers[2]));
        answer4.setText(Integer.toString(answers[3]));

    }

}
