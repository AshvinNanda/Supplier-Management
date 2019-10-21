package com.example.ashvins.suppliermanagement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class scheduleList extends Fragment {
    private RecyclerView recyclerView;
    RecyclerView_Config2.ScheduleAdapter scheduleAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.schedule_list, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerview_schedule);
        recyclerView.setAdapter(scheduleAdapter);
        setHasOptionsMenu(true);
        new FirebaseDBHelper().readSchedule(new FirebaseDBHelper.dataStatus() {
            @Override
            public void DataLoaded(List<scheduleDB> schedule, List<String> keys) {
                new RecyclerView_Config2().setConfig(recyclerView, getActivity(), schedule, keys);
                scheduleAdapter = new RecyclerView_Config2().setConfig(getActivity(), schedule, keys);
                recyclerView.setAdapter(scheduleAdapter);
            }

            @Override
            public void DataAdded() {

            }

            @Override
            public void DataUpdated() {

            }

            @Override
            public void DataDeleted() {

            }
        });

        new FirebaseDBHelper().automatedSchedule(new FirebaseDBHelper.dataStatus() {
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

            }
        });
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                scheduleAdapter.getFilter().filter(s);
                return false;
            }
        });
    }
}
