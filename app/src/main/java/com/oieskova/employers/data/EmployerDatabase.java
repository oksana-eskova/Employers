package com.oieskova.employers.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Employer.class, Specialty.class,SpecialitiesVSEmployers.class},version=7, exportSchema = false)
public abstract class EmployerDatabase extends RoomDatabase {
    private static final String DB_NAME="employers4.db";
    private static final Object LOCK=new Object();

    private static EmployerDatabase database;
    public static EmployerDatabase getInstance(Context context){
        synchronized (LOCK){
            if(database==null){
                database= Room.databaseBuilder(context,EmployerDatabase.class,DB_NAME).build();
            }
        }
        return database;
    }
    public abstract EmployerDao employerDao();
}
