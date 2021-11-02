package github.com.arnaumolins.flamingoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class WarningActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView banner3;
    private Button play;
    private ProgressBar progressBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);

        banner3 = (TextView) findViewById(R.id.banner3);
        banner3.setOnClickListener(this);
        play = (Button) findViewById(R.id.playButton);
        play.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banner3:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.playButton:
                Toast.makeText(WarningActivity.this, "Game starting now!", Toast.LENGTH_LONG).show();
        }
    }
}