package lupricht.development.de.pongaping.GUI.Game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import lupricht.development.de.pongaping.Game.Surface;


public class Game_Init extends AppCompatActivity {

    Surface surface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        //		dialog = new Dialog (this);
        //		decorView= getWindow().getDecorView();
        //		try {
        //			SharedPreferences sharedPreferences = PreferenceManager
        //				.getDefaultSharedPreferences(this);
        //			SharedPreferences.Editor editor = sharedPreferences.edit();
        //			editor.apply();
        //			Gson gson = new Gson();
        //			saveGame = gson.fromJson (sharedPreferences.getString(Key1, null), SaveGame.class);
        //			Log.i ("Loaded: ", sharedPreferences.getString (Key1, "NO DATA FOUND!"));
        //		} catch (Exception e) {
        //			e.printStackTrace();
        //		}
        //		if (saveGame == null) {
        //			surface = new Surface(this, this);
        //		} else {
        //			surface = new Surface(this, saveGame, this);
        //		}
        //		getWindow ().setFlags (
        //			WindowManager.LayoutParams.FLAG_FULLSCREEN ,
        //			WindowManager.LayoutParams.FLAG_FULLSCREEN
        //		                      );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        surface = new Surface(this);
        setContentView(surface);
    }
}
