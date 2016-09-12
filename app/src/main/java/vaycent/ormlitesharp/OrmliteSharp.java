package vaycent.ormlitesharp;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Vaycent on 16/9/12.
 */
public class OrmliteSharp {

    private ArrayList<Class> classList;
    private String dbName = "";
    private int dbVersion = 0;
    private Context context;

    private OrmliteSharpHelper osHelper;


    public OrmliteSharp(Context ct, String name, int version, ArrayList<Class> cl) {
        this.context = ct;
        this.dbName = name;
        this.dbVersion = version;
        this.classList = cl;

        OrmliteSharpHelper.initParameter(dbName, dbVersion, classList);
        osHelper = OrmliteSharpHelper.getHelper(context);
    }

    public Dao getOS_Dao(Class daoClass) {
        osHelper = OrmliteSharpHelper.getHelper(context);
        Dao dao = null;
        try {
            dao = osHelper.getDao(daoClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dao;
    }


    public Context getContext() {
        return context;
    }

    public String getDbName() {
        return dbName;
    }

    public int getDbVersion() {
        return dbVersion;
    }

    public ArrayList<Class> getClassList() {
        return classList;
    }


}
