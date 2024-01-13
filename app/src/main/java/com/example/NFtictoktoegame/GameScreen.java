package com.example.NFtictoktoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class GameScreen extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        final EditText player1,player2;
        final Button StartButton ;

        player1=findViewById(R.id.player1Name);
        player2=findViewById(R.id.player2Name);
        StartButton=findViewById(R.id.GameStartButton);

        StartButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                final String player1Name=player1.getText().toString();
                final String player2Name=player2.getText().toString();

                if(player1Name.equals("") || player2Name.equals("")  ) {
                    Toast.makeText(GameScreen.this, "Please Enter Both Player Name", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent=new Intent(GameScreen.this, MainActivity.class);
                    intent.putExtra("Player1",player1Name);
                    intent.putExtra("Player2", player2Name);
                    startActivity(intent);
                }
            }
        });

    }
}