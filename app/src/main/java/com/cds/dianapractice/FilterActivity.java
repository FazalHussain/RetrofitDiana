package com.cds.dianapractice;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.cds.dianapractice.Constant.BASE_URL;

public class FilterActivity extends AppCompatActivity {

    private EditText et_filter;
    private TextView filter_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        bindUI();
    }

    private void bindUI() {
        et_filter = (EditText) findViewById(R.id.filter_et);
        filter_result = (TextView) findViewById(R.id.filter_result);
    }

    public void FilterClick(View view){
        if(et_filter.getText().length()>0){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiClient service = retrofit.create(ApiClient.class);

            Call<TodosModel> apiResponse = service.getFilterList(et_filter.getText().toString());

            final List<String> list_city = new ArrayList<>();
            apiResponse.enqueue(new Callback<TodosModel>() {
                @Override
                public void onResponse(Call<TodosModel> call, Response<TodosModel> response) {
                    if(response.isSuccessful()){
                        TodosModel todomodel = response.body();
                        for(int i=0;i<todomodel.getTodos().size();i++)
                            list_city.add("Id: "+todomodel.getTodos().get(i).getId()+
                                    "\n\nCity: "+todomodel.getTodos().get(i).getCity()+
                                    "\n\nState: "+todomodel.getTodos().get(i).getState()+
                                    "\n\nZip Code: "+todomodel.getTodos().get(i).getZip_code());
                        Log.d("status","Success");
                        //lv.setAdapter();
                    }

                    filter_result.setText(list_city.get(0));

                }

                @Override
                public void onFailure(Call<TodosModel> call, Throwable t) {
                    Log.d("status","Failed");
                }
            });

        }else {
            new AlertDialog.Builder(this).setTitle("Alert!")
                    .setMessage("Enter any state").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).setCancelable(false).show();
        }
    }
}
