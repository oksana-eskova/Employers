package com.oieskova.employers.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.oieskova.employers.R;
import com.oieskova.employers.data.Specialty;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpecialitiesAdapter extends RecyclerView.Adapter<SpecialitiesAdapter.SpecialistViewHolder> {
    private List<Specialty> specialties;
    private OnSpecialityClickListener onSpecialityClickListener;

    public void setOnSpecialityClickListener(OnSpecialityClickListener onSpecialityClickListener) {
        this.onSpecialityClickListener = onSpecialityClickListener;
    }

    public interface OnSpecialityClickListener {
        void onSpecialityClick(int position);
    }

    public SpecialitiesAdapter() {
        specialties=new ArrayList<Specialty>();
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SpecialistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.specialty_item,parent,false);
        return new SpecialistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialistViewHolder holder, int position) {
        TextView textViewSpecialty=holder.textViewSpeciality;
        Specialty specialty=specialties.get(position);
        textViewSpecialty.setText(specialty.getId()+"  "+specialty.getName());

    }

    @Override
    public int getItemCount() {
        return specialties.size();
    }

    public  class SpecialistViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewSpeciality;
        public SpecialistViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSpeciality=itemView.findViewById(R.id.textViewSpecialty);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onSpecialityClickListener!=null){
                        onSpecialityClickListener.onSpecialityClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
