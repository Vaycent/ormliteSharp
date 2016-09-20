package vaycent.ormlitesharp;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Vaycent on 16/9/12.
 */
public class OrmliteSharp {

    private OrmliteDB ormliteDB;
    private DbObjectClass dbObjClass;

    private Context context;
    private String dbName = "";
    private int dbVersion = 0;
    private ArrayListClass<? extends Class> classList;

    /** New */
    public OrmliteSharp(Context ct, String name, int version, ArrayListClass<? extends Class> cl) {
        this.context = ct;
        this.dbName = name;
        this.dbVersion = version;
        this.classList = cl;

        OrmliteDB.initParameter(dbName, dbVersion, classList);
    }

    /** DataBase Control */
    public void addToDB(Class classObj, Object dbObj){
        try{
            OrmliteDB ormliteDB = OrmliteDB.getHelper(context);
            Dao myDao= ormliteDB.getDao(classObj);
            myDao.create(dbObj);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteById(DbObjectClass dbObjClass, int id){
        try{
            OrmliteDB ormliteDB = OrmliteDB.getHelper(context);
            Dao myDao= ormliteDB.getDao(dbObjClass.getClassData());
            myDao.deleteById(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Object selectBPById(DbObjectClass dbObjClass, int id){
        try{
            OrmliteDB ormliteDB = OrmliteDB.getHelper(context);
            Dao myDao= ormliteDB.getDao(dbObjClass.getClassData());
            Object resultObj=myDao.queryForId(id);
            return resultObj;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void updateToDB(DbObjectClass dbObjClass, Object dbObj){
        try{
            OrmliteDB ormliteDB = OrmliteDB.getHelper(context);
            Dao myDao= ormliteDB.getDao(dbObjClass.getClassData());
            myDao.update(dbObj);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteTable(DbObjectClass dbObjClass){
        try{
            OrmliteDB ormliteDB = OrmliteDB.getHelper(context);
            Dao myDao= ormliteDB.getDao(dbObjClass.getClassData());
            myDao.delete(myDao.queryForAll());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List selectAll(DbObjectClass dbObjClass){
        try{
            OrmliteDB ormliteDB = OrmliteDB.getHelper(context);
            Dao myDao= ormliteDB.getDao(dbObjClass.getClassData());
            return myDao.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    /** DataBase Parameter */
    public Context getContext() {
        return context;
    }
    public String getDbName() {
        return dbName;
    }
    public int getDbVersion() {
        return dbVersion;
    }
    public ArrayListClass<? extends Class> getClassList() {
        return classList;
    }

}
