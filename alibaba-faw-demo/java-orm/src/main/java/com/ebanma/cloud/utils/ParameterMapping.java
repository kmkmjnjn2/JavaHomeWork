package com.ebanma.cloud.utils;

/**
 * @author WHY
 * @version $ Id: ParameterMapping, v 0.1 2023/03/21 10:12 kmkmj Exp $
 */
public class ParameterMapping {

    private String content;

    public ParameterMapping(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ParameterMapping{" +
                "content='" + content + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}