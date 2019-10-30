package com.example.ashvins.suppliermanagement;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDBHelper {
    private FirebaseDatabase firebaseDB;
    private DatabaseReference ref, ref2;
    private List<supplierDB> suppliers = new ArrayList<>();
    private List<scheduleDB> schedules = new ArrayList<>();

    public interface dStatus {
        void DataLoaded(List<supplierDB> suppliers, List<String> keys);
        void DataAdded();
        void DataUpdated();
        void DataDeleted();
    }

    public interface dataStatus {
        void DataLoaded(List<scheduleDB> schedules, List<String> keys);
        void DataAdded();
        void DataUpdated();
        void DataDeleted();
    }

    public FirebaseDBHelper() {
        firebaseDB = FirebaseDatabase.getInstance();
        ref = firebaseDB.getReference("Supplier");
        ref2 = firebaseDB.getReference("Supplier Schedule");
    }

    public void readSupplier(final dStatus ds) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                suppliers.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    supplierDB supplier = keyNode.getValue(supplierDB.class);
                    suppliers.add(supplier);
                }
                ds.DataLoaded(suppliers, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void readSchedule(final dataStatus ds) {
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                schedules.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    scheduleDB schedule = keyNode.getValue(scheduleDB.class);
                    schedules.add(schedule);
                }
                ds.DataLoaded(schedules, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addSupplier(supplierDB supplier, final dStatus ds) {
        String key = ref.push().getKey();

        ref.child(key).setValue(supplier)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        ds.DataAdded();
                    }
                });
    }

    public void addSchedule(scheduleDB schedules, final dataStatus ds) {
        String key = ref2.push().getKey();

        ref2.child(key).setValue(schedules)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        ds.DataAdded();
                    }
                });
    }

    public void updateSupplier(String key, supplierDB supplier, final dStatus ds) {
        ref.child(key).setValue(supplier)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        ds.DataUpdated();
                    }
                });
    }

    public void updateSchedule(String key, scheduleDB schedule, final dataStatus ds) {
        ref2.child(key).setValue(schedule)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        ds.DataUpdated();
                    }
                });
    }

    public void deleteSupplier(String key, final dStatus ds) {
        ref.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        ds.DataDeleted();
                    }
                });
    }

    public void deleteSchedule(String key, final dataStatus ds) {
        ref2.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        ds.DataDeleted();
                    }
                });
    }

    public void automatedSchedule(final dataStatus ds) {
        DatabaseReference ref3= firebaseDB.getReference("Order/");
        final DatabaseReference ref4 = firebaseDB.getReference("Supplier/");

        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                schedules.clear();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    final orderDB order = keyNode.getValue(orderDB.class);
                    if(order.getStatus().equals("Approved")) {
                        ref4.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot keyNode2 : dataSnapshot.getChildren()) {
                                    supplierDB supplier = keyNode2.getValue(supplierDB.class);
                                    if(order.getItem_name().equals(supplier.getItemName())) {
                                        scheduleDB schedule = new scheduleDB(supplier.getcName(), order.getItem_name(), supplier.getcAddr(), order.getQuantity(), "sType", "date", "time");
                                        addSchedule(schedule, ds);
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
