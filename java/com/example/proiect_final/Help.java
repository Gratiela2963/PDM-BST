package com.example.proiect_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help); // setează conținutul la fișierul help.xml

        // Referință către WebView din XML
        WebView infoWebView = findViewById(R.id.infoWebView);
        Button back =findViewById(R.id.BUTON);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Help.this, Main.class);
                startActivity(intent);
            }
        });

        // Text cu teoria despre parcurgerea arborilor binari
        String theory = "<html><body><p>Parcurgerea arborilor binari este procesul de explorare și vizitare a nodurilor "
                + "unui arbore binar într-o anumită ordine. Aceasta include:</p>"
                + "<ol>"
                + "<li>Preordinea: Vizitarea nodului rădăcină înainte de a vizita subarborii săi stâng și drept.</li>"
                + "<li>Inordinea: Vizitarea mai întâi a subarborelui stâng, apoi a nodului rădăcină și în final a subarborelui drept.</li>"
                + "<li>Postordinea: Vizitarea subarborilor stâng și drept înainte de a vizita nodul rădăcină.</li>"
                + "</ol>"
                + "<p>Pentru mai multe informații, poți viziona aceste tutoriale video:</p>"
                + "<ul>"
                + "<li><a href=\"https://www.youtube.com/watch?v=IpyCqRmaKW4&ab_channel=GeeksforGeeks\">Tutoriale GeeksforGeeks</a></li>"
                + "<li><a href=\"https://www.youtube.com/watch?v=-b2lciNd2L4&ab_channel=Jenny%27sLecturesCSIT\">Lecții Jenny's Lectures CS/IT</a></li>"
                + "</ul>"
                + "</body></html>";

        // Afisează conținutul HTML în WebView
        infoWebView.loadDataWithBaseURL(null, theory, "text/html", "UTF-8", null);
    }
}
