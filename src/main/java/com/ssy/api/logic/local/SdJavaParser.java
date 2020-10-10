package com.ssy.api.logic.local;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.exception.SdtException;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.meta.flowtran.Flowtran;
import com.ssy.api.meta.flowtran.IntfService;
import com.ssy.api.utils.business.SdtBusiUtil;
import com.ssy.api.utils.parse.JavaCodeParser;
import com.ssy.api.utils.security.Md5Encrypt;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    public static final String MANDATORY_STMT = "BizUtil.fieldNotNull";
    /** 编译单元缓存 **/
    private static final Map<String, CompilationUnit> cplUnitMap = new ConcurrentHashMap<>();
    /** 递归深度 **/
    private static final ThreadLocal<Integer> RECURSION_DEPTH_LOCAL = new ThreadLocal<>();

    /**
     * @Description 搜索方法调用
     * @Author sunshaoyu
     * @Date 2020/8/12-11:02
     * @param javaCodeFile
     * @param superMethodName
     * @param callStatement
     * @return java.util.List<java.lang.String>
     */
    public static List<String> searchMethodCalls(File javaCodeFile, String superMethodName, String callStatement){
        List<String> callList = new ArrayList<>();
        searchMethodCalls(callList, javaCodeFile, superMethodName, null, callStatement);
        return CommUtil.uniqueList(callList);
    }

    /**
     * @Description 根据flowtran获取必输字段列表
     * @Author sunshaoyu
     * @Date 2020/8/5-13:13
     * @param flowtranId
     * @return java.util.List<java.lang.String>
     */
    public static List<String> searchMandatoryFields(String flowtranId){
        List<String> callList = new ArrayList<>();
        Flowtran flowtran = SdFlowtranParser.load(flowtranId);

        //单个服务处理
        for(IntfService service : flowtran.getServiceList()){
            String serviceId = CommUtil.nvl(service.getId(), service.getServiceName());
            searchMethodCalls(callList, OdbFactory.searchFile(String.format("%sImpl.java", SdtBusiUtil.getDotLeft(serviceId))), SdtBusiUtil.getDotRight(serviceId), null, MANDATORY_STMT);
        }
        return CommUtil.uniqueList(callList);
    }

    /**
     * @Description 搜索方法调用
     * @Author sunshaoyu
     * @Date 2020/8/12-11:03
     * @param callList
     * @param javaCodeFile
     * @param methodName
     * @param expressionNodeList
     * @param callStatement
     */
    private static void searchMethodCalls(List<String> callList, File javaCodeFile, String methodName, NodeList<Expression> expressionNodeList, String callStatement) {
        try{
            //最大递归深度
            final int maxDepth = 10;
            if(CommUtil.isNull(RECURSION_DEPTH_LOCAL.get())){
                RECURSION_DEPTH_LOCAL.set(1);
            }else{
                RECURSION_DEPTH_LOCAL.set(RECURSION_DEPTH_LOCAL.get() + 1);
                if(RECURSION_DEPTH_LOCAL.get() >= maxDepth){
                    return;
                }
            }

            if(CommUtil.isNotNull(javaCodeFile) && callStatement.contains(".")){
                CompilationUnit unit = getJavaCompilationUnit(javaCodeFile);
                MethodDeclaration methodDeclaration = JavaCodeParser.findMethodDeclaration(unit, methodName, expressionNodeList);
                List<MethodCallExpr> methodCallExprList = JavaCodeParser.findMethodCallExprList(unit, methodName, expressionNodeList);
                
                for(MethodCallExpr methodCallExpr : methodCallExprList){
                    String callName = methodCallExpr.getNameAsString().toLowerCase();
                    boolean isMandatorySearch = CommUtil.equals(callStatement, MANDATORY_STMT);
                    String matchStatement = methodCallExpr.getScope().isPresent() ? methodCallExpr.getScope().get() + "." + methodCallExpr.getNameAsString() : BizUtil.getFileRealName(javaCodeFile.getName() + "." + methodCallExpr.getNameAsString());

                    /** 方法匹配 **/
                    if(CommUtil.equals(callStatement, matchStatement)){
                        if(isMandatorySearch){
                            String expr = methodCallExpr.getArgument(1).toString();
                            if(expr.contains(".")){
                                callList.add(expr.split("\\.")[2]);
                            }
                        }else{
                            callList.add("["+BizUtil.getFileRealName(javaCodeFile.getName())+"][" + methodCallExpr.toString() + "][" + methodCallExpr.getRange().toString() + "]");
                        }
                    }
                    /** 检查类方法 **/
                    else {
                        if(methodCallExpr.getScope().isPresent()){
                            searchMethodCalls(callList, OdbFactory.searchFile(methodCallExpr.getScope().get().toString() + SdtConst.JAVA_SUFFIX), methodCallExpr.getNameAsString(), methodCallExpr.getArguments(), callStatement);
                        }else{
                            searchMethodCalls(callList, javaCodeFile, methodCallExpr.getNameAsString(), methodCallExpr.getArguments(), callStatement);
                        }
                    }
                }
            }
        } catch (Exception e){
            throw new SdtException("Failed to search for mandatory fields", e);
        }
    }

    /**
     * @Description 获取java编译单元,优先从缓存获取
     * @Author sunshaoyu
     * @Date 2020/8/12-11:27
     * @param javaCodeFile
     * @return com.github.javaparser.ast.CompilationUnit
     */
    private static CompilationUnit getJavaCompilationUnit(File javaCodeFile) throws IOException, NoSuchAlgorithmException {
        String md5 = Md5Encrypt.md5EncodeFile(javaCodeFile);
        CompilationUnit unit = cplUnitMap.get(md5);
        if(CommUtil.isNull(unit)){
            unit = StaticJavaParser.parse(javaCodeFile);
            cplUnitMap.put(md5, unit);
        }
        return unit;
    }
}