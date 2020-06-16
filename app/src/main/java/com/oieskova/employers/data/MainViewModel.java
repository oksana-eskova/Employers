package com.oieskova.employers.data;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainViewModel extends AndroidViewModel {
    private static EmployerDatabase database;
    private LiveData<List<Employer>>  employers;
    private LiveData<List<Specialty>> specialties;
    private LiveData<List<SpecialitiesVSEmployers>> relations;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database=EmployerDatabase.getInstance(getApplication());
        employers=database.employerDao().getAllEmployers();
        specialties=database.employerDao().getAllSpecialities();
        relations=database.employerDao().getAllRelations();
    }

    public LiveData<List<Employer>> getEmployers() {
        return employers;
    }

    public LiveData<List<Specialty>> getSpecialties() {
        return specialties;
    }

    public LiveData<List<SpecialitiesVSEmployers>> getRelations() {
        return relations;
    }

    public void insertEmployer(Employer employer){
        new InsertEmployerTask().execute(employer);
    }
    private static class InsertEmployerTask extends AsyncTask<Employer, Void, Void>{

        @Override
        protected Void doInBackground(Employer... employers) {
            if(employers!=null&&employers.length>0){
                database.employerDao().insertEmployer(employers[0]);
            }
            return null;
        }
    }
    public void insertSpecialty(Specialty specialty){
        new InsertSpecialtyTask().execute(specialty);
    }
    private static class InsertSpecialtyTask extends AsyncTask<Specialty,Void,Void>{

        @Override
        protected Void doInBackground(Specialty... specialties) {
            if(specialties!=null&&specialties.length>0){
                database.employerDao().insertSpecialty(specialties[0]);
            }
            return null;
        }
    }
    public void deleteEmployer(Employer employer){
        new DeleteEmployerTask().execute(employer);
    }
    private static class DeleteEmployerTask extends AsyncTask<Employer, Void,Void>{

        @Override
        protected Void doInBackground(Employer... employers) {
            if(employers!=null&&employers.length>0){
                database.employerDao().deleteEmployer(employers[0]);
            }
            return null;
        }
    }
    public void deleteSpecialty(Specialty specialty){
        new DeleteSpecialtyTask().execute(specialty);
    }
    private static class DeleteSpecialtyTask extends AsyncTask<Specialty,Void,Void>{

        @Override
        protected Void doInBackground(Specialty... specialties) {
            if(specialties!=null&&specialties.length>0){
                database.employerDao().deleteSpecialty(specialties[0]);
            }
            return null;
        }
    }
    public Employer getEmployerById(int id){
        Employer result= null;
        try {
            result = new GetEmployerTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
    private static class GetEmployerTask extends AsyncTask<Integer,Void,Employer>{

        @Override
        protected Employer doInBackground(Integer... integers) {
            Employer result=null;
            if(integers!=null&&integers.length>0){
                result=database.employerDao().getEmployerById(integers[0]);
            }
            return result;
        }
    }
    public Specialty getSpecialtyById(int id){
        Specialty result=null;
        try {
            result=new GetSpecialtyTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
    private static class GetSpecialtyTask extends AsyncTask<Integer,Void,Specialty>{

        @Override
        protected Specialty doInBackground(Integer... integers) {
            Specialty result=null;
            if(integers!=null&&integers.length>0){
                result=database.employerDao().getSpecialtyById(integers[0]);
            }
            return result;
        }
    }
    public void deleteAllEmployers(){
        new DeleteAllEmployersTask().execute();
    }
    private static class DeleteAllEmployersTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            database.employerDao().deleteAllEmployers();
            return null;
        }
    }
    public void deleteAllSpecialties(){
        new DeleteAllSpacialtiesTask().execute();
    }
    private static class DeleteAllSpacialtiesTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            database.employerDao().deleteAllSpecialties();
            return null;
        }
    }
    public void deleteAllRelations(){
        new DeleteAllRelationsTask().execute();
    }
    private static class DeleteAllRelationsTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            database.employerDao().deleteAllRelations();
            return null;
        }
    }
    public void insertRelation(SpecialitiesVSEmployers relation){
        new InsertRelationTask().execute(relation);
    }
    private static class InsertRelationTask extends AsyncTask<SpecialitiesVSEmployers,Void,Void>{

        @Override
        protected Void doInBackground(SpecialitiesVSEmployers... specialitiesVSEmployers) {
            if(specialitiesVSEmployers!=null&&specialitiesVSEmployers.length>0){
                database.employerDao().insertRelation(specialitiesVSEmployers[0]);
            }
            return null;
        }
    }
    public List<SpecialitiesVSEmployers> getRelationsForSpeciallity(int specialityId){
        List<SpecialitiesVSEmployers> result=null;
        try {
            result=new GetRelationForSpecialityTask().execute(specialityId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;

    }
    private static class GetRelationForSpecialityTask extends AsyncTask<Integer,Void,List<SpecialitiesVSEmployers>>{

        @Override
        protected List<SpecialitiesVSEmployers> doInBackground(Integer... integers) {
            List<SpecialitiesVSEmployers> result=null;
            if(integers!=null&&integers.length>0) {
                result=database.employerDao().getRelationsForSpeciality(integers[0]);
            }
            return result;
        }
    }


}
