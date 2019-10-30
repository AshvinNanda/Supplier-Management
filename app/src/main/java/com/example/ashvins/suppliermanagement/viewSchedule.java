package com.example.ashvins.suppliermanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class viewSchedule extends AppCompatActivity {
    TextView cn, in, ca, qty;
    EditText sType, date, time;
    Button update, delete;

    private String key;
    private String cName;
    private String iName;
    private String cAddr;
    private Integer Quantity;
    private String scheduleType;
    private String Date;
    private String Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        key = getIntent().getStringExtra("key");
        cName = getIntent().getStringExtra("cName");
        iName = getIntent().getStringExtra("iName");
        cAddr = getIntent().getStringExtra("cAddr");
        String Qty = getIntent().getStringExtra("quantity");
        Quantity = Integer.parseInt(Qty);
        scheduleType = getIntent().getStringExtra("sType");
        Date = getIntent().getStringExtra("date");
        Time = getIntent().getStringExtra("time");

        cn = (TextView)findViewById(R.id.editCN2);
        cn.setText(cName);
        in = (TextView)findViewById(R.id.editIN2);
        in.setText(iName);
        ca = (TextView)findViewById(R.id.editAddr2);
        ca.setText(cAddr);
        qty = (TextView)findViewById(R.id.editQty2);
        qty.setText(Quantity.toString());
        sType = (EditText)findViewById(R.id.editsType2);
        sType.setText(scheduleType);
        date = (EditText)findViewById(R.id.editDate2);
        date.setText(Date);
        time = (EditText)findViewById(R.id.editTime2);
        time.setText(Time);
        update = (Button)findViewById(R.id.updateBtn);
        delete = (Button)findViewById(R.id.deleteBtn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleDB schedule = new scheduleDB();
                schedule.setcName(cn.getText().toString());
                schedule.setiName(in.getText().toString());
                schedule.setcAddr(ca.getText().toString());
                schedule.setQuantity(Integer.parseInt(qty.getText().toString()));
                schedule.setsType(sType.getText().toString());
                schedule.setDate(date.getText().toString());
                schedule.setTime(time.getText().toString());

                new FirebaseDBHelper().updateSchedule(key, schedule, new FirebaseDBHelper.dataStatus() {
                    @Override
                    public void DataLoaded(List<scheduleDB> schedules, List<String> keys) {

                    }

                    @Override
                    public void DataAdded() {

                    }

                    @Override
                    public void DataUpdated() {
                        Toast.makeText(viewSchedule.this, "Data updated successfully", Toast.LENGTH_LONG).show();
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
                scheduleDB schedule = new scheduleDB();

                new FirebaseDBHelper().deleteSchedule(key, new FirebaseDBHelper.dataStatus() {
                    @Override
                    public void DataLoaded(List<scheduleDB> schedules, List<String> keys) {

                    }

                    @Override
                    public void DataAdded() {

                    }

                    @Override
                    public void DataUpdated() {

                    }

                    @Override
                    public void DataDeleted() {
                        Toast.makeText(viewSchedule.this, "Data deleted successfully", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
