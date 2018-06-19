package com.onaft.kravchenko.wave.waveandroid.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.onaft.kravchenko.wave.waveandroid.R;
import com.onaft.kravchenko.wave.waveandroid.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSpinnerAdapter extends ArrayAdapter<Employee> {

    private Context mContext;
    private List<Employee> mEmployees = new ArrayList<>();
    LayoutInflater inflate;


    public EmployeeSpinnerAdapter(@NonNull Context context, ArrayList<Employee> list){
        super(context, R.layout.list_item_spinner_employee, R.id.textView_item_spinner_employee_name, list);
        mContext = context;
        mEmployees = list;
        inflate = LayoutInflater.from(mContext);
    }

   /* @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_spinner_employee,parent,false);

        Employee employee = mEmployees.get(position);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.textView_item_spinner_employee_name);
        TextView txtRating = (TextView) convertView.findViewById(R.id.textView_item_spinner_employee_rating);
        txtTitle.setText(employee.getName());
        txtRating.setText(String.valueOf(100-Integer.valueOf(employee.getWork_interest()))+"%");

        return listItem;
    }*/

    @Override
    public int getCount() {
        return mEmployees.size();
    }

    @Override
    public Employee getItem(int i) {
        return mEmployees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflate.inflate(R.layout.list_item_spinner_employee,null);

        Employee employee = mEmployees.get(position);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.textView_item_spinner_employee_name);
        TextView txtRating = (TextView) convertView.findViewById(R.id.textView_item_spinner_employee_rating);
        txtTitle.setText(employee.getName());
        txtRating.setText(String.valueOf(100-Integer.valueOf(employee.getWork_interest()))+"%");

        return convertView;
    }
}
