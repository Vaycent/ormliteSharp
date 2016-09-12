package vaycent.ormlitesharp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vaycent on 16/9/10.
 */

public class OrmliteSharpHelper extends OrmLiteSqliteOpenHelper
{
    private static final String TAG="OrmliteDatabaseHelper";
    private static OrmliteSharpHelper instance;

    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private static ArrayList<Class> classList;
    private static String dbName = "";
    private static int dbVersion= 0;



    public static void initParameter(String name, int version, ArrayList<Class> cl){
        classList=cl;
        dbName=name;
        dbVersion=version;
    }

    private OrmliteSharpHelper(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }


    public static synchronized OrmliteSharpHelper getHelper(Context context) {
        if (instance == null)
        {
            synchronized (OrmliteSharpHelper.class)
            {
                if (instance == null)
                    if(dbName.equalsIgnoreCase("")||dbVersion==0){
                        Log.e(TAG,"You should use initParameter() function to setup dbName and dbVersion before you getHelper");
                    }else{
                        instance = new OrmliteSharpHelper(context,dbName,dbVersion);
                    }
            }
        }
        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /************************  @Override *************************/

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try
        {
            for(int i=0;i<classList.size();i++){
                Class classObj=classList.get(i).getClass();
                TableUtils.createTable(connectionSource, classObj);
                System.out.println("classObj name:"+classObj.getSimpleName());
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            for(int i=0;i<classList.size();i++){
                Class classObj=classList.get(i).getClass();
                TableUtils.dropTable(connectionSource, classObj, true);
            }
            onCreate(database, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void close()
    {
        super.close();

        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}