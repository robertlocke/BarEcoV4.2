package com.example.barecov1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//https://www.youtube.com/watch?v=NT1qxmqH1eM
//http://youtu.be/KxlLsk5j3rY

public class RegisterActivity extends AppCompatActivity {

    private Button registerUser;
    private EditText email;
    private EditText password;
    private EditText rePassword;
    private String strEmail;
    private String strPassword;
    private String strRePassword;

    DatabaseHelper helper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUser = (Button)findViewById(R.id.btnUpdate);
        email = (EditText)findViewById(R.id.txtEmail);
        password = (EditText)findViewById(R.id.txtPassword);
        rePassword = (EditText)findViewById(R.id.txtRePassword);

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    //http://youtu.be/KxlLsk5j3rY
    //nearly identical except naming conventions changed
    public void addUser() {

        strEmail = email.getText().toString();
        strPassword = password.getText().toString();
        strRePassword = rePassword.getText().toString();

        if (strPassword.equals(strRePassword)) {
            if (strEmail != null || strPassword != null) {
                User user = new User();
                user.setEmail(strEmail);
                user.setPassword(strPassword);
                boolean added = helper.addUser(user);

                if(added = true) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast error = Toast.makeText(RegisterActivity.this, "Something Wrong", Toast.LENGTH_SHORT);
                    error.show();
                }

            } else {
                Toast error = Toast.makeText(RegisterActivity.this, "One of the fields is blank", Toast.LENGTH_SHORT);
                error.show();
            }
        }else{
            Toast error = Toast.makeText(RegisterActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT);
            error.show();

        }
    }

}
