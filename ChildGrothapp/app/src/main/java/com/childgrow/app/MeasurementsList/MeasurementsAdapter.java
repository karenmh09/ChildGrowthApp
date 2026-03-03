package com.childgrow.app.MeasurementsList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.childgrow.app.ChildList.RecyclerViewClickListener;
import com.childgrow.app.R;
import com.childgrow.app.model.Measurement;

import java.util.List;

public class MeasurementsAdapter extends RecyclerView.Adapter<MeasurementsAdapter.MeasurementItemViewHolder> {
        private List<Measurement> measurements;
        private static RecyclerViewClickListener itemListener;
        // Constructor
        public MeasurementsAdapter(List<Measurement> measurements/*, RecyclerViewClickListener itemListener*/) {
            this.measurements = measurements;
            //this.itemListener = itemListener;
        }

        @NonNull
        @Override
        public MeasurementItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.measurement_list_item, parent, false);
            return new MeasurementItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MeasurementItemViewHolder holder, int position) {
            Measurement measurement = measurements.get(position);

            holder.weight.setText(measurement.getWeight() + " grams");
            holder.head.setText(measurement.getHead()+ " cm");
            holder.height.setText(measurement.getHeight()+ " cm");
            holder.date.setText(measurement.getMeasurementDate());
            //holder.childIcon.setImageResource(child.getIcon());

        }

        @Override
        public int getItemCount() {
            return measurements.size();
        }

        // ViewHolder class
        static class MeasurementItemViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {
            TextView weight, head, height, date;

            public MeasurementItemViewHolder(@NonNull View itemView) {

                super(itemView);
                //itemView.setOnClickListener(this);
                weight = itemView.findViewById(R.id.weightValue);
                head = itemView.findViewById(R.id.headValue);
                height = itemView.findViewById(R.id.heightValue);
                date = itemView.findViewById(R.id.measurement_date);
            }

            /*@Override
            public void onClick(View v) {
                itemListener.recyclerViewListClicked(v, this.getPosition());

            }*/
        }
    }
