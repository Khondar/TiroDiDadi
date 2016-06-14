package com.example.basil.dicelauncher;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by basil on 14/06/2016.
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    public static void main(String[] args) throws SQLException, IOException {

        writeConfigFile("ormlite_config.txt");
    }
}
