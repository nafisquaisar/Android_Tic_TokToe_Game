package com.example.NFtictoktoegame;

import com.example.NFtictoktoegame.R.*;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class WinDailog extends Dialog {

   private final String message;
   private final MainActivity mainActivity;
    public WinDailog(@NonNull Context context,String message,MainActivity mainActivity) {
        super(context);
        this.message=message;
        this.mainActivity=mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.win_dialog_layout);

        final TextView winTextView=findViewById(id.win_message_text);
        final Button startAgainButton=findViewById(R.id.start_again_button);

        winTextView.setText(message);

        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.restartMatch();
                dismiss();
            }
        });
    }
}
