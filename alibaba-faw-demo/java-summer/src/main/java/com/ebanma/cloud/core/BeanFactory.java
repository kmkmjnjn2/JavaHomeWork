package com.ebanma.cloud.core;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactory {
    // 任务一：读取解析xml,通过反射技术实例化对象并且存储待用(map集合)
    // 任务二：对外提供获取实例对象接口

    /**
     * 存储对象
     */
    private static Map<String, Object> map = new HashMap<>();

    /**
     * 任务一：读取解析xml,通过反射技术实例化对象并且存储待用(map集合)
     */
    static {
        //加载xml文件
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> beanList = rootElement.selectNodes("//bean");
            for (int i = 0; i < beanList.size(); i++) {
                Element element = beanList.get(i);
                //处理每个bean元素，获取id和class元素
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");
                //实例化对象，存起来
                Class<?> aClass = Class.forName(clazz);
                Object newInstance = aClass.newInstance();
                map.put(id, newInstance);
            }
            //解析依赖注入
            List<Element> propertyList = rootElement.selectNodes("//property");
            for (int i = 0; i < propertyList.size(); i++) {
                Element element = propertyList.get(i);
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");
                //找到当前需要依赖注入的bean
                Element parent = element.getParent();
                //从容器中获取父元素的实例
                String parentId = parent.attributeValue("id");
                Object parentObject = map.get(parentId);
                //使用反射进行依赖注入
                Method[] methods = parentObject.getClass().getMethods();
                for (int j = 0; j < methods.length; j++) {
                    Method method = methods[j];
                    if (method.getName().equalsIgnoreCase("set" + name)) {
                        method.invoke(parentObject, map.get(ref));
                    }
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 任务二:对外提供获取实例对象的接口
     * @param id
     * @return
     */
    public static Object getBean(String id) {
        return map.get(id);
    }


}
