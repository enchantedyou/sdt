package com.ssy.api.plugins;

import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.exception.SdtException;
import com.ssy.api.utils.system.CommUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description 批量任务扫描器
 * @Author sunshaoyu
 * @Date 2020年07月04日-21:34
 */
@Component
public class SdtBatchScanner {

    private static SdtContextConfig contextConfig;
    @Autowired
    public void setSdtContextConfig(SdtContextConfig contextConfig) {
        SdtBatchScanner.contextConfig = contextConfig;
    }

    /**
     * 批量任务执行进行扫描器
     * 存放单个日终批量任务执行组(例如日切 10-Switch)的所有子系统(100,102,103,201...)的执行扫描线程
     */
    private static volatile ExecutorService batchTaskScanner;

    /** 未来化线程列表,捕获批量的执行结果 **/
    private static final List<Future<Boolean>> batchFutureList = new ArrayList<>();

    /**
     * @Description 获取批量扫描线程池实例
     * @Author sunshaoyu
     * @Date 2020/7/4-22:19
     * @return java.util.concurrent.ExecutorService
     */
    protected static ExecutorService getInstance(){
        if(null == batchTaskScanner){
            synchronized (ExecutorService.class){
                if(null == batchTaskScanner){
                    batchTaskScanner = Executors.newFixedThreadPool(contextConfig.getBatchPollThreadPoolSize());
                }
            }
        }
        return batchTaskScanner;
    }

    /**
     * @Description 提交批量任务
     * @Author sunshaoyu
     * @Date 2020/7/5-19:50
     * @param task
     * @return java.util.concurrent.Future<T>
     */
    public static void submitTask(Callable<Boolean> task){
        batchFutureList.add(getInstance().submit(task));
    }

    /**
     * @Description 检查当前组各个子系统的的批量任务是否正常执行完成
     * @Author sunshaoyu
     * @Date 2020/7/5-20:50
     * @return boolean
     */
    public static boolean checkCurrentCallResult(){
        try {
            for(Future<Boolean> future : batchFutureList){
                if(!future.get()){
                    return false;
                }
            }
        }catch (Exception e){
            throw new SdtException(e.getCause());
        }finally {
            batchFutureList.clear();
        }
        return true;
    }

    /**
     * @Description 是否存在正在执行的批量任务
     * @Author sunshaoyu
     * @Date 2020/7/15-14:43
     * @return boolean
     */
    public static boolean isExistBatchTask(){
        return CommUtil.isNotNull(batchFutureList);
    }
}
