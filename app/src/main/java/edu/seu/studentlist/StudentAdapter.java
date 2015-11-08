package edu.seu.studentlist;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {
    private ArrayList<Student> items;
    private Context context;

    public StudentAdapter(Context context, ArrayList<Student> items) {
        super(context, R.layout.student_list_item, items);

        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = vi.inflate(R.layout.student_list_item, null);
        }

        Student s = items.get(position);

        // get components
        TextView fullNameTextView = (TextView) itemView.findViewById(R.id.txtStudentFullName);
        TextView emailTextView = (TextView) itemView.findViewById(R.id.txtStudentEmail);

        // assign corresponding values
        fullNameTextView.setText(s.getFullName());
        emailTextView.setText(s.getEmail());

        return itemView;
    }
}
