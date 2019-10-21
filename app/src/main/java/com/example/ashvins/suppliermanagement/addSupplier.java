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

public class addSupplier extends Fragment {
    EditText editAddress, editlandlineNo, editmobileNo, editEmail;
    Button add;
    Spinner iName, cName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_supplier, container, false);

        iName = v.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.ItemName, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        iName.setAdapter(adapter);

        cName = v.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.CompanyName, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cName.setAdapter(adapter2);

        editAddress = (EditText) v.findViewById(R.id.editcAddr);
        editlandlineNo = (EditText) v.findViewById(R.id.editLNO);
        editmobileNo = (EditText) v.findViewById(R.id.editMNO);
        editEmail = (EditText) v.findViewById(R.id.editsAddr);
        add = (Button) v.findViewById(R.id.addBtn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supplierDB supplier = new supplierDB();
                String in = iName.getSelectedItem().toString();
                String cn = cName.getSelectedItem().toString();
                String addr = editAddress.getText().toString();
                Long lno = Long.parseLong(editlandlineNo.getText().toString());
                Long mno = Long.parseLong(editmobileNo.getText().toString());
                String email = editEmail.getText().toString();

                supplier.setItemName(iName.getSelectedItem().toString());
                supplier.setcName(cName.getSelectedItem().toString());
                supplier.setcAddr(editAddress.getText().toString());
                supplier.setLandline(lno);
                supplier.setMobile(mno);
                supplier.setEmail(editEmail.getText().toString());

                if(!validate()) {
                    Toast.makeText(getActivity(), "Registration unsuccessfull", Toast.LENGTH_SHORT).show();
                }
                else {
                    new FirebaseDBHelper().addSupplier(supplier, new FirebaseDBHelper.dStatus() {
                        @Override
                        public void DataLoaded(List<supplierDB> suppliers, List<String> keys) {

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
        String addr = editAddress.getText().toString().trim();
        Long lno = Long.parseLong(editlandlineNo.getText().toString());
        Long mno = Long.parseLong(editmobileNo.getText().toString());
        String email = editEmail.getText().toString().trim();

        if(addr.isEmpty()) {
            editAddress.setError("Field is empty");
            return false;
        }
        if(lno.equals("")) {
            editlandlineNo.setError("Field is empty");
            return false;
        }
        if(mno.equals("")) {
            editmobileNo.setError("Field is empty");
            return false;
        }
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Please enter valid Email Address");
            return false;
        }
        return true;
    }

    public void clear() {
        String addr = editAddress.getText().toString().trim();
        Long lno = Long.parseLong(editlandlineNo.getText().toString());
        Long mno = Long.parseLong(editmobileNo.getText().toString());
        String email = editEmail.getText().toString().trim();

        editAddress.setText("");
        editlandlineNo.setText("");
        editmobileNo.setText("");
        editEmail.setText("");
    }
}
