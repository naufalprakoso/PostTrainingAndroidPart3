package com.fj.practice.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fj.practice.R;
import com.fj.practice.adapter.FriendAdapter;
import com.fj.practice.database.FriendHelper;
import com.fj.practice.model.Friend;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Friend> friends;
    private FriendHelper friendHelper;
    private FriendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddFriendActivity.class);
                startActivity(intent);
            }
        });

        final RecyclerView recyclerView = findViewById(R.id.rv_data);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        friends = new ArrayList<>();
        friendHelper = new FriendHelper(this);
        friendHelper.open();

        adapter = new FriendAdapter(this);
        adapter.setFriends(friends);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new LoadFriendData().execute();
    }

    public class LoadFriendData extends AsyncTask<Void, Void, ArrayList<Friend>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Friend> doInBackground(Void... voids) {
            return friendHelper.allFriends();
        }

        @Override
        protected void onPostExecute(ArrayList<Friend> friendsList) {
            super.onPostExecute(friendsList);

            friends = friendsList;
            adapter.setFriends(friends);
            adapter.notifyDataSetChanged();
        }
    }

}
