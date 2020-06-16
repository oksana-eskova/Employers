package com.oieskova.employers.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "specialitiesVSemployers")
public class SpecialitiesVSEmployers {
    @PrimaryKey(autoGenerate = true)
    private int idLine;
    private int idSpeciality;
    private int idEmployer;

    public SpecialitiesVSEmployers(int idLine, int idSpeciality, int idEmployer) {
        this.idLine = idLine;
        this.idSpeciality = idSpeciality;
        this.idEmployer = idEmployer;
    }
    @Ignore
    public SpecialitiesVSEmployers(int idSpeciality, int idEmployer) {
        this.idSpeciality = idSpeciality;
        this.idEmployer = idEmployer;
    }

    public int getIdLine() {
        return idLine;
    }

    public void setIdLine(int idLine) {
        this.idLine = idLine;
    }

    public int getIdSpeciality() {
        return idSpeciality;
    }

    public void setIdSpeciality(int idSpeciality) {
        this.idSpeciality = idSpeciality;
    }

    public int getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(int idEmployer) {
        this.idEmployer = idEmployer;
    }
}
