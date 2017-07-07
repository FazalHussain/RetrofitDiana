package com.cds.dianapractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.server.converter.StringToIntConverter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.cds.dianapractice.Constant.BASE_URL;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        lv = (ListView) findViewById(R.id.list_todos);
        PopulateList();
    }

    private void PopulateList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient service = retrofit.create(ApiClient.class);

        Call<TodosModel> apiResponse = service.getList();

        final List<String> list_city = new ArrayList<>();
                apiResponse.enqueue(new Callback<TodosModel>() {
            @Override
            public void onResponse(Call<TodosModel> call, Response<TodosModel> response) {
                if(response.isSuccessful()){
                    TodosModel todomodel = response.body();
                    for(int i=0;i<todomodel.getTodos().size();i++)
                    list_city.add(todomodel.getTodos().get(i).getCity());
                    Log.d("status","Success");
                    //lv.setAdapter();
                }
                if(list_city.size()>0)
                lv.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list_city));
            }

            @Override
            public void onFailure(Call<TodosModel> call, Throwable t) {
                Log.d("status","Failed");
            }
        });




    }

    public void SubmitClick(View view){
        Intent i = new Intent(this,FilterActivity.class);
        startActivity(i);
    }
}
