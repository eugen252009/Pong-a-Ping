package lupricht.development.de.pongaping.Sound;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import lupricht.development.de.pongaping.Game.Tools.Tool;

/**
 * Created by eugen on 18.09.2017.
 */

public class Music {

    private int[] songs;
    private MediaPlayer player;
    private Context context;
    private int num = 0;

    public Music() {
        this.songs = new int[5];
//        this.songs[0] = R.raw.music0;
//        this.songs[1] = R.raw.music1;
//        this.songs[2] = R.raw.music2;
//        this.songs[3] = R.raw.music3;
//        this.songs[4] = R.raw.music4;
        this.context = Tool.getAppContext();
    }

    public void startSong(int num) {
        this.num = num;
        if (num == -1) {
            Log.i("Song", "Song off");
            return;
        }
        try {
            if (player != null) player.release();
            player = MediaPlayer.create(context, songs[this.num]);
            player.start();
            player.setLooping(true);

            Log.i("Song", "Song " + (num + 1) + " Started " + songs[this.num]);
        } catch (Exception e) {
            Log.e("Sound", "Error at Line 48", e);
        }
    }

    public void pause() {
        try {
            if (player != null)
                player.pause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        try {
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
            startSong(num);
        }
    }

    public int getNum() {
        return num;
    }

    public void setVolume(float volume) {
        player.setVolume(volume, volume);
    }
}
