package com.example.proiect_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Listare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listare);

        TextView preorder = findViewById(R.id.textViewPreorder);
        TextView inorder = findViewById(R.id.textViewInorder);
        TextView postorder = findViewById(R.id.textViewPostorder);
        Button  edit =findViewById(R.id.backedit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Listare.this, Vizualizare.class);
                startActivity(intent);
            }
        });

        preorder.setText(String.valueOf(preorder.getText()) + " " + getIntent().getStringExtra("preorder"));
        inorder.setText(String.valueOf(inorder.getText()) +" " +  getIntent().getStringExtra("inorder"));
        postorder.setText(String.valueOf(postorder.getText()) +" " +  getIntent().getStringExtra("postorder"));




    }
}