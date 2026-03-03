package com.childgrow.app.ChildList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.childgrow.app.R;
import com.childgrow.app.model.Child;

import java.util.List;


public class ChildListAdapter extends RecyclerView.Adapter<ChildListAdapter.ChildListItemViewHolder> {
    private List<Child> childrenList;
    private static RecyclerViewClickListener itemListener;
// Constructor
    public ChildListAdapter(List<Child> childrenList, RecyclerViewClickListener itemListener) {
        this.childrenList = childrenList;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ChildListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_list_item, parent, false);
        return new ChildListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildListItemViewHolder holder, int position) {
        Child child = childrenList.get(position);

        holder.childName.setText(child.getName());
        holder.dueDate.setText(child.getDueDate().toString());
        holder.dob.setText(child.getDateOfBirth().toString());
        //holder.childIcon.setImageResource(child.getIcon());

}

@Override
public int getItemCount() {
    return childrenList.size();
}

    // ViewHolder class
    static class ChildListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView childName, dueDate, dob;
        ImageView childIcon;

        public ChildListItemViewHolder(@NonNull View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            childName = itemView.findViewById(R.id.child_name);
            dueDate = itemView.findViewById(R.id.child_due_date);
            dob = itemView.findViewById(R.id.child_dob);
            childIcon = itemView.findViewById(R.id.child_icon);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getPosition());

        }
    }
}
