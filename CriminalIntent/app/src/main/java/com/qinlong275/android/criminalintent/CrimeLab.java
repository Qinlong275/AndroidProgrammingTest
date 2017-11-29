package com.qinlong275.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.CrimeDbSchema.CrimeBaseHelper;
import database.CrimeDbSchema.CrimeCursorWrapper;
import database.CrimeDbSchema.CrimeDbSchema.CrimeTable;


/**
 * Created by 秦龙 on 2017/9/5.
 */

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context){
        if (sCrimeLab==null){
            sCrimeLab=new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase=new CrimeBaseHelper(context).getWritableDatabase();
    }

    public void addCrime(Crime c){
        ContentValues values=getContentValues(c);
        mDatabase.insert(CrimeTable.NAME,null,values);
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes=new ArrayList<>();

        CrimeCursorWrapper cursor=queryCrimes(null,null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id){

        CrimeCursorWrapper cursor=queryCrimes(CrimeTable.Cols.UUID+" = ?",new String[]{id.toString()});
        try{
            if (cursor.getCount()==0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        }finally {
            cursor.close();
        }

    }

    public void update(Crime crime){
        String uuidString=crime.getId().toString();
        ContentValues values=getContentValues(crime);
        mDatabase.update(CrimeTable.NAME,values,CrimeTable.Cols.UUID+"= ?",new String[]{uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor=mDatabase.query(
                CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values=new ContentValues();
        values.put(CrimeTable.Cols.UUID,crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE,crime.getTitle());
        values.put(CrimeTable.Cols.DATE,crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED,crime.isSolved()?1:0);
        values.put(CrimeTable.Cols.SUSPECT,crime.getSuspect());

        return values;
    }

    public File getPhotoFile(Crime crime){
        File fileDir=mContext.getFilesDir();
        return new File(fileDir,crime.getPhotoFilename());
    }
}
