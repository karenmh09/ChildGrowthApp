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
import com.childgrow.app.utils.AddDatePickerDialog;

import java.text.DateFormat;
import java.util.Calendar;

public class AddChildActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText nameEditText;
    TextView dobText, dueText;

    String due, dob;
    private Button saveButton;
    private boolean isDOBdateSelected;

    private ChildViewModel childViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_new_child_activity);
        nameEditText = findViewById(R.id.child_name_edittext);
        dobText = findViewById(R.id.child_dob_text);
        dueText = findViewById(R.id.child_due_text);
        saveButton = findViewById(R.id.save_button);
        addDatePickerDialog();
        saveButton();
    }

    private void addChild(Child child) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ChildViewModelFactory childviewModelFactory = new ChildViewModelFactory(getApplication());
                childViewModel = new ViewModelProvider(AddChildActivity.this, childviewModelFactory).get(ChildViewModel.class);
                childViewModel.addChild(child);
                finish();
            }
        }).start();
    }

    private void saveButton() {
        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            Child child = new Child(name,dob,due);
            addChild(child);
        });
    }

    private void addDatePickerDialog(){
        dobText.setOnClickListener(v -> {
            isDOBdateSelected = true;
            AddDatePickerDialog mDatePickerDialogFragment = new AddDatePickerDialog();
            mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE_PICK");
        });

        dueText.setOnClickListener(v -> {
            isDOBdateSelected = false;
            AddDatePickerDialog mDatePickerDialogFragment = new AddDatePickerDialog();
            mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE_PICK");
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());

        if (isDOBdateSelected) {
            dobText.setText(selectedDate);
            dob = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(mCalendar.getTime());
        } else {
            dueText.setText(selectedDate);
            due = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(mCalendar.getTime());
        }
    }

}



