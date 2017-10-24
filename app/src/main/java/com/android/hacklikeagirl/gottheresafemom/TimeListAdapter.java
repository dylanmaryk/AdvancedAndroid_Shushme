package com.android.hacklikeagirl.gottheresafemom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.hacklikeagirl.gottheresafemom.provider.MainActivity;

import java.util.ArrayList;


/**
 * Created by Anna on 23.10.2017.
 */

public class TimeListAdapter extends RecyclerView.Adapter<TimeListAdapter.TimeViewHolder> {

    private Context mContext;
    private ArrayList<String> mTimes = new ArrayList<>(0);
    private Callbacks callbacks;


    /**
     * Constructor using the context and the db cursor
     *
     * @param context the calling context/activity
     */
    public TimeListAdapter(Context context, ArrayList<String> times) {
        this.mContext = context;
        this.mTimes = times;
    }

    public TimeListAdapter(Callbacks callbacks){
        this.callbacks = callbacks;
    }

    public TimeListAdapter(Context context, ArrayList<String> times, Callbacks callbacks) {
        this.mContext = context;
        this.mTimes = times;
        this.callbacks = callbacks;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item
     *
     * @param parent   The ViewGroup into which the new View will be added
     * @param viewType The view type of the new View
     * @return A new PlaceViewHolder that holds a View with the item_place_card layout
     */
    @Override
    public TimeListAdapter.TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_time_card, parent, false);
        return new TimeListAdapter.TimeViewHolder(view);
    }

    /**
     * Binds the data from a particular position in the cursor to the corresponding view holder
     *
     * @param holder   The PlaceViewHolder instance corresponding to the required position
     * @param position The current position that needs to be loaded with data
     */
    @Override
    public void onBindViewHolder(TimeListAdapter.TimeViewHolder holder, final int position) {

        String date = "12.07.2018";
        holder.timeTextView.setText(MainActivity.times_list.get(position));
        holder.dateTextView.setText(date);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PlaceDbHelper dbHelper = new PlaceDbHelper(mContext);
                // TODO: 15.10.2017 delete
                //dbHelper.getWritableDatabase().delete(dbHelper.getDatabaseName(), ,null);
            }
        });
    }

    public void swapTimes(ArrayList<String> newTimes){
        mTimes = newTimes;
        if (mTimes != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    /**
     * Returns the number of items in the cursor
     *
     * @return Number of items in the cursor, or 0 if null
     */
    @Override
    public int getItemCount() {
        if(mTimes==null) return 0;
        return mTimes.size();
    }

    /**
     * PlaceViewHolder class for the recycler view item
     */
    class TimeViewHolder extends RecyclerView.ViewHolder {

        TextView timeTextView;
        TextView dateTextView;
        Button delete;
        private TimeListAdapter item;

        public TimeViewHolder(View itemView) {
            super(itemView);
            timeTextView = (TextView) itemView.findViewById(R.id.time_text_view);
            dateTextView = (TextView) itemView.findViewById(R.id.date_text_view);
            delete = (Button) itemView.findViewById(R.id.delete);

            dateTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callbacks.onDateClicked(item);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callbacks.onItemDelete(item);
                }
            });

        }

    }

    public interface Callbacks {
        void onDateClicked(TimeListAdapter item);

        void onItemDelete(TimeListAdapter item);
    }
}
