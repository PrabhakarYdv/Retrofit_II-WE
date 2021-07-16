package com.prabhakar.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText postId;
    private Button fetchBtn;
    private RecyclerView recyclerView;
    private List<ResponseModel> responseModels;
    private PostAdapter  postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void setRecyclerView() {
        postAdapter = new PostAdapter(responseModels);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(postAdapter);
    }


    private void buildList() {

    }

    private void callAPI() {
        ApiService apiService = Network.getInstance().create(ApiService.class);
        int postId = Integer.parseInt(this.postId.getText().toString());
        apiService.getPost(postId).enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {
                if (response.body() != null) {
                    responseModels = response.body();
                    setRecyclerView();
                }
            }
            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {

            }
        });
    }

    private void initViews() {
        postId = findViewById(R.id.etPostId);
        fetchBtn = findViewById(R.id.btnFetch);
        recyclerView = findViewById(R.id.recyclerView);
        fetchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAPI();
            }
        });
    }
}
