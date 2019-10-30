package com.example.ashvins.suppliermanagement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Config2 {
    private Context scheduleContext;
    private RecyclerView_Config2.ScheduleAdapter scheduleAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<scheduleDB> schedule, List<String> keys) {
        scheduleContext = context;
        scheduleAdapter = new RecyclerView_Config2.ScheduleAdapter(schedule, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(scheduleAdapter);
    }

    public ScheduleAdapter setConfig(Context context, List<scheduleDB> schedule, List<String> keys) {
        scheduleContext = context;
        scheduleAdapter = new ScheduleAdapter(schedule, keys);
        return scheduleAdapter;
    }

    class ScheduleView extends RecyclerView.ViewHolder {
        private TextView cn, in, ca, qty, sType, date, time;
        private String key;

        public ScheduleView(ViewGroup parent) {
            super(LayoutInflater.from(scheduleContext).inflate(R.layout.schedule_list_2, parent, false));

            cn = (TextView)itemView.findViewById(R.id.pCat_View);
            in = (TextView)itemView.findViewById(R.id.cName_View);
            ca = (TextView)itemView.findViewById(R.id.cAddr_View);
            qty = (TextView)itemView.findViewById(R.id.lNo_View);
            sType = (TextView)itemView.findViewById(R.id.sType_View);
            date = (TextView)itemView.findViewById(R.id.mNo_View);
            time = (TextView)itemView.findViewById(R.id.eMail_View);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(scheduleContext, viewSchedule.class);
                    intent.putExtra("key", key);
                    intent.putExtra("cName", cn.getText().toString());
                    intent.putExtra("iName", in.getText().toString());
                    intent.putExtra("cAddr", ca.getText().toString());
                    intent.putExtra("quantity", qty.getText().toString());
                    intent.putExtra("sType", sType.getText().toString());
                    intent.putExtra("date", date.getText().toString());
                    intent.putExtra("time", time.getText().toString());

                    scheduleContext.startActivity(intent);
                }
            });
        }

        public void bind(scheduleDB schedule, String key) {
            cn.setText(schedule.getcName());
            in.setText(schedule.getiName());
            ca.setText(schedule.getcAddr());
            qty.setText(schedule.getQuantity().toString());
            sType.setText(schedule.getsType());
            date.setText(schedule.getDate());
            time.setText(schedule.getTime());
            this.key = key;
        }
    }

    class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView_Config2.ScheduleView> implements Filterable {
        private List<scheduleDB> scheduleList;
        private List<String> keys;
        private List<scheduleDB> scheduleListFull;

        public ScheduleAdapter(List<scheduleDB> scheduleList, List<String> keys) {
            this.scheduleList = scheduleList;
            this.keys = keys;
            scheduleListFull = new ArrayList<>(scheduleList);
        }

        @Override
        public RecyclerView_Config2.ScheduleView onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerView_Config2.ScheduleView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView_Config2.ScheduleView holder, int position) {
            holder.bind(scheduleList.get(position), keys.get(position));
        }

        @Override
        public int getItemCount() {
            return scheduleList.size();
        }

        @Override
        public Filter getFilter() {
            return scheduleFilter;
        }

        private Filter scheduleFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<scheduleDB> filteredList = new ArrayList<>();

                if(charSequence == null || charSequence.length() == 0) {
                    filteredList.addAll(scheduleListFull);
                } else {
                    String filterPattern = charSequence.toString().toLowerCase().trim();

                    for(scheduleDB schedule : scheduleListFull) {
                        if(schedule.getcName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(schedule);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                scheduleList.clear();
                scheduleList.addAll((List) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
}
