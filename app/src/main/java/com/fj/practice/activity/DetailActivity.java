package com.fj.practice.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fj.practice.R;
import com.fj.practice.model.Friend;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        final Friend friend = intent.getParcelableExtra("FriendData");

        final TextView txtName = findViewById(R.id.txt_name);
        final TextView txtPhone = findViewById(R.id.txt_phone);

        txtName.setText(friend.getName());
        txtPhone.setText(friend.getPhone());
    }
}
