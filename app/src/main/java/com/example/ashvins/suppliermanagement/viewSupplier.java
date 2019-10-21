package com.example.ashvins.suppliermanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class viewSupplier extends AppCompatActivity {
    TextView in, cn;
    EditText ca, lno, mno, eMail;
    Button update, delete;

    private String key;
    private String iName;
    private String cName;
    private String cAddr;
    private Long landline;
    private Long mobile;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_supplier);

        key = getIntent().getStringExtra("key");
        iName = getIntent().getStringExtra("itemName");
        cName = getIntent().getStringExtra("cName");
        cAddr = getIntent().getStringExtra("cAddr");
        String landlineNo = getIntent().getStringExtra("landline");
        landline = Long.parseLong(landlineNo);
        String mobileNo = getIntent().getStringExtra("mobile");
        mobile = Long.parseLong(mobileNo);
        email = getIntent().getStringExtra("email");

        in = (TextView)findViewById(R.id.editIN2);
        in.setText(iName);
        cn = (TextView) findViewById(R.id.editCN2);
        cn.setText(cName);
        ca = (EditText)findViewById(R.id.editAddr2);
        ca.setText(cAddr);
        lno = (EditText)findViewById(R.id.editLNO);
        lno.setText(landline.toString());
        mno = (EditText)findViewById(R.id.editMNO);
        mno.setText(mobile.toString());
        eMail = (EditText)findViewById(R.id.editEmail2);
        eMail.setText(email);
        update = (Button)findViewById(R.id.updateBtn);
        delete = (Button)findViewById(R.id.deleteBtn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supplierDB supplier = new supplierDB();
                supplier.setItemName(in.getText().toString());
                supplier.setcName(cn.getText().toString());
                supplier.setcAddr(ca.getText().toString());
                supplier.setLandline(Long.parseLong(lno.getText().toString()));
                supplier.setMobile(Long.parseLong(mno.getText().toString()));
                supplier.setEmail(eMail.getText().toString());

                new FirebaseDBHelper().updateSupplier(key, supplier, new FirebaseDBHelper.dStatus() {
                    @Override
                    public void DataLoaded(List<supplierDB> suppliers, List<String> keys) {

                    }

                    @Override
                    public void DataAdded() {

                    }

                    @Override
                    public void DataUpdated() {
                        Toast.makeText(viewSupplier.this, "Data updated successfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataDeleted() {

                    }
                });

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDBHelper().deleteSupplier(key, new FirebaseDBHelper.dStatus() {
                    @Override
                    public void DataLoaded(List<supplierDB> suppliers, List<String> keys) {

                    }

                    @Override
                    public void DataAdded() {

                    }

                    @Override
                    public void DataUpdated() {

                    }

                    @Override
                    public void DataDeleted() {
                        Toast.makeText(viewSupplier.this, "Data deleted successfully", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
