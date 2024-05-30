package com.example.proiect_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class Vizualizare extends AppCompatActivity {

    private BinaryTreeView binaryTreeView;
    private EditText valueEditText;
    private Button insertButton;
    private Button travButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizare);

        binaryTreeView = findViewById(R.id.binaryTreeView);
        valueEditText = findViewById(R.id.valueEditText);
        insertButton = findViewById(R.id.insertButton);
        travButton = findViewById(R.id.trav);
        Button back =findViewById(R.id.qw);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Vizualizare.this, Main.class);
                startActivity(intent);
            }
        });

        insertButton.setOnClickListener(v -> {
            // Get the value from the EditText
            String valueStr = valueEditText.getText().toString();

            // Check if the input is valid
            if (!valueStr.isEmpty()) {
                int value = Integer.parseInt(valueStr);
                binaryTreeView.insertNode(value);
                valueEditText.setText(""); // Clear the EditText after inserting
            }
        });

        travButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String preorder = binaryTreeView.traversePreorder(binaryTreeView.root);
                String inorder = binaryTreeView.traverseInorder(binaryTreeView.root);
                String postorder = binaryTreeView.traversePostorder(binaryTreeView.root);


                Intent intent = new Intent(Vizualizare.this, Listare.class);
                intent.putExtra("preorder", preorder);
                intent.putExtra("inorder", inorder);
                intent.putExtra("postorder", postorder);
                startActivity(intent);


            }
        });


    }
}
