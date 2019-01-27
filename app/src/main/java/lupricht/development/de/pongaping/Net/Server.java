package lupricht.development.de.pongaping.Net;

import android.graphics.Point;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

import lupricht.development.de.pongaping.Entity.Player;
import lupricht.development.de.pongaping.Game.Settings;


public class Server extends Thread implements ConnectionType {

    private final String tag = "SERVER";


    private DatagramSocket socket;
    private boolean running;
    private DataPack dataPack = new DataPack();
    private int playernum = 0;
    private Point touchpos = new Point(0, 0);
    private Point rightpos = new Point(0, 0);
    private Point leftpos = new Point(0, 0);
    private Point ballpos = new Point(0, 0);
    private ArrayList<Player> players = new ArrayList<>();


    public Server(int server_port) {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                heartBeat();
            }
        }, 2000, 1000);
        try {
            this.socket = new DatagramSocket(server_port);
            players.add(new Player(players.size(), socket.getLocalAddress(), socket.getLocalPort(),
                    Settings.username));
        } catch (SocketException e) {
            e.printStackTrace();
        }
        running = true;
        this.start();
    }

    public Server(int server_port, DataPack dataPack) {
        this.dataPack = dataPack;
        try {
            this.socket = new DatagramSocket(server_port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        running = true;
        this.start();
    }


    public void run() {
        while (running) {
            synchronized (players) {
                byte[] data = new byte[1024];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                Log.w(tag, "Server is waiting for message...");
                try {
                    this.socket.receive(packet);
                    Log.i("SERVER ",
                            "Message to SERVER " + new String(packet.getData()).trim());
                } catch (IOException e) {
                    Log.e(tag, "error", e);
                    e.printStackTrace();
                }
                String[] message = new String(packet.getData()).trim().split(",");


                switch (getNUM(message[0])) {
                    case UPDATE_FROM_CLIENT:
                        switch (getNUM(message[1])) {
                            case PLAYER_DATA:
                                //message=PLAYER_DATA+","+players.size ()+","+player.getId ()+","+player.getX () +","+player.getY ()+","+touchpos.x+","+touchpos.y+",";


                                players.get(getNUM(message[1])).setY(Integer
                                        .parseInt(message[3]));
                                players.get(getNUM(message[1])).getTouch().set
                                        (getNUM(message[4]), getNUM(message[5]));
                                break;
                            case BALL:
                                break;
                        }
                        break;
                    case LOGIN:
                        players.add(new Player(players.size(), packet.getAddress(), packet
                                .getPort(), message[1]));
                        Log.i("NEW_USER", new String(packet.getData()).trim() + "///" + packet
                                .getAddress()
                                + ":" + packet
                                .getPort() + " " +
                                "" + message[1]);
                        break;
                }
				
				
				
				
			/*	int connecttype = Integer.parseInt (message[0]);
				switch ( connecttype ) {
					case LOGIN:
						playerip[playernum]= new IP_ADRESSES (playernum,packet.getAddress (),packet
							.getPort ());
						Log.i ("LOGIN " + packet
							       .getAddress ()
							       .getHostAddress () + ":" + packet.getPort () ,
						       packet
							       .getAddress ()
							       .getHostAddress () + ":" + packet.getPort () + " Logged in with " + "Username " + message[1]);
						players[playernum] = new Player (playernum , message[1],true);
						sendData (PLAYER_DATA + "," + getPlayerData () ,
						          packet.getAddress () ,
						          packet.getPort ());
						break;
					case DISCONNECT:
						players[Integer.parseInt (message[1])].setConnected (false);
						Settings.startGame=false;
						break;
					case UPDATE_FROM_CLIENT:
						
						break;
					case PLAYER_DATA:
						players = new Player[Integer.parseInt (message[1])];
						for ( int i = 0; i < players.length; i += 3 ) {
							players[i] = new Player (Integer.parseInt (message[i + 1]) ,
							                         message[i + 2],true);
							Settings.startGame=true;
						}
						break;
					case HeartBeat:
						
						break;
					default:
						break;
				}*/
            }
        }
    }


    public Point getRightpos() {
        return rightpos;
    }


    public Point getLeftpos() {
        return leftpos;
    }

    private Point getPos(int id) {
        if (id == LEFT)
            return leftpos;
        return rightpos;
    }


    public Point getlefttouch() {


        return touchpos;
    }

    int getNUM(String num) {
        return Integer.parseInt(num);
    }

    private void heartBeat() {
        String message = HeartBeat + "," + players.size() + ",";
        for (Player player : players) {
            message = player.getId() + "," + player.getX() + ","
                    + player.getY() + "," + touchpos.x + "," + touchpos.y + ",";
        }
        for (Player player : players) {
            sendData(message.getBytes(), player.getIP(), player.getPort());
            Log.i("HeartBeat", player.getIP() + " " + player.getPort() + " " + message);
        }
    }


    public void sendData(byte[] data, InetAddress ip, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
        Log.i("SERVER", "Sending MESSAGE: " + new String(packet.getData()));
        try {
            this.socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData(String data, InetAddress ip, int port) {
        DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, ip, port);
        Log.i("SERVER", "Sending MESSAGE: " + new String(packet.getData()));
        try {
            this.socket.send(packet);
            Log.i("SERVER", "Sended Message: " + new String(packet.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress() + ":" + socket.getLocalPort();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return "NO IP";
    }


    public synchronized Player[] getPlayers() {
        return players.toArray(new Player[0]);
    }


    public boolean isRunning() {
        return running;
    }


    public void setRunning(boolean running) {
        this.running = running;
    }


    public void setRightPos(Point point) {
        rightpos = point;
    }


    public void setLeftPos(Point point) {
        leftpos = point;
    }


    public void setBallPos(Point point) {
        ballpos = point;
    }


    public void setPlayer(Player[] players) {
    }
}