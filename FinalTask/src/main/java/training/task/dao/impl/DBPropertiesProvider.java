package training.task.dao.impl;

import java.util.ResourceBundle;

public class DBPropertiesProvider {
    private static final DBPropertiesProvider instance = new DBPropertiesProvider();
    private ResourceBundle bundle = ResourceBundle.getBundle("dao.db");

    private DBPropertiesProvider(){}

    public static DBPropertiesProvider getInstance(){return instance;}

    public String getString(String key) {return bundle.getString(key);}

    public int getInt(String key) {
        String value = bundle.getString(key);
        return Integer.parseInt(value);
    }
}
