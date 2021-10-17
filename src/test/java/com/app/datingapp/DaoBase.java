package com.app.datingapp;

import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.QueryTimeOut;

public class DaoBase {

    @QueryTimeOut(2)
    @SqlUpdate("CREATE TABLE IF NOT EXISTS \"user\" (\n" +
            "  id        SERIAL PRIMARY KEY,\n" +
            "  name      VARCHAR(50),\n" +
            "  surname   VARCHAR(50),\n" +
            "  email     VARCHAR(50),\n" +
            "  password  VARCHAR(50),\n" +
            "  password2 VARCHAR(50),\n" +
            "  city      VARCHAR(100),\n" +
            "  country   VARCHAR(100),\n" +
            "  gender    VARCHAR(50),\n" +
            "  birth     DATE,\n" +
            "  likes     INT DEFAULT 0,\n" +
            "  bio       VARCHAR(100) DEFAULT NULL,\n" +
            "  register_date DATE\n" +
            ");")
    public void createUserTable() {

    };
}
