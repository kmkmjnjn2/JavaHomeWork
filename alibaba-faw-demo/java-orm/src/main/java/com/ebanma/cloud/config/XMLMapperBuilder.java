package com.ebanma.cloud.config;

import com.ebanma.cloud.pojo.Configuration;
import com.ebanma.cloud.pojo.MappedStatement;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WHY
 * @version $ Id: XMLMapperBuilder, v 0.1 2023/03/21 8:31 kmkmj Exp $
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String sql = element.getTextTrim();

            //封装mappedStatement对象
            MappedStatement mappedStatement = new MappedStatement();
            String mappedStatementId = rootElement.attributeValue("namespace")+"."+id;
            mappedStatement.setMappedStatementId(mappedStatementId);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setSql(sql);
            mappedStatement.setCommandType("select");

            configuration.getMappedStatementMap().put(mappedStatementId, mappedStatement);
        }

    }
}