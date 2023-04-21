package com.ebanma.cloud.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WHY
 * @version $ Id: Configuration, v 0.1 2023/03/20 21:23 kmkmj Exp $
 */
public class Configuration {

    private DataSource dataSource;


    private Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "dataSource=" + dataSource +
                ", mappedStatementMap=" + mappedStatementMap +
                '}';
    }
}