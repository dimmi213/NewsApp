package com.example.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScienceFragment extends Fragment {


    public ScienceFragment(){

    }


    String API_KEY = "0b98352a63284198a97929771ce7d67b";
    RecyclerView recyclerView;
    Adapter adapter;
    String country = "us";
    String category = "science";
    ArrayList<Model> modelArrayList;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_science, null);

        recyclerView = v.findViewById(R.id.sciencce_recycleview);
        modelArrayList = new ArrayList<>();
        adapter = new Adapter(getContext(),modelArrayList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        getNews();


        return v;
    }

    void getNews(){

        Log.v("INSIDE","inside get news");


        ApiUtilities.getApiInterface().getCategory(country,category,100,API_KEY).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {

                int status = response.code();
                Log.v("INSIDE3",String.valueOf(status));

                if(response.isSuccessful()){

                    modelArrayList.addAll(response.body().getArticles());
                    Log.v("INSIDE2",response.body().toString());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });
    }


}