package lupricht.development.de.pongaping.GUI.Options;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import lupricht.development.de.pongaping.Game.Tools.Tool;
import lupricht.development.de.pongaping.R;


public class Options_reset extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_reset);
        main();
    }

    void main() {
        Button yes, no;
        yes = findViewById(R.id.options_reset_yes);
        no = findViewById(R.id.options_reset_no);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tool.save(null);
                finish();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
