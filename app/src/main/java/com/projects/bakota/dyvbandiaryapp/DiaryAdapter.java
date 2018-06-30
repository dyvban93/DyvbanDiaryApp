package com.projects.bakota.dyvbandiaryapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import DAO.DiaryEntry;

import static android.provider.Settings.System.DATE_FORMAT;

/**
 * Created by Bakota on 26/06/2018.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder> {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    final private ItemClickListener mItemClickListener;

    private List<DiaryEntry> mDiaryEntries;
    private Context mContext;
    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    /**
     * Constructor for the DiaryAdapter that initializes the Context.
     *
     * @param context  the current Context
     * @param listener the ItemClickListener
     */
    public DiaryAdapter(Context context, ItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
    }

    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.diary_layout, parent, false);

        return new DiaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DiaryViewHolder holder, int position) {
        // Determine the values of the wanted data
        DiaryEntry diaryEntry = mDiaryEntries.get(position);
        String description = diaryEntry.getDescription();
        String title = diaryEntry.getTitle();
        String addAt = dateFormat.format(diaryEntry.getAddedDate());

        //Set values
        holder.diaryDescriptionView.setText(description);
        holder.addedAtView.setText(addAt);
        holder.titleView.setText(title);


    }


    @Override
    public int getItemCount() {
        if (mDiaryEntries == null) {
            return 0;
        }
        return mDiaryEntries.size();
    }

    public List<DiaryEntry> getTasks() {
        return mDiaryEntries;
    }

    public void setTasks(List<DiaryEntry> taskEntries) {
        mDiaryEntries = taskEntries;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }



    class DiaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Class variables for the task description,title and addAt TextViews
        TextView diaryDescriptionView;
        TextView addedAtView;
        TextView titleView;

        /**
         * Constructor for the DiaryViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public DiaryViewHolder(View itemView) {
            super(itemView);

            diaryDescriptionView = itemView.findViewById(R.id.diaryDescription);
            addedAtView = itemView.findViewById(R.id.addedAt);
            titleView = itemView.findViewById(R.id.titleTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = mDiaryEntries.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }



}


