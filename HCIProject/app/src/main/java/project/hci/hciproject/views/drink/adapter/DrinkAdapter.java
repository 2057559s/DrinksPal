package project.hci.hciproject.views.drink.adapter;

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
 * Created by nicholassaunderson on 17/11/2016.
 */


public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {

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


    public DrinkAdapter(Context context, List<Drink> items) {
        mItems = items;
        mContext = context;
    }


    private Context getContext() {
        return mContext;
    }


    @Override
    public DrinkAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DrinkAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.itemView.setSelected(selectedPos == position);

        Drink contact = mItems.get(position);

        if(selectedPos == position){
            viewHolder.itemView.setBackgroundColor(Color.GREEN);
        }else{
            viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

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
        textView.setText(contact.getDrink_name());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}


