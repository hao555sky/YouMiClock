package com.example.youmiclock.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youmiclock.R;

import java.util.List;

/**
 * Created by hao on 17-2-25.
 */
public class TimeRepeatAndRingAdapter extends RecyclerView.Adapter<TimeRepeatAndRingAdapter.ViewHolder> {

    private List<String> stringList;

    public TimeRepeatAndRingAdapter(List<String> list){
        stringList = list;
    }

    @Override
    public TimeRepeatAndRingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_repeat_and_ring,
                parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.TimeRepeatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String name = stringList.get(position);
                Toast.makeText(v.getContext(), name, Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(TimeRepeatAndRingAdapter.ViewHolder holder, int position) {
        String name = stringList.get(position);
        holder.timeRepeatName.setText(name);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        View TimeRepeatView;

        private TextView timeRepeatName;

        public ViewHolder(View view){
            super(view);
            TimeRepeatView = view;
            timeRepeatName = (TextView)view.findViewById(R.id.time_repeat_name);
        }
    }
}
