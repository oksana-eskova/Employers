package com.oieskova.employers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.oieskova.employers.R;
import com.oieskova.employers.data.Employer;
import com.oieskova.employers.data.MainViewModel;
import com.oieskova.employers.data.Specialty;
import com.oieskova.employers.utils.DataUtils;

public class EmployerDetailActivity extends AppCompatActivity {
    private int idEmployer;
    private int idSpeciality;
    private Employer employer;
    private Specialty specialty;
    private MainViewModel viewModel;

    private TextView textViewName;
    private TextView textViewLastName;
    private TextView textViewBirthday;
    private TextView textViewAge;
    private TextView textViewSpecialityEmployer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_detail);
        textViewName=findViewById(R.id.textViewName);
        textViewLastName=findViewById(R.id.textViewLastName);
        textViewBirthday=findViewById(R.id.textViewBirthday);
        textViewAge=findViewById(R.id.textViewAge);
        textViewSpecialityEmployer=findViewById(R.id.textViewSpecialityEmployer);

        Intent intent=getIntent();
        idEmployer=intent.getIntExtra("employerId",0);
        idSpeciality=intent.getIntExtra("specialityId",0);

        viewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        employer=viewModel.getEmployerById(idEmployer);
        specialty=viewModel.getSpecialtyById(idSpeciality);

        textViewName.setText(employer.getName());
        textViewLastName.setText(employer.getLastName());
        String birthday=employer.getBirthday();
        if((birthday!=null)&&(!birthday.isEmpty())&&!birthday.equals("null")) {
            textViewBirthday.setText(birthday);
        }else{
            textViewBirthday.setText(" ");
        }
        int age= DataUtils.getAge(employer.getBirthday());
        if(age!=0) {
            textViewAge.setText(""+age);
        }else{
            textViewAge.setText(" ");
        }
        String specialityName=specialty.getName();
        textViewSpecialityEmployer.setText(specialityName);

    }
}
