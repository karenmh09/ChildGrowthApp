package com.childgrow.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.childgrow.app.R;
import com.childgrow.app.ChildList.ChildListAdapter;
import com.childgrow.app.ChildList.RecyclerViewClickListener;
import com.childgrow.app.model.Child;
import com.childgrow.app.model.ChildViewModel;
import com.childgrow.app.model.ChildViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private RecyclerView childListRecyclerView;
    private TextView noChildTextView;
    private FloatingActionButton addButton;

    private ChildViewModel childViewModel;

    private List<Child> children= new ArrayList<>();
    private ChildListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        childListRecyclerView = findViewById(R.id.childListRecyclerView);
        addButton = findViewById(R.id.add_child_button);
        addButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, AddChildActivity.class);
            startActivity(myIntent);
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        getData();
        loadAdapter();
    }

    private void loadAdapter(){
        childListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        noChildTextView = findViewById(R.id.noChildTextView);

        // Set Adapter
        if (!children.isEmpty()) {
            noChildTextView.setVisibility(RecyclerView.GONE);
            childListRecyclerView.setVisibility(RecyclerView.VISIBLE);
            adapter = new ChildListAdapter(children, this);
            childListRecyclerView.setAdapter(adapter);
        } else {
            noChildTextView.setVisibility(RecyclerView.VISIBLE);
        }

    }

    public void getData() {
        /*LocalDate dobAlejandro = LocalDate.of(2021, Month.FEBRUARY, 6);
        LocalDate dueAlejandro = LocalDate.of(2021, Month.FEBRUARY, 14);
        LocalDate dobAndres = LocalDate.of(2023, Month.JUNE, 19);
        LocalDate dueAndres = LocalDate.of(2023, Month.JUNE, 28);

        childrenList.add(new Child("Alejandro", dobAlejandro.toString(), dueAlejandro.toString()));
        childrenList.add(new Child("Andres", dobAndres.toString(), dueAndres.toString()));
        */
        new Thread(() -> {
            ChildViewModelFactory childviewModelFactory = new ChildViewModelFactory(getApplication());
            childViewModel = new ViewModelProvider(MainActivity.this, childviewModelFactory).get(ChildViewModel.class);
            children = childViewModel.getAllChildren();

            // Update the UI with the data that came from DB
            runOnUiThread(() -> MainActivity.this.loadAdapter());
        }).start();
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Intent myIntent = new Intent(MainActivity.this, MeasurementActivity.class);
        myIntent.putExtra("CHILD_ID", children.get(position).getId());
        myIntent.putExtra("CHILD_NAME", children.get(position).getName());
        startActivity(myIntent);
    }
}