package com.example.studentApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView title;
        public TextView description;
        OnClickListenner onClickListenner;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView, OnClickListenner onClickListenner) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.quiz_title);
            description = (TextView) itemView.findViewById(R.id.quiz_description);
            this.onClickListenner = onClickListenner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickListenner.onQuizClick(getAdapterPosition());
        }
    }
    private OnClickListenner mOnClickListenner;

    public interface OnClickListenner{
        void onQuizClick(int position);
    }

    private ArrayList<Quiz> quizList;

    // Pass in the contact array into the constructor
    public QuizAdapter(ArrayList<Quiz> quizzes, OnClickListenner onClickListenner) {
        this.quizList = quizzes;
        this.mOnClickListenner = onClickListenner;
    }

    @Override
    public QuizAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_quiz, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView, mOnClickListenner);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(QuizAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Quiz contact = quizList.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.title;
        textView.setText(contact.getTitle());
        TextView textView1 = holder.description;
        textView1.setText(contact.getDescription());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return quizList.size();
    }
}
