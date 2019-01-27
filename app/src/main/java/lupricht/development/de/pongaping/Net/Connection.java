package lupricht.development.de.pongaping.Net;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

import lupricht.development.de.pongaping.Entity.Player;
import lupricht.development.de.pongaping.GUI.Game.Game_Init;
import lupricht.development.de.pongaping.Game.Settings;
import lupricht.development.de.pongaping.Game.Tools.Tool;
import lupricht.development.de.pongaping.R;


public class Connection extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Button button;
    private ImageView imageView;
    private Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);


        main();
    }

    void main() {
        if (!Settings.serverbool) {
            Settings.client = new Client(Settings.ipAdress, Settings.port);
            Settings.client.start();
            Settings.client.login();
        }

        imageView = findViewById(R.id.connection_refresh);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        linearLayout = findViewById(R.id.connection_linearLayout);
        button = new Button(Tool.getAppContext());
        button.setText(Settings.server == null ? "" : Settings.server.getLocalIpAddress());
        button.setEnabled(false);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        button.setBackgroundColor(Color.TRANSPARENT);
        button.setTextColor(Color.WHITE);
        linearLayout.addView(button);
        update();
    }

    @Override
    public void onBackPressed() {
        if (!Settings.serverbool) {
            Settings.client.sendData((ConnectionType.DISCONNECT + "").getBytes());
        } else {
            Settings.server.setRunning(false);
        }
        finish();
    }

    private void refresh() {
        if (Settings.startGame) {
            startActivity(new Intent(Tool.getAppContext(), Game_Init.class));
            return;
        }

        int i = 0;
        Player[] players = new Player[2];
        linearLayout.removeAllViews();
        if (Settings.serverbool) {
            if (Settings.server == null) return;
            players = Settings.server.getPlayers();
        }
        if (!Settings.serverbool) {
            if (Settings.client.getPlayers() == null) {
                Settings.client.login();
                return;
            }
            players = Settings.client.getPlayers();
        }
        if (players == null) {
            return;
        }
        if (Settings.serverbool) {
            for (Player player : players) {
                Button newbutton = new Button(Tool.getAppContext());
                newbutton.setText(player.getId() + ". " + player.getUsername());
                newbutton.setEnabled(false);
                newbutton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                newbutton.setBackgroundColor(Color.TRANSPARENT);
                newbutton.setTextColor(Color.WHITE);
                linearLayout.addView(newbutton);
                i++;
            }
            if (i == players.length) {
                Settings.startGame = true;
            }

        }
        if (!Settings.serverbool) {
            startActivity(new Intent(Tool.getAppContext(), Game_Init.class));
        }


        if (Settings.startGame) {
            timer.cancel();
        }

    }

    public void update() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                    }
                });
            }
        }, 2000, 500);

    }
}