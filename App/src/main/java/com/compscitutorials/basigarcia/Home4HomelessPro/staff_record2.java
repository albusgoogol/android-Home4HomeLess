package com.compscitutorials.basigarcia.Home4HomelessPro;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.compscitutorials.basigarcia.Home4HomelessPro.manager.HttpManager;
import com.compscitutorials.basigarcia.Home4HomelessPro.model.RangeCollection;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class staff_record2 extends AppCompatActivity {

    Call<RangeCollection> call;
    Spinner dropdown1;
    Spinner dropdown2;
    ArrayAdapter<String> dataAdapter;
    ProgressDialog dialog;

    ScrollView rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_record2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog = new ProgressDialog(this);
        dialog.setMessage("กำลังโหลดข้อมูล");
        dialog.setCancelable(false);
        dialog.show();
        loadRangeDate();

        rootLayout = (ScrollView) findViewById(R.id.rootLayout);
        dropdown1 = (Spinner) findViewById(R.id.spinner1);
        dropdown2 = (Spinner) findViewById(R.id.spinner2);

        rootLayout.setOnClickListener((view) -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(rootLayout.getWindowToken(), 0);
        });
    }

    private void loadRangeDate() {
        call = HttpManager.getInstance().getService().getRange();
        call.enqueue(new Callback<RangeCollection>() {
            @Override
            public void onResponse(Call<RangeCollection> call, Response<RangeCollection> response) {
                if (response.isSuccessful()) {
                    List<String> listRange = new ArrayList<>();
                    listRange.add(0, "เลือกช่วงอายุ");
                    for (int i = 0; i < response.body().getItemRange().size(); i++)
                        listRange.add(response.body().getItemRange().get(i).getName());
                    dataAdapter = new ArrayAdapter<>(staff_record2.this,
                            android.R.layout.simple_spinner_item, listRange);
                    dropdown1.setAdapter(dataAdapter);

                    List<String> listJob = new ArrayList<>();
                    listJob.add(0, "เลือกอาชีพเดิม");
                    for (int i = 0; i < response.body().getItemJob().size(); i++)
                        listJob.add(response.body().getItemJob().get(i).getJob());
                    dataAdapter = new ArrayAdapter<>(staff_record2.this,
                            android.R.layout.simple_spinner_item, listJob);
                    dropdown2.setAdapter(dataAdapter);
                    dialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<RangeCollection> call, Throwable t) {
                dialog.cancel();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        dialog.cancel();
    }
}
