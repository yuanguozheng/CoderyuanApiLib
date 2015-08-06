/**
 * Copyright (c) 2015 coderyuan.com. All Rights Reserved.
 * <p>
 * CoderyuanApiLib
 * <p>
 * DbUtils.java created on 上午11:48
 *
 * @author yuanguozheng
 * @version 1.0.0
 * @since 15/8/6
 */
package com.coderyuan.db;

import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.Element;

import com.coderyuan.models.DbConnectionModel;
import com.coderyuan.utils.XmlParser;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

/**
 * DbUtils
 *
 * @author yuanguozheng
 */
public class DbUtils {

    private static final String XML_PATH = "db_config.xml";

    private ComboPooledDataSource mDatasource;
    private static DbUtils sDb = new DbUtils();
    private static String sUrl;

    public static DbUtils getInstance() {
        return sDb;
    }

    private DbUtils() {
        sUrl = new File(System.getProperty("user.dir"), XML_PATH).getAbsolutePath();
        try {
            DbConnectionModel config = getConfig();
            mDatasource = new ComboPooledDataSource();
            mDatasource.setDriverClass(config.getDriver());
            mDatasource.setJdbcUrl(config.getUrl());
            mDatasource.setUser(config.getUserName());
            mDatasource.setPassword(config.getPassword());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        try {
            return mDatasource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void finalize() throws Throwable {
        DataSources.destroy(mDatasource);
        super.finalize();
    }

    private DbConnectionModel getConfig() {
        Document document = XmlParser.getDocument(sUrl);
        Element root = document.getRootElement();
        DbConnectionModel connectionInfo = new DbConnectionModel();
        connectionInfo.setDriver(XmlParser.getValue(root, "driver"));
        connectionInfo.setUrl(XmlParser.getValue(root, "url"));
        connectionInfo.setUserName(XmlParser.getValue(root, "username"));
        connectionInfo.setPassword(XmlParser.getValue(root, "password"));
        return connectionInfo;
    }
}
