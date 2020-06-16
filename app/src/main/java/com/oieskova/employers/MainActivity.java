package com.oieskova.employers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.oieskova.employers.adapter.SpecialitiesAdapter;
import com.oieskova.employers.data.Employer;
import com.oieskova.employers.data.MainViewModel;
import com.oieskova.employers.data.SpecialitiesVSEmployers;
import com.oieskova.employers.data.Specialty;
import com.oieskova.employers.utils.JSONUtils;
import com.oieskova.employers.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;

    private LiveData<List<Specialty>> specialties;
    private RecyclerView recyclerViewSpecialists;
    SpecialitiesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewSpecialists=findViewById(R.id.recyclerViewSpecialities);
        recyclerViewSpecialists.setLayoutManager(new LinearLayoutManager(this));
        adapter=new SpecialitiesAdapter();
        recyclerViewSpecialists.setAdapter(adapter);
        viewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        downloadData();

        specialties=viewModel.getSpecialties();

         specialties.observe(this, new Observer<List<Specialty>>() {
            @Override
            public void onChanged(List<Specialty> specialties) {
               adapter.setSpecialties(specialties);
            }
        });
        adapter.setOnSpecialityClickListener(new SpecialitiesAdapter.OnSpecialityClickListener() {
            @Override
            public void onSpecialityClick(int position) {
               // Toast.makeText(MainActivity.this,"Номер позиции "+position,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,EmployerListActivity.class);
                int idSpeciality=specialties.getValue().get(position).getId();
                intent.putExtra("idSpeciality",idSpeciality);
                startActivity(intent);
            }
        });

    }
    private void downloadData(){
        ArrayList<Employer> employersFromJSON=null;
        ArrayList<Specialty> specialtiesFromJSON=null;
        ArrayList<SpecialitiesVSEmployers> relationsFromJSON=null;
        JSONObject jsonObject= NetworkUtils.getJson();
        if(jsonObject!=null) {
            JSONUtils jsonUtils = new JSONUtils(jsonObject);
            employersFromJSON = jsonUtils.getEmployers();
            specialtiesFromJSON = jsonUtils.getSpecialities();
            relationsFromJSON = jsonUtils.getRelations();
        }
        if(employersFromJSON!=null&& !employersFromJSON.isEmpty()) {
            viewModel.deleteAllEmployers();
            for (Employer employer : employersFromJSON) {
                viewModel.insertEmployer(employer);
            }
        }
        if(specialtiesFromJSON!=null&& !specialtiesFromJSON.isEmpty()) {
            viewModel.deleteAllSpecialties();
            for (Specialty specialty : specialtiesFromJSON) {
                viewModel.insertSpecialty(specialty);
            }
        }
        if(relationsFromJSON!=null&&!relationsFromJSON.isEmpty()){
            viewModel.deleteAllRelations();
            for(SpecialitiesVSEmployers relation:relationsFromJSON){
                viewModel.insertRelation(relation);
            }
        }
    }
}
