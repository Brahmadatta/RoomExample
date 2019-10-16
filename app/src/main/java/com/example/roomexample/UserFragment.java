package com.example.roomexample;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    AsyncTask<Void,Void, List<User>> userData;
    RecyclerView userDataRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        userDataRecyclerView = view.findViewById(R.id.userDataRecyclerView);

        ((MainActivity)getActivity()).dataAdded(new PassData() {
            @Override
            public boolean isDataAdded(Boolean isAdded) {
                if (isAdded){

                    getTheUserData();
                }
                return false;
            }
        });

        return view;
    }

    private void getTheUserData() {

        userData = new RetrieveData();
        userData.execute();
    }

    public class RetrieveData extends AsyncTask<Void,Void,List<User>>{

        @Override
        protected List<User> doInBackground(Void... voids) {
            List<User> users = UserDatabase.getInstance(getContext()).mUserDao().getAllUsers();
            return users;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            setUserData(users);
        }
    }

    private void setUserData(List<User> users) {
        UserAdapter adapter = new UserAdapter(users,getActivity());
        userDataRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userDataRecyclerView.setHasFixedSize(true);
        userDataRecyclerView.setAdapter(adapter);
    }

}
