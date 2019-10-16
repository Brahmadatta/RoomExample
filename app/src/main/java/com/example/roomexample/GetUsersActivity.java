package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.List;

public class GetUsersActivity extends AppCompatActivity {

    RecyclerView usersRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_users);

        usersRecyclerView = findViewById(R.id.usersRecyclerView);

        getTasks();

        /*setListData(users);*/
    }

    private void getTasks() {

        class GetTasks extends AsyncTask<Void,Void, List<User>>{

            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> userList = UserDatabase.getInstance(getApplicationContext())
                        .mUserDao().getAllUsers();
                return userList;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
                UserAdapter adapter = new UserAdapter(users,GetUsersActivity.this);
                usersRecyclerView.setLayoutManager(new LinearLayoutManager(GetUsersActivity.this));
                usersRecyclerView.setHasFixedSize(true);
                usersRecyclerView.setAdapter(adapter);
            }
        }
        GetTasks getTasks = new GetTasks();
        getTasks.execute();
    }
}
