package com.example.NFtictoktoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // take all the 9 id
    ImageView btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;


    // make a List
    private final List<int[]> combinationList=new ArrayList<>();

    // make an array
    private int[]boxPositions={0,0,0,0,0,0,0,0,0};
    // make a varaible to find whose play
    private int playerTurn=1;

    // make a variable to incres the box who filled
    private int totalSelectedBox=1;

    // make a layout to show the name of player and icon either 0 or X
    private LinearLayout player1Layout,player2Layout;

    // make a text view to show the player name
    private TextView player1Name,player2Name;


//    private TextView getcountResultX,getCountResultO;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        // get name and layout  in variable through id
        player1Name=findViewById(R.id.player1Name);
        player2Name=findViewById(R.id.player2Name);
        player1Layout=findViewById(R.id.player1Layout);
        player2Layout=findViewById(R.id.player2Layout);

        // make the combination list to find when player win or not
        combinationList.add(new int[]{0,1,2});
        combinationList.add(new int[]{3,4,5});
        combinationList.add(new int[]{6,7,8});
        combinationList.add(new int[]{0,3,6});
        combinationList.add(new int[]{1,4,7});
        combinationList.add(new int[]{2,5,8});
        combinationList.add(new int[]{2,4,6});
        combinationList.add(new int[]{0,4,8});


        // get name through from GameScreen layout and set on  String variable
        final String getPlayer1Name=getIntent().getStringExtra("Player1");
        final String getPlayer2Name=getIntent().getStringExtra("Player2");

        // set name on game top to find which player turn
        player1Name.setText(getPlayer1Name);
        player2Name.setText(getPlayer2Name);



        // all the condition put on each button

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(0)){
                 performAction((ImageView)view,0);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(1)){
                    performAction((ImageView)view,1);
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(2)){
                    performAction((ImageView)view,2);
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(3)){
                    performAction((ImageView)view,3);
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(4)){
                    performAction((ImageView)view,4);
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(5)){
                    performAction((ImageView)view,5);
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(6)){
                    performAction((ImageView)view,6);
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(7)){
                    performAction((ImageView)view,7);
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(8)){
                    performAction((ImageView)view,8);
                }
            }
        });


    }


   // **************** this function used to point or perform game to set 0 or X **************
    private void performAction(ImageView imageView,int selectedBoxPosition){

        boxPositions[selectedBoxPosition]=playerTurn;

        if(playerTurn==1){
            // set the cross image on the board
            imageView.setImageResource(R.drawable.first_tictok);

            //check the player is win or not if win then if condition run
            if(checkPlayerWin()){
            WinDailog winDailog=new WinDailog(MainActivity.this,player1Name.getText().toString()+" has won the Game",MainActivity.this);
            winDailog.setCancelable(false);
            winDailog.show();

            } else if (totalSelectedBox==9) {
                WinDailog winDailog=new WinDailog(MainActivity.this,"Match Has been Draw",MainActivity.this);
                winDailog.setCancelable(false);
                winDailog.show();
            }
            else {
                changePlayerTurn(2);
                totalSelectedBox++;
            }


        }else{

            //set O in the board
            imageView.setImageResource(R.drawable.circle_tictok);
            if(checkPlayerWin()){
                WinDailog winDailog=new WinDailog(MainActivity.this,player2Name.getText().toString()+" has won the Game",MainActivity.this);
                winDailog.setCancelable(false);
                winDailog.show();

            } else if (totalSelectedBox==9) {
                WinDailog winDailog=new WinDailog(MainActivity.this,"Match Has been Draw",MainActivity.this);
                winDailog.setCancelable(false);
                winDailog.show();
            }
            else {
                changePlayerTurn(1);
                totalSelectedBox++;
            }

        }
    }


    private void changePlayerTurn(int currentPlayerTurn){
        playerTurn=currentPlayerTurn;

        if(playerTurn==1){
            player1Name.setBackgroundResource(R.drawable.round_back_border);
            player2Name.setBackgroundResource(R.drawable.round_back_dark_blue);
        }else {
            player2Name.setBackgroundResource(R.drawable.round_back_border);
            player1Name.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
    }
    private boolean checkPlayerWin(){

        boolean response=false;

        for (int i=0;i<combinationList.size();i++){
            final int[] combination=combinationList.get(i);

            if(boxPositions[combination[0]]==playerTurn && boxPositions[combination[1]]==playerTurn&& boxPositions[combination[2]]==playerTurn){
                response= true;
            }
        }
        return response;
    }
    // *********************************************************************************************



    // **********  this is the function to check the box is selected or not *******************
    private boolean isBoxSelectable (int boxPosition)
    {
        boolean response = false;

        if (boxPositions[boxPosition] == 0)
        {
            response = true;
        }
        return response;
    }
    // *********************************************************************************************



  public void restartMatch(){
        boxPositions=new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        playerTurn=1;
        totalSelectedBox=1;

      btn1.setImageResource(R.drawable.transparent2);
      btn2.setImageResource(R.drawable.transparent2);
      btn3.setImageResource(R.drawable.transparent2);
      btn4.setImageResource(R.drawable.transparent2);
      btn5.setImageResource(R.drawable.transparent2);
      btn6.setImageResource(R.drawable.transparent2);
      btn7.setImageResource(R.drawable.transparent2);
      btn8.setImageResource(R.drawable.transparent2);
      btn9.setImageResource(R.drawable.transparent2);
      player1Name.setBackgroundResource(R.drawable.round_back_border);
      player2Name.setBackgroundResource(R.drawable.round_back_dark_blue);


  }





//    public void check(View view){
//        // condition for game
//        Button currentButton=(Button) view;
//
//        if(currentButton.getText().toString().equals("")) {
//
//
//            count++;
//            if (flag == 0) {
//                currentButton.setText("X");
//                flag = 1;
//            } else {
//                currentButton.setText("O");
//                flag = 0;
//            }
//
//            if (count > 4) {
//                b1 = btn1.getText().toString();
//                b2 = btn2.getText().toString();
//                b3 = btn3.getText().toString();
//                b4 = btn4.getText().toString();
//                b5 = btn5.getText().toString();
//                b6 = btn6.getText().toString();
//                b7 = btn7.getText().toString();
//                b8 = btn8.getText().toString();
//                b9 = btn9.getText().toString();
//
//                if (b1.equals(b2) && b2.equals(b3) && !b1.equals("")) {
//                    //
//                    Toast.makeText(this, "Win Player" + b1, Toast.LENGTH_SHORT).show();
//                    Restart();
//                } else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("")) {
//                    Toast.makeText(this, "Win player " + b4, Toast.LENGTH_SHORT).show();
//                    Restart();
//
//                } else if (b7.equals(b8) && b8.equals(b9) && !b7.equals("")) {
//                    Toast.makeText(this, "Win player " + b7, Toast.LENGTH_SHORT).show();
//                    Restart();
//
//                } else if (b1.equals(b4) && b4.equals(b7) && !b1.equals("")) {
//                    Toast.makeText(this, "Win player " + b1, Toast.LENGTH_SHORT).show();
//                    Restart();
//
//                } else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("")) {
//                    Toast.makeText(this, "Win player " + b2, Toast.LENGTH_SHORT).show();
//                    Restart();
//
//                } else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("")) {
//                    Toast.makeText(this, "Win player " + b3, Toast.LENGTH_SHORT).show();
//                    Restart();
//
//                } else if (b1.equals(b5) && b5.equals(b9) && !b1.equals("")) {
//                    Toast.makeText(this, "Win player " + b1, Toast.LENGTH_SHORT).show();
//                    Restart();
//
//                } else if (b3.equals(b5) && b5.equals(b7) && !b3.equals("")) {
//                    Toast.makeText(this, "Win player " + b3, Toast.LENGTH_SHORT).show();
//                    Restart();
//
//                } else if (count == 9) {
//                    Toast.makeText(this, "Game Draw", Toast.LENGTH_SHORT).show();
//                    Restart();
//
//                }
//
//            }
//        }
//    }
//
//    public void Restart(){
//        btn1.setText("");
//        btn2.setText("");
//        btn3.setText("");
//        btn4.setText("");
//        btn5.setText("");
//        btn6.setText("");
//        btn7.setText("");
//        btn8.setText("");
//        btn9.setText("");
//        count=0;
//        flag=0;
//    }

    public void init(){
        btn1=findViewById(R.id.image1);
        btn2=findViewById(R.id.image2);
        btn3=findViewById(R.id.image3);
        btn4=findViewById(R.id.image4);
        btn5=findViewById(R.id.image5);
        btn6=findViewById(R.id.image6);
        btn7=findViewById(R.id.image7);
        btn8=findViewById(R.id.image8);
        btn9=findViewById(R.id.image9);




    }

}