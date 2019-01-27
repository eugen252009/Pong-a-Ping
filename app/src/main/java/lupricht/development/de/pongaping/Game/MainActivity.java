package lupricht.development.de.pongaping.Game;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lupricht.development.de.pongaping.GUI.Options.About;
import lupricht.development.de.pongaping.GUI.Options.Game_Gamemode;
import lupricht.development.de.pongaping.GUI.Options.Options;
import lupricht.development.de.pongaping.Game.Tools.Tool;
import lupricht.development.de.pongaping.R;

public class MainActivity extends Activity {

    final int API = 0;


    private Button startgame, options, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        startgame = findViewById(R.id.main_start_game);
        options = findViewById(R.id.main_options);
        about = findViewById(R.id.main_about);
        startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tool.getAppContext(), Game_Gamemode.class));
            }
        });
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tool.getAppContext(), Options.class));
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tool.getAppContext(), About.class));
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Settings.server.setRunning(false);
        try {
            if (Settings.server != null)
                Settings.server.join();
            if (Settings.client != null)
                Settings.client.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
     /*   if (hasFocus) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }else {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN);
            }
        }
        super.onWindowFocusChanged(hasFocus);*/
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public int getAPI() {
        return API;
    }
}