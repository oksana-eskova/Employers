package com.oieskova.employers.adapter;

import android.icu.util.LocaleData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oieskova.employers.R;
import com.oieskova.employers.data.Employer;
import com.oieskova.employers.utils.DataUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployerAdapter extends RecyclerView.Adapter<EmployerAdapter.EmployerViewHolder> {
    private List<Employer> employers;
    private OnEmployerClickListener onEmployerClickListener;

    public void setOnEmployerClickListener(OnEmployerClickListener onEmployerClickListener) {
        this.onEmployerClickListener = onEmployerClickListener;
    }

    public interface OnEmployerClickListener{
        void onEmployerClick(int position);
    }
    public EmployerAdapter() {
        employers=new ArrayList<Employer>();
    }

    public void setEmployers(List<Employer> employers) {
        this.employers = employers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.employer_item,parent,false);
        return new EmployerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployerViewHolder holder, int position) {
        Employer employer=employers.get(position);
        holder.textViewName.setText(employer.getLastName()+" "+employer.getName());
        String birthday=employer.getBirthday();
        int age=DataUtils.getAge(birthday);
        if(age!=0) {
            holder.textViewAge.setText("Возраст: " + age);
        }

    }

    @Override
    public int getItemCount() {
        return employers.size();
    }

    public class EmployerViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewAge;
        public EmployerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.textViewName);
            textViewAge=itemView.findViewById(R.id.textViewAge);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onEmployerClickListener!=null){
                        onEmployerClickListener.onEmployerClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
