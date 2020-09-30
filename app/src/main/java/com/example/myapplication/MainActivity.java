package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName()+"My";
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread t1 = new otherThread();
        RecyclerView recyclerView;
        MyAdapter myAdapter;
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        t1.start();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView startT,endT,PMTname,PMTunit;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                startT = itemView.findViewById(R.id.textView_startT);
                endT = itemView.findViewById(R.id.textView_endT);
                PMTname = itemView.findViewById(R.id.textView_PMTname);
                PMTunit = itemView.findViewById(R.id.textView_PMTunit);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
            holder.startT.setText(arrayList.get(position).get("startTime"));
            holder.endT.setText(arrayList.get(position).get("endTime"));
            holder.PMTname.setText(arrayList.get(position).get("parameterName"));
            holder.PMTunit.setText(arrayList.get(position).get("parameterUnit"));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }


}
