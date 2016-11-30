package project.hci.hciproject;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import project.hci.hciproject.realm.Bar;

/**
 * Created by jake on 11/29/16.
 */

public class BarResultsAdapter extends RecyclerView.Adapter<BarResultsAdapter.ViewHolder> {

    static int selectedPos;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

    // Store a member variable for the contacts
    private List<Bar> mItems;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor

    public BarResultsAdapter(Context context, List<Bar> items) {
        mItems = items;
        mContext = context;
    };

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public BarResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        // Return a new holder instance
        BarResultsAdapter.ViewHolder viewHolder = new BarResultsAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(BarResultsAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.itemView.setSelected(selectedPos == position);

        Bar contact = mItems.get(position);

        viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notifyItemChanged(selectedPos);
                selectedPos = position;
                notifyItemChanged(selectedPos);

            }
        });


        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getBar_name());

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
