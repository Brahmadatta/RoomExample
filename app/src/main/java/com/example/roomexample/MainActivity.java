package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    EditText username,place,country;
    Button add;
    AsyncTask<List<User>,Void,List<User>> mAddTask;
    UserDatabase mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.recyclerView);
        username = findViewById(R.id.username);
        place = findViewById(R.id.place);
        country = findViewById(R.id.country);
        add = findViewById(R.id.addUser);


        loadData();

    }





    public void onUserAdd(){
        if (validateFields()){

            User user = new User(username.getText().toString(),place.getText().toString(),country.getText().toString());
            new InsertUsersTask().execute();
        }
    }

    private boolean validateFields() {

        if (username.getText().toString().isEmpty()){
            username.setError("please enter user name");
            username.requestFocus();
        }else if (place.getText().toString().isEmpty()){
            place.setError("please enter place");
            place.requestFocus();
        }else if (country.getText().toString().isEmpty()){
            country.setError("please enter country");
            country.requestFocus();
        }
        return false;
    }

    private class InsertUsersTask extends AsyncTask<List<User>,Void,List<User>> {

        private WeakReference<MainActivity> mWeakReference;
        private User mUser;

        @Override
        protected List<User> doInBackground(List<User>... lists) {
            List<User> user = lists[0];

            UserDao userDao = UserDatabase.getInstance(getApplication()).mUserDao();
            ArrayList<User> userArrayList = new ArrayList<>(user);
            List<Long> result = userDao.insertAll(userArrayList.toArray(new User[0]));

            int i = 0;

            while (i < user.size()){

                user.get(i).id = result.get(i).intValue();
                ++i;
            }
            return user;
            /*mWeakReference.get().mUserDatabase.mUserDao().insertAll(mUser);
            return mUser;*/
        }

        @Override
        protected void onPostExecute(List<User> users) {
            setListData(users);
        }
    }

    private void setListData(List<User> users) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter();
    }
}
