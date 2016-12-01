package project.hci.hciproject.views.drink_results.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import project.hci.hciproject.R;
import project.hci.hciproject.realm.Drink;

/**
 * Created by jake on 11/29/16.
 */

public class ListDrinksAdapter extends RecyclerView.Adapter<ListDrinksAdapter.ViewHolder> {

    public static int selectedPos;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;


        public ViewHolder(View itemView) {

            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }


    private List<Drink> mItems;

    private Context mContext;

    public ListDrinksAdapter(Context context, List<Drink> items) {
        mItems = items;
        mContext = context;
    };


    private Context getContext() {
        return mContext;
    }

    @Override
    public ListDrinksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        View contactView = inflater.inflate(R.layout.item_contact, parent, false);


        ListDrinksAdapter.ViewHolder viewHolder = new ListDrinksAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListDrinksAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.itemView.setSelected(selectedPos == position);

        Drink contact = mItems.get(position);

        viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notifyItemChanged(selectedPos);
                selectedPos = position;
                notifyItemChanged(selectedPos);

            }
        });


        String result = contact.getDrink_name() + "   £"+ String.valueOf(contact.getPrice());
        TextView textView = viewHolder.nameTextView;
        textView.setText(result);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
