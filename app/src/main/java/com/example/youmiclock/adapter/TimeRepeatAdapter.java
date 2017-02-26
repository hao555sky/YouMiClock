package com.example.youmiclock.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.youmiclock.R;

import java.util.List;

/**
 * Created by hao on 17-2-25.
 */
public class TimeRepeatAdapter extends RecyclerView.Adapter<TimeRepeatAdapter.ViewHolder> {

    private List<String> stringList;

    public TimeRepeatAdapter(List<String> list){
        stringList = list;
    }

    @Override
    public TimeRepeatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_repeat,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimeRepeatAdapter.ViewHolder holder, int position) {
        String name = stringList.get(position);
        holder.timeRepeatName.setText(name);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView timeRepeatName;

        public ViewHolder(View view){
            super(view);
            timeRepeatName = (TextView)view.findViewById(R.id.time_repeat_name);
        }
    }
}
