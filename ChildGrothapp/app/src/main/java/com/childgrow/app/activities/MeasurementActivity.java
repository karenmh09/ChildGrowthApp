package com.childgrow.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.childgrow.app.ChildList.ChildListAdapter;
import com.childgrow.app.MeasurementsList.MeasurementsAdapter;
import com.childgrow.app.R;
import com.childgrow.app.model.Child;
import com.childgrow.app.model.Measurement;
import com.childgrow.app.model.MeasurementViewModel;
import com.childgrow.app.model.MeasurementViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MeasurementActivity extends AppCompatActivity {
    private RecyclerView measurementRecyclerView;
    private TextView noMeasurementTextView;
    private List<Measurement> measurements = new ArrayList<>();
    private MeasurementsAdapter adapter;
    private int childId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.measurement_activity);
        Intent intent = getIntent();
        childId = intent.getIntExtra("MEASUREMENTS", -1);
        measurementRecyclerView = findViewById(R.id.measurementsListRecyclerView);
        noMeasurementTextView = findViewById(R.id.noMeasurementsTextView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getMeasurements();
        loadAdapter();
    }

    private void getMeasurements() {
        new Thread(() -> {
            MeasurementViewModelFactory measurementViewModelFactory = new MeasurementViewModelFactory(getApplication());
            MeasurementViewModel measurementViewModel = new ViewModelProvider(MeasurementActivity.this, measurementViewModelFactory).get(MeasurementViewModel.class);
            measurements = measurementViewModel.getMeasurementsByChild(childId);

            // Update the UI with the data that came from DB
            runOnUiThread(() -> MeasurementActivity.this.loadAdapter());
        }).start();
    }

    private void loadAdapter(){
        measurementRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        noMeasurementTextView = findViewById(R.id.noChildTextView);

        // Set Adapter
        if (!measurements.isEmpty()) {
            noMeasurementTextView.setVisibility(RecyclerView.GONE);
            measurementRecyclerView.setVisibility(RecyclerView.VISIBLE);
            adapter = new MeasurementsAdapter(measurements);
            measurementRecyclerView.setAdapter(adapter);
        } else {
            noMeasurementTextView.setVisibility(RecyclerView.VISIBLE);
        }

    }
}
