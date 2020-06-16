package com.oieskova.employers.utils;

import com.oieskova.employers.data.Employer;
import com.oieskova.employers.data.SpecialitiesVSEmployers;
import com.oieskova.employers.data.Specialty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class JSONUtils {
    private static String KEY_RESPONSE="response";
    private static String KEY_NAME="f_name";
    private static String KEY_LAST_NAME="l_name";
    private static String KEY_BIRTHDAY="birthday";
    private static String KEY_AVATR_URL="avatr_url";
    private static String KEY_SPECIALTY="specialty";
    private static String KEY_SPECIALTY_ID="specialty_id";
    private static String KEY_SPECIALTY_NAME="name";

    private JSONObject jsonObject;
    private ArrayList<Employer> employers;
    private ArrayList<Specialty> specialities;
    private ArrayList<SpecialitiesVSEmployers> relations;

    public JSONUtils(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        employers=new ArrayList<Employer>();
        HashSet<Specialty> result=new HashSet<>();
        relations=new ArrayList<SpecialitiesVSEmployers>();
        try {
            JSONArray jsonArray=jsonObject.getJSONArray(KEY_RESPONSE);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject   objectEmployer=jsonArray.getJSONObject(i);
                String name=objectEmployer.getString(KEY_NAME);
                String last_name=objectEmployer.getString(KEY_LAST_NAME);
                String birthday=objectEmployer.getString(KEY_BIRTHDAY);
                String avatr_url=objectEmployer.getString(KEY_AVATR_URL);
                Employer employer=new Employer(i,name,last_name,birthday,avatr_url);
                employers.add(employer);
                JSONArray specialtiesArray=objectEmployer.getJSONArray(KEY_SPECIALTY);
                for(int j=0;j<specialtiesArray.length();j++){
                    JSONObject objectSpecialty=specialtiesArray.getJSONObject(j);
                    int specialty_id=objectSpecialty.getInt(KEY_SPECIALTY_ID);
                    String specialty_name=objectSpecialty.getString(KEY_SPECIALTY_NAME);
                    Specialty specialty=new Specialty(specialty_id,specialty_name);
                    result.add(specialty);
                    SpecialitiesVSEmployers relation=new SpecialitiesVSEmployers(specialty_id,i);
                    relations.add(relation);
                }
            }
            specialities=new ArrayList<>();
            specialities.addAll(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Employer> getEmployers() {
        return employers;
    }

    public ArrayList<Specialty> getSpecialities() {
        return specialities;
    }

    public ArrayList<SpecialitiesVSEmployers> getRelations() {
        return relations;
    }
}
