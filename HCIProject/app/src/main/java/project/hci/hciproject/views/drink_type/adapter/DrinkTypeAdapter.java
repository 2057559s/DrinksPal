package project.hci.hciproject.views.drink_type.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import project.hci.hciproject.R;
import project.hci.hciproject.realm.DrinkType;

/**
 * Created by thevj on 27/11/16.
 */

public class DrinkTypeAdapter extends RecyclerView.Adapter<DrinkTypeAdapter.ViewHolder> {

    public static int selectedPos;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;

        public ViewHolder(View itemView) {

            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }


    private List<DrinkType> mItems;
    private Context mContext;


    public DrinkTypeAdapter(Context context, List<DrinkType> items) {
        mItems = items;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public DrinkTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        DrinkTypeAdapter.ViewHolder viewHolder = new DrinkTypeAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DrinkTypeAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.itemView.setSelected(selectedPos == position);

        DrinkType contact = mItems.get(position);

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


        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getDrinkType());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}