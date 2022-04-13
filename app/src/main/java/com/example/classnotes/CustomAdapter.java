package com.example.classnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> noteTitle;
    ArrayList<String> noteDate;
    ArrayList<String> noteDetails;
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, ArrayList<String> noteTitle, ArrayList<String> noteDate, ArrayList<String> noteDetails) {
        this.noteTitle = noteTitle;
        this.noteDate = noteDate;
        this.noteDetails = noteDetails;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return noteTitle.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_note_item, null);
        TextView noteTitle = (TextView) view.findViewById(R.id.title);
        TextView noteDate = (TextView) view.findViewById(R.id.date);
        TextView noteDetails = (TextView) view.findViewById(R.id.details);

        noteTitle.setText(this.noteTitle.get(i));
        noteDate.setText(this.noteDate.get(i));
        noteDetails.setText(this.noteDetails.get(i));
        return view;
    }
}