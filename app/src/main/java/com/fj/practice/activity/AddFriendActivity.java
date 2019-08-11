package com.fj.practice.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fj.practice.R;
import com.fj.practice.database.FriendHelper;
import com.fj.practice.model.Friend;

public class AddFriendActivity extends AppCompatActivity {

    private EditText edtName, edtPhone;

    private FriendHelper friendHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_phone);

        friendHelper = new FriendHelper(this);
        friendHelper.open();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();

                Friend friend = new Friend();
                friend.setName(name);
                friend.setPhone(phone);

                friendHelper.insert(friend);

                Toast.makeText(AddFriendActivity.this, "Friend added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
