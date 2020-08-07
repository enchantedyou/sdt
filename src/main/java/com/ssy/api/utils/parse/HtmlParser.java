package com.ssy.api.utils.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * @Description html解析器
 * @Author sunshaoyu
 * @Date 2020年07月31日-13:42
 */
public class HtmlParser {

    /**
     * @Description 根据url获取html Document
     * @Author sunshaoyu
     * @Date 2020/7/31-13:43
     * @param url
     * @return org.jsoup.nodes.Document
     */
    public static Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    
}
