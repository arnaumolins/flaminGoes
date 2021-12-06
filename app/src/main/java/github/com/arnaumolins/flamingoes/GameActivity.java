package github.com.arnaumolins.flamingoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    public static TextView rewardCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        rewardCounter = (TextView) findViewById(R.id.counterReward);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_game);

    }

    public static void GameOver(Boolean finish, Context mContext){
        if (finish == true){
            Intent gameOver = new Intent(mContext, GameOver.class);
            mContext.startActivity(gameOver);
        }
    }

    public static void setRewardCounter(String stringReward){
        rewardCounter.setText(stringReward);
    }
}