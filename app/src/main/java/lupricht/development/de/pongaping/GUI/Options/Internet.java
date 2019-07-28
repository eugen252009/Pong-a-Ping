package lupricht.development.de.pongaping.GUI.Options;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import lupricht.development.de.pongaping.Net.Client;
import lupricht.development.de.pongaping.Net.Server;
import lupricht.development.de.pongaping.R;


public class Internet extends AppCompatActivity {

    private Button back, next;
    private ToggleButton serverswitch;
    private EditText ip, username, port;
    private TextView status,player;
    private Server server;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        back = findViewById(R.id.internet_back);
        next = findViewById(R.id.internet_next);
        serverswitch = findViewById(R.id.internet_serverswitch);
        username = findViewById(R.id.internet_username);
        ip= findViewById(R.id.internet_ip);
        port= findViewById(R.id.internet_port);
        status= findViewById(R.id.internet_status);
        player = findViewById(R.id.internet_player);






        serverswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (serverswitch.isChecked()){
                    ip.setEnabled(false);
                }else {
                    ip.setEnabled(true);

                }
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("")){
                   username.setText("Du hattest nur einen Job");
                }





                next.setEnabled(false);
                username.setEnabled(false);
                serverswitch.setEnabled(false);
                ip.setEnabled(false);
                port.setEnabled(false);
                status.setText("Warte auf Spieler ...");
                status.setVisibility(View.VISIBLE);
                player.setVisibility(View.VISIBLE);
                player.setText("Spieler 1: \n"+username.getText());
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





//        server_ip_set_btn = findViewById(R.id.internet_set_server_ip);
////        back = findViewById(R.id.internet_back);
////        ip = findViewById(R.id.internet_ip);
////        username = findViewById(R.id.internet_username);
////        button2 = findViewById(R.id.button2);
////        button3 = findViewById(R.id.button3);
////        button4 = findViewById(R.id.button4);
////        button4.setText("Start Game");
////        button4.setEnabled(false);
////        init();
    }


//    private void init() {
//
//        //SET SERVER ADRESS
//        server_ip_set_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Settings.ipAdress = ip.getText().toString();
//            }
//        });
//        //server
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Settings.serverbool) {
//                    server.setRunning(false);
//                    try {
//                        server.join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Settings.serverbool = true;
//                Settings.username = username.getText().toString();
//                Settings.server = new Server(Settings.port);
//                button2.setEnabled(false);
//                button3.setEnabled(false);
//
//                button4.setEnabled(true);
//            }
//        });
//        //client
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Settings.client = new Client(ip.getText().toString(), Settings.port);
//                Settings.username = username.getText().toString();
//                button2.setEnabled(false);
//                button3.setEnabled(false);
//                button4.setEnabled(true);
//                Settings.client.start();
//                Settings.client.login();
//            }
//        });
//        //Start Game
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Settings.gameMode = 1;
//
//
//                startActivity(new Intent(Tool.getAppContext(), Game_Init.class));
//            }
//        });
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
}