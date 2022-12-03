package com.example.gorup16project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_con {

    private Context mContext;
    private jobAdapter mJobsAdapter;
    public void setConfig(RecyclerView recyclerview, Context context, List<jobs> jobs, List<String> keys) {
        mContext = context;
        mJobsAdapter = new jobAdapter(jobs, keys);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(mJobsAdapter);
    }

    class JobItemView extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mpay;
        private TextView mduration;
        private TextView mlocation;
        //private TextView murgent;

        private String key;

        public JobItemView (ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.job_list_item, parent, false));

            mTitle = (TextView) itemView.findViewById(R.id.title2);
            mpay = (TextView) itemView.findViewById(R.id.pay2);
            mlocation = (TextView) itemView.findViewById(R.id.location2);
            mduration = (TextView) itemView.findViewById(R.id.duration2);

        }

        public void bind(jobs job, String key) {
            mTitle.setText(job.getTitle());
            mpay.setText(job.getPay());
            mlocation.setText(job.getLocation());
            mduration.setText(job.getDuration());
            this.key = key;
        }
    }

    class jobAdapter extends RecyclerView.Adapter<JobItemView> {
        private List<jobs> mJobList;
        private List<String> mKeys;

        public jobAdapter(List<jobs> mJobList, List<String> mKeys) {
            this.mJobList = mJobList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public JobItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new JobItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull JobItemView holder, int position) {
            holder.bind(mJobList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mJobList.size();
        }
    }

}
