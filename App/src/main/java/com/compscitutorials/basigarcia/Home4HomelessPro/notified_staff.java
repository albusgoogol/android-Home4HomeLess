package com.compscitutorials.basigarcia.Home4HomelessPro;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.compscitutorials.basigarcia.Home4HomelessPro.adapter.InformAdapter;
import com.compscitutorials.basigarcia.Home4HomelessPro.manager.HttpManager;
import com.compscitutorials.basigarcia.Home4HomelessPro.model.InformCollection;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class notified_staff extends AppCompatActivity {


    InformAdapter adapter;
    RecyclerView recyclerView;
    Call<InformCollection> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notified_staff);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new InformAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);


        call = HttpManager.getInstance().getService().getInform();
        call.enqueue(new Callback<InformCollection>() {
            @Override
            public void onResponse(Call<InformCollection> call, Response<InformCollection> response) {
                if (response.isSuccessful())
                    adapter.setListInform(response.body().getListInform());
            }

            @Override
            public void onFailure(Call<InformCollection> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!call.isCanceled() && call != null)
            call.cancel();
    }
}
