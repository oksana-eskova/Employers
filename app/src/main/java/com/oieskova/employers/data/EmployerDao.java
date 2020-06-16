package com.oieskova.employers.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
@Dao
public interface EmployerDao {
    @Query("SELECT * FROM employers")
    LiveData<List<Employer>> getAllEmployers();

    @Query("SELECT * FROM specialties")
    LiveData<List<Specialty>> getAllSpecialities();
    @Query("SELECT * FROM specialitiesVSemployers")
    LiveData<List<SpecialitiesVSEmployers>> getAllRelations();
     @Query("SELECT * FROM employers WHERE idEmployer== :employerId")
    Employer getEmployerById(int employerId);

     @Query("SELECT * FROM specialties WHERE id== :specialtyId")
    Specialty getSpecialtyById(int specialtyId);

     @Query("DELETE FROM employers")
    void deleteAllEmployers();

     @Insert
    void insertEmployer(Employer employer);

     @Delete
    void deleteEmployer(Employer employer);

    @Query("DELETE FROM specialties")
    void deleteAllSpecialties();
    @Query("DELETE FROM specialitiesVSemployers")
    void deleteAllRelations();

    @Insert
    void insertSpecialty(Specialty specialty);

    @Delete
    void deleteSpecialty(Specialty specialty);
    @Insert
    void insertRelation(SpecialitiesVSEmployers relation);

    @Query("SELECT * FROM specialitiesVSemployers WHERE idSpeciality== :specialityId")
    List<SpecialitiesVSEmployers> getRelationsForSpeciality(int specialityId);

}
