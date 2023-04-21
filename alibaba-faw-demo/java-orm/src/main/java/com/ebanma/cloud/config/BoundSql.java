package com.ebanma.cloud.config;

import com.ebanma.cloud.utils.ParameterMapping;

import java.util.List;

/**
 * @author WHY
 * @version $ Id: BoundSql, v 0.1 2023/03/21 10:10 kmkmj Exp $
 */
public class BoundSql {

    private String sql;

    private List<ParameterMapping> parameterMappingList;

    public BoundSql(String sql, List<ParameterMapping> parameterMappingList) {
        this.sql = sql;
        this.parameterMappingList = parameterMappingList;
    }

    @Override
    public String toString() {
        return "BoundSql{" +
                "sql='" + sql + '\'' +
                ", parameterMappingList=" + parameterMappingList +
                '}';
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}