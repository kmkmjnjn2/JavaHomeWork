package com.ebanma.cloud.io;

import java.io.InputStream;

/**
 * @author WHY
 * @version $ Id: Resources, v 0.1 2023/03/20 21:14 kmkmj Exp $
 */
public class Resources {


    public static InputStream getResourceAsStream(String path) {
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}