package lupricht.development.de.pongaping.GUI.Options;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import lupricht.development.de.pongaping.Game.Tools.Tool;
import lupricht.development.de.pongaping.R;


public class Options extends AppCompatActivity {

    private Button options_ball, options_music, options_wallpaper, options_back, options_difficult,
            options_reset;
    private TextView options_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        init();
    }


    private void init() {
        options_text = findViewById(R.id.options_reset_text);
        //Ball,Wallpaper,difficult,music,, reset,back
        options_ball = findViewById(R.id.options_reset_yes);
        options_wallpaper = findViewById(R.id.options_reset_no);
        options_difficult = findViewById(R.id.options_difficult);
        options_music = findViewById(R.id.options_music);
        options_reset = findViewById(R.id.options_reset);
        options_back = findViewById(R.id.options_back);
        options_ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tool.getAppContext(), Options_Ball.class));
            }
        });
        options_wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tool.getAppContext(), Options_wallpaper.class));
            }
        });
        options_difficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tool.getAppContext(), Options_difficult.class));
            }
        });
        options_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tool.getAppContext(), Options_music.class));
            }
        });
        options_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tool.getAppContext(), Options_reset.class));
            }
        });
        options_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}