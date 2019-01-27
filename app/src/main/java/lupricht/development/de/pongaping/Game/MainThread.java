package lupricht.development.de.pongaping.Game;

import android.graphics.Canvas;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by eugen on 21.02.2017.
 */

public class MainThread extends Thread {

    public static final int MAX_FPS = 60;
    public static Canvas canvas;
    private double averangeFps;
    private boolean running;
    private Surface surface;
    private SurfaceHolder surfaceHolder;


    public MainThread(SurfaceHolder surfaceHolder, Surface surface) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.surface = surface;
    }

    public void run() {
        long startTime = 0;
        long timeMillis;
        long waitTime = 0;
        int framecount = 0;
        long totaltime = 0;
        long targetTime = 1000 / MAX_FPS;
        if (running) Log.i("Engine Starts", "wird gestartet");

        while (running) {

            //init

            startTime = System.nanoTime();
            canvas = null;

            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    canvas = this.surfaceHolder.lockHardwareCanvas();
                } else {
                    canvas = this.surfaceHolder.lockCanvas();
                }
                synchronized (surfaceHolder) {
                    this.surface.update();
                    this.surface.draw(canvas);
                }
            } catch (Exception e) {
                Log.e("Fehler in Mainthread", "52", e);
                setRunning(false);
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        Log.e("ERROR on Line", "59", e);
                        setRunning(false);
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;
            try {
                if (waitTime > 0) {
                    sleep(waitTime);
                }
            } catch (Exception e) {
                Log.e("MainThread.Run3", "69", e);
                setRunning(false);
            }
            totaltime += System.nanoTime() - startTime;
            framecount++;
            if (framecount == MAX_FPS) {
                averangeFps = 1000 / ((totaltime / framecount) / 1000000);
                framecount = 0;
                totaltime = 0;
                Log.i("FPS: ", averangeFps + "");
            }

        }
        Log.i("", "Engine ist aus");
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    public int getFPS() {
        return (int) averangeFps;
    }
}

