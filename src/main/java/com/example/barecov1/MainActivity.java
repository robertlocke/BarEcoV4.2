package com.example.barecov1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    //code for switching activities https://www.youtube.com/watch?v=bgIUdb-7Rqo

    User user = new User();

    //declaring variables to assign to elements
    private EditText txtEmail;
    private EditText txtPassword;
    private TextView register;
    private Button login;


    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connecting variables and elements by id
        register = (TextView) findViewById(R.id.registerLink);
        login = (Button) findViewById(R.id.btnLogin);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);

         //Giving actions to the variables when they are clicked
         register.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openRegisterActivity();
             }
         });
         login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

    }

    //Opens the register activity
    public void openRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    //Login User and open home page
    //https://www.youtube.com/watch?v=NT1qxmqH1eM
    //http://youtu.be/KxlLsk5j3rY

    public void  loginUser() {

        String email = txtEmail.getText().toString();
        String pass = txtPassword.getText().toString();

        if (email != null && pass != null) {
            String[] userDetails = helper.searchPassword(email);

            if (pass.equals(userDetails[2])) {

                user.setId(Integer.parseInt(userDetails[0]));
                user.setEmail(userDetails[1]);
                user.setPassword(userDetails[2]);


                //https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android-application
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("user_id", user.getId());
                startActivity(intent);

            } else {
                Toast error = Toast.makeText(MainActivity.this, "The Username or Password is Incorect", Toast.LENGTH_SHORT);
                error.show();
            }

        }else{
            Toast error = Toast.makeText(MainActivity.this, "Please Enter Both a Username an Password", Toast.LENGTH_SHORT);
            error.show();
        }

    }


}
