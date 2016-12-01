package project.hci.hciproject.views.bar_results.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import project.hci.hciproject.R;
import project.hci.hciproject.realm.Bar;

/**
 * Created by jake on 11/29/16.
 */

public class BarResultsAdapter extends RecyclerView.Adapter<BarResultsAdapter.ViewHolder> {

    public static int selectedPos;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;

        public ViewHolder(View itemView) {

            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

    private List<Bar> mItems;
    private Context mContext;


    public BarResultsAdapter(Context context, List<Bar> items) {
        mItems = items;
        mContext = context;
    };

    private Context getContext() {
        return mContext;
    }


    @Override
    public BarResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);
        BarResultsAdapter.ViewHolder viewHolder = new BarResultsAdapter.ViewHolder(contactView);
        return viewHolder;
    }

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

        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getBar_name());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
