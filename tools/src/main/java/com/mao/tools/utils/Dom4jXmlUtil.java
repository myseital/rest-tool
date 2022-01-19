package com.mao.tools.utils;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

/**
 * dom4j工具类
 *
 * @author myseital
 **/
@Slf4j
public final class Dom4jXmlUtil {

    public static Document getDocument() {
        return DocumentHelper.createDocument();
    }

    public static Document parse2Document(String xml) throws DocumentException {
        return DocumentHelper.parseText(xml);
    }

}