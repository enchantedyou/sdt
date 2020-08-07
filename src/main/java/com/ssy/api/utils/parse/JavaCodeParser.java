package com.ssy.api.utils.parse;

import com.github.javaparser.Range;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.utils.system.CommUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description java代码解析器
 * @Author sunshaoyu
 * @Date 2020年08月03日-16:41
 */
public class JavaCodeParser {

    /**
     * @Description 查找到指定的方法声明节点
     * @Author sunshaoyu
     * @Date 2020/8/3-17:30
     * @param unit
     * @param methodName
     * @return com.github.javaparser.ast.body.MethodDeclaration
     */
    public static MethodDeclaration findMethodDeclaration(CompilationUnit unit, String methodName){
        List<MethodDeclaration> methodDeclarationList = unit.findAll(MethodDeclaration.class);
        for(MethodDeclaration m : methodDeclarationList){
            if(CommUtil.equals(m.getNameAsString(), methodName)){
                return m;
            }
        }
        throw SdtServError.E0019(methodName);
    }

    /**
     * @Description 查找方法调用表达式列表
     * @Author sunshaoyu
     * @Date 2020/8/5-10:11
     * @param unit
     * @param methodName
     * @return java.util.List<com.github.javaparser.ast.expr.MethodCallExpr>
     */
    public static List<MethodCallExpr> findMethodCallExprList(CompilationUnit unit, String methodName){
        MethodDeclaration methodDeclaration = findMethodDeclaration(unit, methodName);
        List<MethodCallExpr> methodCallExprList = unit.findAll(MethodCallExpr.class);
        List<MethodCallExpr> resultList = new ArrayList<>();
        
        for(MethodCallExpr m : methodCallExprList){
            if(lineIn(m.getRange().get(), methodDeclaration.getRange().get())){
                resultList.add(m);
            }
        }
        return resultList;
    }

    /**
     * @Description 检查内部范围是否在外部范围内
     * @Author sunshaoyu
     * @Date 2020/8/5-9:49
     * @param inner
     * @param outer
     * @return boolean
     */
    private static boolean lineIn(Range inner, Range outer){
        return CommUtil.compare(inner.begin.line, outer.begin.line) >= 0 && CommUtil.compare(inner.end.line, outer.end.line) <= 0;
    }
}
