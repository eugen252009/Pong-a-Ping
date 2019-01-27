package lupricht.development.de.pongaping.Game.gamemodes;

import android.graphics.Canvas;
import android.graphics.Point;

import lupricht.development.de.pongaping.Entity.Player;
import lupricht.development.de.pongaping.Game.Settings;
import lupricht.development.de.pongaping.Net.Client;
import lupricht.development.de.pongaping.Net.Server;


public class Multiplayer {


    private Client client;
    private Server server;
    private boolean serverbool = Settings.serverbool;
    private Classic classic = new Classic();
    private int rightx, righty;


    private final String TAG = "MULTIPLAYER";


    public Multiplayer() {
        init();
    }

    private void init() {
        if (serverbool) {
            server = Settings.server;
            server.setPlayer(new Player[]{
                    classic.getLeft(),
                    classic.getRight()
            });
        }
        if (!serverbool) {
            client = Settings.client;
            client.login();

        }
    }


    public void draw(Canvas canvas) {
        classic.draw(canvas);
    }


    public void render(Point touch) {
        if (serverbool) {
            classic.render(touch);
        } else {


            classic.render(touch);
        }
		
		
		
		
		
		
		/*if (serverbool){
			classic.render(touch,server.getlefttouch());
			server.setRightPos(classic.getRight ().getPoint ());
			server.setLeftPos(classic.getLeft ().getPoint ());
			server.setBallPos(classic.getBall ().getPoint ());
		}
		if (!serverbool){
			classic.getLeft ().setPos (client.getLeftpos ());
			classic.getRight ().setPos (client.getRightpos ());
			client.sendint (ConnectionType.UPDATE_FROM_CLIENT,3,touch.x,touch.y);
		}*/
    }
//	//ID
    //	private final int BALL=0;
    //	private final int LEFT=1;
    //	private final int RIGHT=2;
    //	private final int TOUCH=3;

    public void screenchange() {
        classic.screenchange();
    }

    public void save(int gamemode) {
        classic.save(gamemode);
    }

}
