package com.compscitutorials.basigarcia.Home4HomelessPro.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.compscitutorials.basigarcia.Home4HomelessPro.model.InformCollection;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.R;

import java.util.List;

public class InformAdapter extends RecyclerView.Adapter<InformAdapter.InformViewHolder> {

    private List<InformCollection.Inform> listInform;

    public void setListInform(List<InformCollection.Inform> listInform) {
        this.listInform = listInform;
        notifyDataSetChanged();
    }

    @Override
    public InformViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notified_staff, parent, false);
        return new InformViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InformViewHolder holder, int position) {
        InformCollection.Inform inform = listInform.get(position);
        holder.tvDate.setText(inform.getDate());
        holder.tvLocation.setText(inform.getLocation());
    }

    @Override
    public int getItemCount() {
        return listInform == null ? 0 : listInform.size();
    }

    class InformViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDate;
        private TextView tvLocation;

        public InformViewHolder(View itemView) {
            super(itemView);

            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
        }
    }
}
