package lupricht.development.de.pongaping.Net;

import android.graphics.Point;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import lupricht.development.de.pongaping.Entity.Player;
import lupricht.development.de.pongaping.Game.Settings;


public class Client extends Thread implements ConnectionType {

    private final String TAG = "CLIENT";
    //ID
    private final int BALL = 0;
    private final int LEFT = 1;
    private final int RIGHT = 2;
    private final int TOUCH = 3;


    private DatagramSocket socket;
    private String ip;
    private InetAddress ipAdress;
    private int server_port;
    private boolean running;
    private ArrayList<Player> players = new ArrayList<>();
    private DatagramPacket send;
    private int id = 1;
    private byte[] data = new byte[1024];
    private String[] message;
    private Point rightpos = new Point(0, 0);
    private Point leftpos = new Point(0, 0);
    private Point ballpos = new Point(0, 0);
    private Point touchpos = new Point(0, 0);


    public Client(String ip, int server_port) {
        this.ip = ip;
        this.server_port = server_port;
        running = true;
    }


    public synchronized void run() {
        try {
            this.socket = new DatagramSocket();
            this.ipAdress = InetAddress.getByName(ip);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        while (running) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            Log.w(TAG, "Wait for Message from Server  " + "/" + ip + "/" + server_port);
            try {
                this.socket.receive(packet);
                Log.i(TAG, new String(packet.getData()).trim());
            } catch (IOException e) {
                e.printStackTrace();
            }
            message = new String(packet.getData()).trim().split(",");
            switch (getNUM(message[0])) {

                //UPDATE_FROM_SERVER
                case UPDATE_FROM_SERVER:
                    switch (getNUM(message[1])) {
                        case PLAYER_DATA:
                            players.get(getNUM(message[1])).setY(getNUM(message[2]));
                            break;
                        case BALL:
                            ballpos.set(getNUM(message[2]), getNUM(message[3]));
                            break;
                    }
                    break;

                //HEARTBEAT
                case HeartBeat:
                    for (int i = 2; i < getNUM(message[1]); i += 3) {
                        players.get(getNUM(message[i])).setTouch(getNUM(message[i + 1]), getNUM
                                (message[i + 2]));
                    }
					
						
						
						/*		String message=Heartbeat+","+players.size ()+",";
		for (Player player  : players) {
			message=player.getId ()+","+player.getX () +","
				+player.getY ()+","+touchpos.x+","+touchpos.y+",";
		}
		for (Player player  : players) {
			sendData (message.getBytes (),player.getIP (),player.getPort ());
			Log.i ("HeartBeat",player.getIP ()+" "+player.getPort ()+" "+message);
		}
		
		PLAY
		
						*
						* */

                    sendData((UPDATE_FROM_CLIENT + "," + TOUCH + "," + getTouchpos().x + "," + getTouchpos().y).getBytes());
                    break;
            }
        }
    }


    int getNUM(String num) {
        return Integer.parseInt(num);
    }

    private byte[] bytes(String s) {
        return s.getBytes();
    }


    public void sendData(byte[] bytes) {
        this.data = bytes;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                data = Settings.client.data;
                if (socket == null) {
                    Log.e("NO CLIENT SOCKET", "NO CLIENT SOCKET!");
                    return;
                }
                try {
                    send = new DatagramPacket(data, data
                            .length,
                            ipAdress,
                            server_port);
                    socket.send(send);
                    Log.i("send", new String(data).trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }


////ID
//	private final int BALL=0;
//	private final int LEFT=1;
//	private final int RIGHT=2;
//	private final int TOUCH=3;


    public void sendint(int contype, int id, int var1, int var2) {
        sendData(bytes(contype + "," + id + "," + var1 + "," + var2));
    }

    public Point getRightpos() {
        return rightpos;
    }


    public Point getLeftpos() {
        return leftpos;
    }


    public Player[] getPlayers() {
        return (Player[]) players.toArray();
    }

    public void setTouch(Point touch) {
        touchpos = touch;
    }


    public Point getBallpos() {
        return ballpos;
    }


    public Point getTouchpos() {
        return touchpos;
    }

    public void login() {
        String message = (LOGIN + "," + Settings.username);
        sendData(message.getBytes());

    }
}
/*
			
				DATEN EMPFANGEN UND VERARBEITEN
				
					message = new String (packet.getData ()).trim ().split (",");
				int connecttype = Integer.parseInt (message[0]);
				switch ( connecttype ) {
					case DISCONNECT:
				sendData ((DISCONNECT+","+players[id].getId ()).getBytes ());
				socket.disconnect ();
						break;
					case UPDATE_FROM_CLIENT:
						break;
					case UPDATE_FROM_SERVER:
						break;
					case PLAYER_DATA:
						this.players = new Player[Integer.parseInt (message[1])];
						for ( int i = 0; i < players.length; i += 3 ) {
							this.players[i] = new Player (Integer.parseInt (this.message[i + 1]) ,
							                              this.message[i + 2],true);
						}
						break;
					case 	HeartBeat:
						this.players = new Player[Integer.parseInt (message[1])];
						for ( int i = 0; i < players.length; i += 3 ) {
							this.players[i] = new Player (Integer.parseInt (this.message[i + 1]) ,
							                              this.message[i + 2],true);
						}
//						sendData ((HeartBeat+","+getPlayerData ()).getBytes ());
						break;
					case 	START_GAME:
						Settings.startGame=true;
						break;
				}*/