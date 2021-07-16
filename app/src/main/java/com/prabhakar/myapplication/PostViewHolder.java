package com.prabhakar.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView email;
    private TextView body;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        name = itemView.findViewById(R.id.tvName);
        email = itemView.findViewById(R.id.tvEmail);
        body = itemView.findViewById(R.id.tvBody);
    }

    public void setData(ResponseModel responseModel ) {
        name.setText(responseModel.getName());
        email.setText(responseModel.getEmail());
        body.setText(responseModel.getBody());

    }
}
