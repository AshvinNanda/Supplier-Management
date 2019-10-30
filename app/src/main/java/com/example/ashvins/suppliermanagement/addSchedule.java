package com.example.ashvins.suppliermanagement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class addSchedule extends Fragment {
    EditText editiName, editcAddress, editQuantity, editDate, editTime;
    Spinner cName, sType;
    Button add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_schedule, container, false);

        cName = v.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.CompanyName, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cName.setAdapter(adapter2);

        sType = v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.ScheduleType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sType.setAdapter(adapter);

        editiName = (EditText) v.findViewById(R.id.editiName);
        editcAddress = (EditText) v.findViewById(R.id.editcAddr);
        editQuantity = (EditText)v.findViewById(R.id.editQuantity);
        editDate = (EditText)v.findViewById(R.id.editDate);
        editTime = (EditText)v.findViewById(R.id.editTime);
        add = (Button) v.findViewById(R.id.addBtn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleDB schedule = new scheduleDB();

                String cn = cName.getSelectedItem().toString();
                String in = editiName.getText().toString();
                String ca = editcAddress.getText().toString();
                Integer qty = Integer.parseInt(editQuantity.getText().toString());
                String st = sType.getSelectedItem().toString();
                String date = editDate.getText().toString();
                String time = editTime.getText().toString();

                schedule.setcName(cName.getSelectedItem().toString());
                schedule.setiName(editiName.getText().toString());
                schedule.setcAddr(editcAddress.getText().toString());
                schedule.setQuantity(qty);
                schedule.setsType(sType.getSelectedItem().toString());
                schedule.setDate(editDate.getText().toString());
                schedule.setTime(editTime.getText().toString());

                if(!validate()) {
                    Toast.makeText(getActivity(), "Registration unsuccessfull", Toast.LENGTH_SHORT).show();
                }
                else {
                    new FirebaseDBHelper().addSchedule(schedule, new FirebaseDBHelper.dataStatus() {
                        @Override
                        public void DataLoaded(List<scheduleDB> schedules, List<String> keys) {

                        }

                        @Override
                        public void DataAdded() {
                            Toast.makeText(getActivity(), "Data added successfully", Toast.LENGTH_LONG).show();
                            clear();
                        }

                        @Override
                        public void DataUpdated() {

                        }

                        @Override
                        public void DataDeleted() {

                        }
                    });
                }
            }
        });

        return v;
    }

    public boolean validate() {
        String in = editiName.getText().toString().trim();
        String ca = editcAddress.getText().toString().trim();
        Integer qty = Integer.parseInt(editQuantity.getText().toString().trim());
        String date = editDate.getText().toString().trim();
        String time = editTime.getText().toString().trim();

        if(in.isEmpty()) {
            editiName.setError("Field is empty");
            return false;
        }
        if(ca.isEmpty()) {
            editcAddress.setError("Field is empty");
            return false;
        }
        if(qty.equals("")) {
            editQuantity.setError("Field is empty");
            return false;
        }
        if(date.isEmpty()) {
            editDate.setError("Field is empty");
            return false;
        }
        if(time.isEmpty()) {
            editTime.setError("Field is empty");
            return false;
        }
        return true;
    }

    public void clear() {
        String in = editiName.getText().toString();
        String ca = editcAddress.getText().toString();
        Integer qty = Integer.parseInt(editQuantity.getText().toString());
        String date = editDate.getText().toString();
        String time = editTime.getText().toString();

        editiName.setText("");
        editcAddress.setText("");
        editQuantity.setText("");
        editDate.setText("");
        editTime.setText("");
    }
}


