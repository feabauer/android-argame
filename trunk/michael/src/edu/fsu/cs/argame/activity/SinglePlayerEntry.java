package edu.fsu.cs.argame.activity;

import edu.fsu.cs.argame.R;
import net.peterd.zombierun.game.GameSettings;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SinglePlayerEntry extends Activity {
  
  @Override
  public void onCreate(Bundle state) {
    super.onCreate(state);
    if (state != null) {
      onRestoreInstanceState(state);
    }
    setContentView(R.layout.singleplayer_entry);

    ((Button) findViewById(R.id.button_start_singleplayer)).setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            startGame();
          }
        });
  }
  
  private void startGame() {
    Bundle bundle = new Bundle();
    GameSettings settings = Util.handleGameSettings(this, false);
    settings.toBundle(bundle);
    
    Intent startGameIntent = new Intent(this, StartGame.class);
    startGameIntent.putExtras(bundle);
    startActivity(startGameIntent);
  }
}
