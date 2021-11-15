package github.com.arnaumolins.flamingoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView createAccountListener;
    private EditText email1, psswrd1;
    private ProgressBar progressBar1, progressBar3;
    private FirebaseAuth mAuth;
    private Button loginUser;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        createAccountListener = (TextView) findViewById(R.id.createAccount);
        createAccountListener.setOnClickListener(this);
        email1 = (EditText) findViewById(R.id.email);
        psswrd1 = (EditText) findViewById(R.id.password);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        loginUser = (Button) findViewById(R.id.loginUser);
        loginUser.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.createAccount:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.loginUser:
                loginUserFunction();
                break;
        }
    }

    private void loginUserFunction() {
        String emailString = email1.getText().toString().trim();
        String passwordString = psswrd1.getText().toString().trim();

        if(emailString.isEmpty()){
            email1.setError("Email is required!");
            email1.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
            email1.setError("Please enter a valid email!");
            email1.requestFocus();
            return;
        }

        if(passwordString.isEmpty()){
            psswrd1.setError("Password is required!");
            psswrd1.requestFocus();
            return;
        }

        if(passwordString.length() < 6){
            psswrd1.setError("Password must have at least 6 characters!");
            psswrd1.requestFocus();
            return;
        }

        progressBar1.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login passed successfully!", Toast.LENGTH_SHORT).show();
                    progressBar1.setVisibility(View.GONE);
                    startActivity(new Intent(MainActivity.this, WarningActivity.class));
                }else{
                    Toast.makeText(MainActivity.this, "Login failed! Check your credentials!", Toast.LENGTH_SHORT).show();
                    progressBar1.setVisibility(View.GONE);
                }
            }
        });

    }
}