package com.ebanma.cloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.ebanma.cloud.io.Resources;
import com.ebanma.cloud.pojo.Configuration;
import org.dom4j.Document;


import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author WHY
 * @version $ Id: XMLConfigBuilder, v 0.1 2023/03/21 8:31 kmkmj Exp $
 */
public class XMLConfigBuilder {

    /*
        使用dom4j+xpath解析配置文件，封装Configuration对象
     */
    public Configuration parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        list.forEach(element -> {
            properties.setProperty(element.attributeValue("name")
                                  ,element.attributeValue("value"));
        });

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getProperty("driverClass"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));

        Configuration configuration = new Configuration();
        configuration.setDataSource(dataSource);

        /* 解析映射配置文件 */
        List<Element> mapperList = rootElement.selectNodes("//mapper");

        for (Element element : mapperList) {
            String path = element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(path);
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsStream);
        }
        return configuration;

    }
}