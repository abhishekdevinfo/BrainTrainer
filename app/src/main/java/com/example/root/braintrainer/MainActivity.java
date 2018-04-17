package com.example.root.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int randomPlace;
    int correctAnswer = 0;
    int totalQuestion = 0;
    boolean isAnswerCorrect = false;
    ConstraintLayout mainPage;
    ArrayList<Integer> answer = new ArrayList<>();

    ImageView startImageView;
    TextView timerTextView, showQuestionTextView, showScoreTextView, firstGuessTextView, secondGuessTextView, thirdGuessTextView, fourthGuessTextView, resultTestView;
    Button playAgainButton;

    public void reset(View view) {
        correctAnswer = 0;
        totalQuestion = 0;
        isAnswerCorrect = false;
        resultTestView.setVisibility(View.INVISIBLE);
        timer.start();
        showScoreTextView.setText("0/0");
        showRNumberQuestion();
        firstGuessTextView.setEnabled(true);
        secondGuessTextView.setEnabled(true);
        thirdGuessTextView.setEnabled(true);
        fourthGuessTextView.setEnabled(true);
        playAgainButton.setVisibility(View.INVISIBLE);

    }

    public void checkAnswer(View view) {
        // Click when user touch and answer

        // One way of doing this by id.
        /*String id = view.getResources().getResourceEntryName(view.getId());
        if (randomPlace == 0 && id.equals("firstGuessTextView")) {
            correctAnswer++;
            isAnswerCorrect = true;
        } else if (randomPlace == 1 && id.equals("secondGuessTextView")) {
            correctAnswer++;
            isAnswerCorrect = true;
        } else if(randomPlace == 2 && id.equals("thirdGuessTextView")) {
            correctAnswer++;
            isAnswerCorrect = true;
        } else if(randomPlace == 3 && id.equals("fourthGuessTextView")) {
            correctAnswer++;
            isAnswerCorrect = true;
        }*/

        // Another way by tag
        if(view.getTag().toString().equals(Integer.toString(randomPlace))) {
            correctAnswer++;
            isAnswerCorrect = true;
        }

        totalQuestion++;

        showScore();
        showRNumberQuestion();

    }

    public void showScore() {
        showScoreTextView.setText(Integer.toString(correctAnswer) + "/" + Integer.toString(totalQuestion));
        resultTestView.setVisibility(View.VISIBLE);
        if (isAnswerCorrect) {
            resultTestView.setText("Correct!!");
        } else {
            resultTestView.setText("Wrong!");
        }
        isAnswerCorrect = false;

    }

    public int randomNumberGenerator(int num) {
        return (int) (Math.random() * num + 1);
    }

    public void showRNumberQuestion() {
        // Show random number in QuestionTextView and store the sum.
        int random_1 = randomNumberGenerator(10);
        int random_2 = randomNumberGenerator(10);
        int total = random_1 + random_2;

        showQuestionTextView.setText(Integer.toString(random_1) + " + " + Integer.toString(random_2));

        // pass sum to the function that show on random place;
        showSum(total);
    }

    public void showSum(int sum) {
        // Show sum of the random number
        int incorrect = 0;
        randomPlace = randomNumberGenerator(3);

        for (int i = 0; i < 4; i++) {
            if(randomPlace == i) {
                answer.add(sum);
            } else {
                incorrect = randomNumberGenerator(20);

                while(incorrect == sum) {
                    incorrect = randomNumberGenerator(20);
                }


                answer.add(incorrect);
            }
        }

        firstGuessTextView.setText(Integer.toString(answer.get(0)));
        secondGuessTextView.setText(Integer.toString(answer.get(1)));
        thirdGuessTextView.setText(Integer.toString(answer.get(2)));
        fourthGuessTextView.setText(Integer.toString(answer.get(3)));
        answer.clear();

//        switch (randomPlace) {
//            case 0:
//                firstGuessTextView.setText(Integer.toString(sum));
//                secondGuessTextView.setText(Integer.toString(sum+1));
//                thirdGuessTextView.setText(Integer.toString(sum-5));
//                fourthGuessTextView.setText(Integer.toString(sum*2));
//                break;
//            case 1:
//                firstGuessTextView.setText(Integer.toString(sum+1));
//                secondGuessTextView.setText(Integer.toString(sum));
//                thirdGuessTextView.setText(Integer.toString(sum-5));
//                fourthGuessTextView.setText(Integer.toString(sum*2));
//                break;
//            case 2:
//                firstGuessTextView.setText(Integer.toString(sum-5));
//                secondGuessTextView.setText(Integer.toString(sum+1));
//                thirdGuessTextView.setText(Integer.toString(sum));
//                fourthGuessTextView.setText(Integer.toString(sum*2));
//                break;
//            case 3:
//                firstGuessTextView.setText(Integer.toString(sum*2));
//                secondGuessTextView.setText(Integer.toString(sum+1));
//                thirdGuessTextView.setText(Integer.toString(sum-5));
//                fourthGuessTextView.setText(Integer.toString(sum));
//        }


    }


    public void startGame(View view) {
        startImageView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTestView.setVisibility(View.INVISIBLE);
        mainPage.setVisibility(View.VISIBLE);

        timer.start();
        showRNumberQuestion();
    }

    CountDownTimer timer = new CountDownTimer(30000 + 100, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timerTextView.setText(Long.toString(millisUntilFinished / 1000) + "s");
        }

        @Override
        public void onFinish() {
            timerTextView.setText("0s");
            resultTestView.setVisibility(View.VISIBLE);
            resultTestView.setText("Your Score: " + Integer.toString(correctAnswer) +"/" + Integer.toString(totalQuestion));
            playAgainButton.setVisibility(View.VISIBLE);
            firstGuessTextView.setEnabled(false);
            secondGuessTextView.setEnabled(false);
            thirdGuessTextView.setEnabled(false);
            fourthGuessTextView.setEnabled(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startImageView = findViewById(R.id.startImageView);
        timerTextView = findViewById(R.id.timerTextView);
        showQuestionTextView = findViewById(R.id.showQuestionTextView);
        showScoreTextView = findViewById(R.id.showScoreTextView);
        resultTestView = findViewById(R.id.resultTestView);
        firstGuessTextView = findViewById(R.id.firstGuessTextView);
        secondGuessTextView = findViewById(R.id.secondGuessTextView);
        thirdGuessTextView = findViewById(R.id.thirdGuessTextView);
        fourthGuessTextView = findViewById(R.id.fourthGuessTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        mainPage = findViewById(R.id.mainPage);

    }
}
