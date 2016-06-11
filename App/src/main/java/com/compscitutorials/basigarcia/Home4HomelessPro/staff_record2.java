package com.compscitutorials.basigarcia.Home4HomelessPro;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.compscitutorials.basigarcia.Home4HomelessPro.manager.HttpManager;
import com.compscitutorials.basigarcia.Home4HomelessPro.model.RangeCollection;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.R;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class staff_record2 extends AppCompatActivity {

    Call<RangeCollection> call;
    Spinner dropdown;
    ArrayAdapter<String> dataAdapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_record2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog = new ProgressDialog(this);
        dialog.setMessage("กำลังโหลดข้อมูล");
        dialog.show();
        loadRangeDate();

        dropdown = (Spinner) findViewById(R.id.spinner1);

    }

    private void loadRangeDate() {
        call = HttpManager.getInstance().getService().getRange();
        call.enqueue(new Callback<RangeCollection>() {
            @Override
            public void onResponse(Call<RangeCollection> call, Response<RangeCollection> response) {
                if (response.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < response.body().getItem().size(); i++)
                        list.add(response.body().getItem().get(i).getName());
                    dataAdapter = new ArrayAdapter<String>(staff_record2.this,
                            android.R.layout.simple_spinner_item, list);
                    dropdown.setAdapter(dataAdapter);
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
