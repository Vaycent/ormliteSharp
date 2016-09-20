package vaycent.ormlitesharp;

/**
 * Created by Vaycent on 16/9/14.
 */
public class DbObjectClass<T extends Class> {
    public T classData;

    public void setClassData(T classData) {
        this.classData = classData;
    }
    public T getClassData() {
        return classData;
    }

    public void setSelectFromDB(Object obj){
        classData = (T) obj;
    }

    public T getSelectFromDB(){
        return classData;
    }
}

