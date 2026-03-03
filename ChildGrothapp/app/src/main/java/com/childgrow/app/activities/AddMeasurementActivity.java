package com.childgrow.app.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.childgrow.app.R;
import com.childgrow.app.model.Child;
import com.childgrow.app.model.ChildViewModel;
import com.childgrow.app.model.ChildViewModelFactory;
import com.childgrow.app.model.Measurement;
import com.childgrow.app.model.MeasurementViewModel;
import com.childgrow.app.model.MeasurementViewModelFactory;
import com.childgrow.app.utils.AddDatePickerDialog;

import java.text.DateFormat;
import java.util.Calendar;

public class AddMeasurementActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private TextView measurementDate;
    private MeasurementViewModel measurementViewModel;
    private EditText headEditText,weightEditText,heightEditText;
    private String measurementDateString;
    private Button saveButton;
    private String childName;
    private int childId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_measurement_activity);

        childId = getIntent().getIntExtra("CHILD_ID", -1);
        childName = getIntent().getStringExtra("CHILD_NAME");


        measurementDate = findViewById(R.id.measurement_date_text);
        saveButton = findViewById(R.id.save_measurement_button);
        headEditText = findViewById(R.id.measurement_head);
        weightEditText = findViewById(R.id.measurement_weight);
        heightEditText = findViewById(R.id.measurement_height);
        addDatePickerDialog();
        saveButton();
    }

    private void saveButton() {
        saveButton.setOnClickListener(v -> {

            double head = Double.parseDouble(headEditText.getText().toString());
            double weight = Double.parseDouble(weightEditText.getText().toString() );
            double height = Double.parseDouble(heightEditText.getText().toString());
            Measurement measurement = new Measurement(childName,childId,measurementDateString,weight,height,head);
            addMeasurement(measurement);
        });
    }

    private void addMeasurement (Measurement measurement){
        new Thread(() -> {
            MeasurementViewModelFactory measurementViewModelFactory = new MeasurementViewModelFactory(getApplication());
            measurementViewModel = new ViewModelProvider(AddMeasurementActivity.this, measurementViewModelFactory).get(MeasurementViewModel.class);
            measurementViewModel.addMeasurement(measurement);
            finish();
        }).start();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());

        measurementDate.setText(selectedDate);
        measurementDateString = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(mCalendar.getTime());
    }

    private void addDatePickerDialog(){
        measurementDate.setOnClickListener(v -> {
            AddDatePickerDialog mDatePickerDialogFragment = new AddDatePickerDialog();
            mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE_PICK");
        });

    }
}
