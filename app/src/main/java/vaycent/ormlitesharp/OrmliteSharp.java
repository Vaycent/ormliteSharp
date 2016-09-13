package vaycent.ormlitesharp;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Vaycent on 16/9/12.
 */
public class OrmliteSharp {


    private ArrayListClass<? extends Class> classList;
    private String dbName = "";
    private int dbVersion = 0;
    private Context context;

    private OrmliteSharpHelper osHelper;


    public OrmliteSharp(Context ct, String name, int version, ArrayListClass<? extends Class> cl) {
        this.context = ct;
        this.dbName = name;
        this.dbVersion = version;
        this.classList = cl;

        OrmliteSharpHelper.initParameter(dbName, dbVersion, classList);
    }

    public OrmliteSharpHelper synchronizedDB(){
        osHelper = OrmliteSharpHelper.getHelper(context);
        return osHelper;
    }

    public Dao getOS_Dao(OrmliteSharpHelper osh, Class daoClass) {
        Dao dao = null;
        osHelper=osh;
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



}
