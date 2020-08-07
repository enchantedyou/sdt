package com.ssy.api.logic.local;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.exception.SdtException;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.meta.flowtran.Flowtran;
import com.ssy.api.meta.flowtran.IntfService;
import com.ssy.api.utils.business.SdtBusiUtil;
import com.ssy.api.utils.parse.JavaCodeParser;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description java代码解析器
 * @Author sunshaoyu
 * @Date 2020年07月28日-16:34
 */
@Slf4j
public class SdJavaParser {

    /** 检查类方法的关键字 **/
    private static final String[] CHECK_KEY = {"check", "valid"};
    /** 字段必输调用语句 **/
    private static final String[] MANDATORY_STMT = {"BizUtil", "fieldNotNull"};

    /**
     * @Description 搜索必输字段
     * @Author sunshaoyu
     * @Date 2020/8/5-10:57
     * @param javaCodeFile
     * @param superMethodName
     * @return java.util.List<java.lang.String>
     */
    public static List<String> searchMandatoryFields(File javaCodeFile, String superMethodName){
        List<String> fieldNameList = new ArrayList<>();
        searchMandatoryFields(fieldNameList, javaCodeFile, superMethodName);
        return CommUtil.uniqueList(fieldNameList);
    }

    /**
     * @Description 根据flowtran获取必输字段列表
     * @Author sunshaoyu
     * @Date 2020/8/5-13:13
     * @param flowtranId
     * @return java.util.List<java.lang.String>
     */
    public static List<String> searchMandatoryFields(String flowtranId){
        List<String> fieldNameList = new ArrayList<>();
        Flowtran flowtran = SdFlowtranParser.load(flowtranId);

        //单个服务处理
        for(IntfService service : flowtran.getServiceList()){
            String serviceId = CommUtil.nvl(service.getId(), service.getServiceName());
            searchMandatoryFields(fieldNameList, OdbFactory.searchFile(String.format("%sImpl.java", SdtBusiUtil.getDotLeft(serviceId))), SdtBusiUtil.getDotRight(serviceId));
        }
        return CommUtil.uniqueList(fieldNameList);
    }

    /**
     * @Description 搜索必输字段
     * @Author sunshaoyu
     * @Date 2020/8/5-10:57
     * @param fieldNameList
     * @param javaCodeFile
     * @param methodName
     */
    private static void searchMandatoryFields(List<String> fieldNameList, File javaCodeFile, String methodName) {
        try{
            if(CommUtil.isNotNull(javaCodeFile)){
                List<MethodCallExpr> methodCallExprList = JavaCodeParser.findMethodCallExprList(StaticJavaParser.parse(javaCodeFile), methodName);
                for(MethodCallExpr methodCallExpr : methodCallExprList){
                    /** 检查类方法 **/
                    String callName = methodCallExpr.getNameAsString().toLowerCase();
                    if(callName.contains(CHECK_KEY[0]) || callName.contains(CHECK_KEY[1])){
                        if(methodCallExpr.getScope().isPresent()){
                            searchMandatoryFields(fieldNameList, OdbFactory.searchFile(methodCallExpr.getScope().get().toString() + SdtConst.JAVA_SUFFIX), methodCallExpr.getNameAsString());
                        }else{
                            searchMandatoryFields(fieldNameList, javaCodeFile, methodCallExpr.getNameAsString());
                        }
                    }
                    /** 必输类方法 **/
                    else if(methodCallExpr.getScope().isPresent() && CommUtil.equals(methodCallExpr.getScope().get().toString(), MANDATORY_STMT[0]) && CommUtil.equals(methodCallExpr.getNameAsString(), MANDATORY_STMT[1])){
                        String expr = methodCallExpr.getArgument(1).toString();
                        if(expr.contains(".")){
                            fieldNameList.add(expr.split("\\.")[2]);
                        }
                    }
                }
            }
        } catch (Exception e){
            throw new SdtException("Failed to search for mandatory fields", e);
        }
    }
}