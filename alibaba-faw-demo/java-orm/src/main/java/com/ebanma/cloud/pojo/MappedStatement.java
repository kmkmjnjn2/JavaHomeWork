package com.ebanma.cloud.pojo;

/**
 * @author WHY
 * @version $ Id: MappedStatement, v 0.1 2023/03/20 21:24 kmkmj Exp $
 */
public class MappedStatement {

    private String mappedStatementId;

    private String resultType;

    private String parameterType;

    private String commandType;

    private String sql;

    public String getMappedStatementId() {
        return mappedStatementId;
    }

    public void setMappedStatementId(String mappedStatementId) {
        this.mappedStatementId = mappedStatementId;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "MappedStatement{" +
                "mappedStatementId='" + mappedStatementId + '\'' +
                ", resultType='" + resultType + '\'' +
                ", parameterType='" + parameterType + '\'' +
                ", commandType='" + commandType + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}