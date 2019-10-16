package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    EditText username, place, country;
    Button add;
    AsyncTask<List<User>, Void, List<User>> addTask;
    AsyncTask<Void, Void, List<User>> retrievTask;
    UserDatabase mUserDatabase;
    List<User> mUserList;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.recyclerView);
        username = findViewById(R.id.username);
        place = findViewById(R.id.place);
        country = findViewById(R.id.country);
        add = findViewById(R.id.addUser);


    }





    private boolean validateFields() {

        if (username.getText().toString().isEmpty()) {
            username.setError("please enter user name");
            username.requestFocus();
        } else if (place.getText().toString().isEmpty()) {
            place.setError("please enter place");
            place.requestFocus();
        } else if (country.getText().toString().isEmpty()) {
            country.setError("please enter country");
            country.requestFocus();
        }
        return false;
    }

    public void onUserAdd(View view) {

            final String name = username.getText().toString().trim();
            final String plc = place.getText().toString().trim();
            final String cntry = country.getText().toString().trim();


        if (username.getText().toString().isEmpty()) {
            username.setError("please enter user name");
            username.requestFocus();
            return;
        } else if (place.getText().toString().isEmpty()) {
            place.setError("please enter place");
            place.requestFocus();
            return;
        } else if (country.getText().toString().isEmpty()) {
            country.setError("please enter country");
            country.requestFocus();
            return;
        }

            //User user = new User(username.getText().toString(),place.getText().toString(),country.getText().toString());
            /*User user = new User();
            user.setName(username.getText().toString());
            user.setPlace(place.getText().toString());
            user.setCountry(country.getText().toString());
            mUserList.add(user);*/

            /*addTask = new InsertUsersTask();
            addTask.execute(mUserList);*/

            /*@Override
            protected List<User> doInBackground(List<User>... lists) {
                List<User> user = lists[0];

                UserDao userDao = UserDatabase.getInstance(getApplication()).mUserDao();
                ArrayList<User> userArrayList = new ArrayList<>(user);
                List<Long> result = userDao.insertAll(userArrayList.toArray(new User[0]));

                int i = 0;

                while (i < user.size()) {

                    user.get(i).id = result.get(i).intValue();
                    ++i;
                }
                return user;
            *//*mWeakReference.get().mUserDatabase.mUserDao().insertAll(mUser);
            return mUser;*//*
            }

            @Override
            protected void onPostExecute(List<User> users) {
                setListData(users);
            }
        }*/

            class OnUserAdd extends AsyncTask<List<User>, Void, List<User>> {


                @Override
                protected List<User> doInBackground(List<User>... users) {

                    //List<User> userList = users[0];

                    User user = new User();
                    user.setName(name);
                    user.setPlace(plc);
                    user.setCountry(cntry);

                    ArrayList<User> newList = new ArrayList<User>();
                    //List<Long> result = UserDatabase.getInstance(getApplicationContext()).mUserDao().insertAll(user);
                    List<Long> result = UserDatabase.getInstance(getApplicationContext()).mUserDao().insertAll(newList.toArray(new User[0]));

                    int i = 0;
                    while (i < newList.size()){
                        newList.get(i).id = result.get(i).intValue();
                        ++i;
                    }
                    return newList;
                }

                @Override
                protected void onPostExecute(List<User> users) {
                    super.onPostExecute(users);
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                    mHandler = new Handler();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("data",""+users.size());
                        }
                    },5000);

                    startActivity(new Intent(MainActivity.this,GetUsersActivity.class));
                }
            }

            OnUserAdd userAdd = new OnUserAdd();
            userAdd.execute();

    }


        private void setListData (List<User> users) {
            UserAdapter userAdapter = new UserAdapter(users, this);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(userAdapter);
        }


        /*private class RetrieveUser extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                return UserDatabase.getInstance(MainActivity.this).mUserDao().getAllUsers();
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
                setListData(users);
            }
        }*/
    }
