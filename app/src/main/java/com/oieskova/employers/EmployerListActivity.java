package com.oieskova.employers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.oieskova.employers.adapter.EmployerAdapter;
import com.oieskova.employers.data.Employer;
import com.oieskova.employers.data.MainViewModel;
import com.oieskova.employers.data.SpecialitiesVSEmployers;

import java.util.ArrayList;
import java.util.List;

public class EmployerListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewEmployers;
    private List<Employer> employers;
    private List<SpecialitiesVSEmployers> relations;
    private MainViewModel viewModel;
    EmployerAdapter employerAdapter;
    private int idSpeciality;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_list);
        recyclerViewEmployers=findViewById(R.id.recyclerViewEmployers);
        recyclerViewEmployers.setLayoutManager(new LinearLayoutManager(this));

        Intent intent=getIntent();
        idSpeciality=intent.getIntExtra("idSpeciality",0);

        viewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        relations=viewModel.getRelationsForSpeciallity(idSpeciality);
        employers=new ArrayList<Employer>();
        for(SpecialitiesVSEmployers relation:relations){
            Employer employerFromBase=viewModel.getEmployerById(relation.getIdEmployer());
            employers.add(employerFromBase);
        }
        employerAdapter=new EmployerAdapter();
        recyclerViewEmployers.setAdapter(employerAdapter);
        employerAdapter.setEmployers(employers);
        employerAdapter.setOnEmployerClickListener(new EmployerAdapter.OnEmployerClickListener() {
            @Override
            public void onEmployerClick(int position) {
                Intent intent=new Intent(EmployerListActivity.this, EmployerDetailActivity.class);
                intent.putExtra("employerId",employers.get(position).getIdEmployer());
                intent.putExtra("specialityId",idSpeciality);
                startActivity(intent);
            }
        });
    }
}
