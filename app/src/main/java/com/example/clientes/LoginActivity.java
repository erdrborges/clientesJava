package com.example.clientes;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
//import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "loginActivity";
    private FirebaseAuth mAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        //mapeamento de botão e tratamento de evento onClick
        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((EditText)findViewById(R.id.etEmailUsr)).getText().toString();
                String senha = ((EditText)findViewById(R.id.etSenhaUsr)).getText().toString();
                if(!email.isEmpty() && !senha.isEmpty()){
                    login(email, senha);
                }else{
                    Snackbar.make(findViewById(R.id.container_al), "Os campos são obrigatórios.", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        findViewById(R.id.bt_cad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((EditText)findViewById(R.id.etEmailUsr)).getText().toString();
                String senha = ((EditText)findViewById(R.id.etSenhaUsr)).getText().toString();
                if(!email.isEmpty() && !senha.isEmpty()){
                    cadastro(email, senha);
                }else{
                    Snackbar.make(findViewById(R.id.container_al), "Os campos são obrigatórios.", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    user = mAuth.getCurrentUser();
                    Log.d(TAG, Objects.requireNonNull(user).getUid());
                    startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure ",  task.getException());
                    if(Objects.requireNonNull(task.getException()).getMessage().contains("password")){
                        Snackbar.make(findViewById(R.id.container_al), R.string.password_fail, Snackbar.LENGTH_LONG).show();
                        //Toast.makeText(LoginActivity.this, "Senha não cadastrada.", Toast.LENGTH_SHORT).show();
                    }else{
                        Snackbar.make(findViewById(R.id.container_al), R.string.email_fail, Snackbar.LENGTH_LONG).show();
                        //Toast.makeText(LoginActivity.this, "Email não cadastrado.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void cadastro(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign up success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    user = mAuth.getCurrentUser();
                    startActivity(new Intent(LoginActivity.this, ClientesActivity.class));
                } else {
                    // If sign up fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    if(Objects.requireNonNull(task.getException()).getMessage().contains("email")){
                        Snackbar.make(findViewById(R.id.container_al), R.string.email_already, Snackbar.LENGTH_LONG).show();
                    }else {
                        Snackbar.make(findViewById(R.id.container_al), R.string.signup_fail, Snackbar.LENGTH_LONG).show();
                    }
                    //Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }
            }
        });
    }
}
