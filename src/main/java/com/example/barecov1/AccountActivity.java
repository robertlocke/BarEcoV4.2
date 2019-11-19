package com.example.barecov1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AccountActivity extends AppCompatActivity {

    private Button update;
    private EditText changeEmail;
    private EditText changePassword;
    private TextView delete;
    private String email;
    private String newEmail;
    private String newPassword;

    User user =  new User();

    DatabaseHelper helper = new DatabaseHelper(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        email = getIntent().getStringExtra("user_email");
        String[] userDetails = helper.searchPassword(email);

        user.setId(Integer.parseInt(userDetails[0]));
        user.setEmail(userDetails[1]);
        user.setPassword(userDetails[2]);


        update = (Button)findViewById(R.id.btnUpdate);
        changeEmail = (EditText)findViewById(R.id.txtEmail);
        changePassword = (EditText)findViewById(R.id.txtPassword);
        delete = (TextView)findViewById(R.id.txtDelete);

        changeEmail.setText(user.getEmail());
        changePassword.setText(user.getPassword());


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(user.getEmail());
                Toast deleted = Toast.makeText(AccountActivity.this, "Account Deleted", Toast.LENGTH_SHORT);
                deleted.show();
                openMainActivity();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
                if(updateUser() == true){
                    Toast updated = Toast.makeText(AccountActivity.this, "User Details Updated", Toast.LENGTH_SHORT);
                    updated.show();
                    openHomeActivity();
                }
            }
        });



    }


    public boolean updateUser(){
        newEmail = changeEmail.getText().toString();
        newPassword = changePassword.getText().toString();

        helper.updateDate(Integer.toString(user.getId()), newEmail, newPassword);
        return true;
    }

    public boolean deleteUser(String email){
        String[] userDetails = helper.searchPassword(email);
        helper.deleteData(userDetails[0]);
        return true;
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
