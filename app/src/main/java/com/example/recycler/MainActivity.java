package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyAdapter.rowItemClickListener{

    private RecyclerView pokeList;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] pokemon = this.getResources().getStringArray(R.array.pokemon);

        pokeList = this.findViewById(R.id.pokeList);
        pokeList.setHasFixedSize(true); // performance boost
        myAdapter = new MyAdapter(pokemon, this, this);
        pokeList.setAdapter(myAdapter);
        layoutManager = new LinearLayoutManager(this);
        pokeList.setLayoutManager(layoutManager);
    }

    @Override
    public void rowItemWasClicked(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}