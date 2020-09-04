package com.ssy.api.logic.higention;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.lang.Params;
import com.ssy.api.exception.SdtException;
import com.ssy.api.servicetype.ModuleMapService;
import com.ssy.api.utils.http.HttpUtil;
import com.ssy.api.utils.security.Md5Encrypt;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import com.ssy.api.utils.system.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @Description 私服gitlab相关处理包
 * @Author sunshaoyu
 * @Date 2020年07月31日-11:01
 */
@Component
@Slf4j
public class SdGitlabReader {

    @Autowired
    private SdtContextConfig contextConfig;
    @Autowired
    private ModuleMapService moduleMapService;
    private final String GIT_BASE_PATH = "http://e-git.yfb.sunline.cn/";

    /**
     * @Description 带gitlab session的get请求
     * @Author sunshaoyu
     * @Date 2020/7/31-14:00
     * @param url
     * @return java.lang.String
     */
    public String doGet(String url) throws IOException {
        return HttpUtil.doGet(url, new Params().add("Cookie", String.format("_gitlab_session=%s", contextConfig.getGitlabSession())));
    }

    /**
     * @Description 获取合并文件差异
     * @Author sunshaoyu
     * @Date 2020/7/31-14:16
     * @param moduleId
     * @param jiraId
     * @return java.lang.String
     */
    public String getMergeDiffs(String moduleId, String jiraId){
        BizUtil.fieldNotNull(moduleId, SdtDict.A.module_id.getId(), SdtDict.A.module_id.getLongName());
        BizUtil.fieldNotNull(jiraId, SdtDict.A.jira_id.getId(), SdtDict.A.jira_id.getLongName());
        String md5 = Md5Encrypt.md5EncodeStr(moduleId + jiraId);
        String cache = String.valueOf(RedisHelper.getValue(md5));

        if(CommUtil.isNotNull(cache)){
            return cache;
        }
        StringBuffer buffer = new StringBuffer();
        try{
            if(jiraId.length() > 8){
                List<String> urlList = searchMergedRequest(moduleId, jiraId);
                if(CommUtil.isNotNull(urlList)){
                    for(String url : urlList){
                        buffer.append(parseMergeFiles(url));
                    }
                }
            }
        }catch (Exception e){
            throw new SdtException("Failed to parse difference file list from jira identity["+jiraId+"] and business module["+moduleId+"]", e);
        }
        String diffStr = removeRepeate(buffer.toString());
        if(CommUtil.isNotNull(diffStr)){
            RedisHelper.addandSetValue(md5, diffStr, SdtConst.REDIS_GITDIFFS_TIMEOUT);
        }
        return diffStr;
    }

    /**
     * @Description 差异文件去重
     * @Author sunshaoyu
     * @Date 2020/8/3-15:19
     * @param diffStr
     * @return java.lang.String
     */
    private String removeRepeate(String diffStr){
        String splitToken = "\r\n";
        if(CommUtil.isNotNull(diffStr) && diffStr.contains(splitToken)){
            StringBuffer buffer = new StringBuffer();
            String[] diffs = diffStr.split(splitToken);
            List<String> diffList = new ArrayList<>(new LinkedHashSet<>(CommUtil.asList(diffs)));

            for(String d : diffList){
                buffer.append(d).append(splitToken);
            }
            return buffer.toString();
        }
        return diffStr;
    }

    /**
     * @Description 从合并url解析合并文件文件列表
     * @Author sunshaoyu
     * @Date 2020/7/31-13:25
     * @param mergeUrl
     * @return java.lang.String
     */
    private String parseMergeFiles(String mergeUrl) throws IOException {
        BizUtil.fieldNotNull(mergeUrl, SdtDict.A.merge_url.getId(), SdtDict.A.merge_url.getLongName());
        mergeUrl = mergeUrl.contains("diffs") ? mergeUrl + ".json" : mergeUrl + "/diffs.json";
        StringBuffer buffer = new StringBuffer();

        JSONArray diffFiles = JSON.parseObject(doGet(mergeUrl)).getJSONArray("diff_files");
        for(int i = 0;i < diffFiles.size();i++){
            String oldPath = diffFiles.getJSONObject(i).getString("old_path");
            String newPath = diffFiles.getJSONObject(i).getString("new_path");
            if(!newPath.toLowerCase().contains("keep")){
                buffer.append(newPath).append("\r\n");
            }
        }
        return buffer.toString();
    }

    /**
     * @Description 搜索合并请求链接列表
     * @Author sunshaoyu
     * @Date 2020/7/31-13:51
     * @param moduleId
     * @param jiraId
     * @return java.util.List<java.lang.String>
     */
    private List<String> searchMergedRequest(String moduleId, String jiraId) throws IOException {
        String searchUrl = moduleMapService.getModuleMapping(moduleId).getGitlabSearchUrl();
        List<String> mergeUrlList = new ArrayList<>();

        if(CommUtil.isNotNull(searchUrl)){
            for(String search : searchUrl.split(SdtConst.LIST_SPLIT_TOKEN)){
                Document document = Jsoup.parse(doGet(search + jiraId));
                List<Element> spanList = document.getElementsByClass("merge-request-title-text");
                for(Element e : spanList){
                    mergeUrlList.add(GIT_BASE_PATH + e.getElementsByTag("a").first().attr("href"));
                }
            }
        }
        return mergeUrlList;
    }
}
