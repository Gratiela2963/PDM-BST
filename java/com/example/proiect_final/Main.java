package com.example.proiect_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button vizualizareButton = findViewById(R.id.vizualizare);
        Button logoutButton = findViewById(R.id.logout);
        Button quizzButton = findViewById(R.id.quizz);
        Button helpButton = findViewById(R.id.help);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Main.this, MainActivity.class);
                startActivity(intent);
                finish(); // Pentru a termina activitatea curentă și a o elimina din stiva activităților
            }
        });

        vizualizareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///////FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Main.this, Vizualizare.class);
                startActivity(intent);
            }
        });

        quizzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, QuizActivity.class);
                startActivity(intent);

            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Help.class );
                startActivity(intent);

            }
        });
    }
}
