package lupricht.development.de.pongaping.GUI.Options;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import lupricht.development.de.pongaping.GUI.Game.Game_Init;
import lupricht.development.de.pongaping.Game.Settings;
import lupricht.development.de.pongaping.Game.Tools.Tool;
import lupricht.development.de.pongaping.R;


public class Game_Gamemode extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__gamemode);
        main();
    }


    private void main() {
        final Button resume, classic, hard, multiplayer, back;
        resume = findViewById(R.id.gamemode_resume);
        classic = findViewById(R.id.gamemode_classic);
        hard = findViewById(R.id.gamemode_Hard);
        multiplayer = findViewById(R.id.gamemode_multiplayer);
        back = findViewById(R.id.gamemode_back);
        if (Tool.load() == null) {
            resume.setVisibility(View.INVISIBLE);
        }


        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Settings.loadGame = true;
                startActivity(new Intent(Tool.getAppContext(), Game_Init.class));
                finish();
            }
        });

        classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Settings.loadGame = false;
                Settings.gameMode = 0;
                startActivity(new Intent(Tool.getAppContext(), Game_Init.class));
                finish();
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Settings.loadGame = false;
                Settings.gameMode = 2;
                startActivity(new Intent(Tool.getAppContext(), Game_Init.class));
                finish();
            }
        });

        multiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Settings.loadGame = false;
                Settings.gameMode = 1;
                startActivity(new Intent(Tool.getAppContext(), Internet.class));

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}