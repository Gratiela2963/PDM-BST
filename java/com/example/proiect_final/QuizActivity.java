package com.example.proiect_final;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class QuizActivity extends AppCompatActivity {

    private int score = 0;
    private int currentQuestionIndex = 0;
    private final String[] questions = {
            "Intrebare 1: Un arbore binar de căutare este un arbore binar în care fiecare nod are o cheie mai mare decât toți nodurile din subarborele său stâng și mai mică decât toți nodurile din subarborele său drept?",
            "Intrebare 2: Un arbore de căutare binară de căutare binară este un arbore binar în care fiecare nod are o cheie mai mare decât toți nodurile din subarborele său stâng și mai mică decât toți nodurile din subarborele său drept?",
            "Intrebare 3: Parcurgerea in-order a unui arbore binar de căutare implică vizitarea nodurilor în ordinea: stânga, radacina, dreapta?",
            "Intrebare 4: Un arbore de căutare binară de căutare binară de căutare binară este un arbore binar în care fiecare nod are o cheie mai mare decât toți nodurile din subarborele său stâng și mai mică decât toți nodurile din subarborele său drept?",
            "Intrebare 5:Un arbore AVL este un arbore binar de căutare auto-echilibrat?",
            "Intrebare 6:Un arbore de căutare binară de căutare binară de căutare binară de căutare binară este un arbore binar în care fiecare nod are o cheie mai mare decât toți nodurile din subarborele său stâng și mai mică decât toți nodurile din subarborele său drept?",
            "Intrebare 7: Un arbore B este un arbore binar de căutare auto-echilibrat, în care fiecare nod are un număr fix de copii (cel puțin 2 și cel mult 4), iar toate frunzele sunt la aceeași adâncime?",
            "Intrebare 8: Un arbore de căutare binară echilibrat este un arbore binar de căutare în care înălțimea fiecărui subarbore este limitată de o diferență de -1 la 1 față de înălțimea arborelui?",
    };
    private final String[] correctAnswers = {
            "Da",
            "Nu",
            "Da",
            "Nu",
            "Da",
            "Nu",
            "Da",
            "Da",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        final EditText answerInput = findViewById(R.id.answer_input);
        final TextView questionText = findViewById(R.id.question_text);
        final TextView scoreText = findViewById(R.id.score_text);
        final Button submitButton = findViewById(R.id.submit_button);
        final TextView finalScoreText = findViewById(R.id.final_score_text);
        Button back =findViewById(R.id.AAA);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(QuizActivity.this, Main.class);
                startActivity(intent);
            }
        });

        questionText.setText(questions[currentQuestionIndex]);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String userAnswer = answerInput.getText().toString().trim();
                if (userAnswer.equalsIgnoreCase(correctAnswers[currentQuestionIndex])) {
                    score++;
                    scoreText.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                } else {
                    scoreText.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
                scoreText.setText("Scor: " + score);

                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    questionText.setText(questions[currentQuestionIndex]);
                    answerInput.setText("");
                } else {
                    // Pastrăm scorul final în SharedPreferences
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(QuizActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("finalScore", score);
                    editor.apply();

                    // Afișează scorul final și butonul pentru a naviga către o nouă pagină
                    finalScoreText.setText("Scor final: " + score);
                    finalScoreText.setVisibility(View.VISIBLE);


                }
            }
        });
    }
}