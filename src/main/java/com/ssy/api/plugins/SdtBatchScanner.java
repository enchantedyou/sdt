package com.ssy.api.plugins;

import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.enums.E_BATCHEXESTATUS;
import com.ssy.api.entity.type.edsp.SdCallBatchOut;
import com.ssy.api.utils.CommUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description 批量任务扫描器
 * @Author sunshaoyu
 * @Date 2020年07月04日-21:34
 */
public class SdtBatchScanner {

    private static SdtContextConfig contextConfig;
    @Autowired
    public void setContextConfig(SdtContextConfig contextConfig) {
        SdtBatchScanner.contextConfig = contextConfig;
    }

    /**
     * 批量任务执行进行扫描器
     * 存放单个日终批量任务执行组(例如日切 10-Switch)的所有子系统(100,102,103,201...)的执行扫描线程
     */
    private static final ExecutorService batchTaskScanner = Executors.newFixedThreadPool(contextConfig.getBatchPollThreadPoolSize());

    /** 未来化线程列表,捕获批量的执行结果 **/
    private static final List<Future<SdCallBatchOut>> batchFutureList = new ArrayList<>();

    /**
     * @Description 获取批量扫描线程池实例
     * @Author sunshaoyu
     * @Date 2020/7/4-22:19
     * @return java.util.concurrent.ExecutorService
     */
    @Deprecated
    public static ExecutorService getInstance(){
        return SdtBatchScanner.batchTaskScanner;
    }

    /**
     * @Description 提交批量任务
     * @Author sunshaoyu
     * @Date 2020/7/5-19:50
     * @param task
     * @return java.util.concurrent.Future<T>
     */
    public static void submitTask(Callable<SdCallBatchOut> task){
        batchFutureList.add(batchTaskScanner.submit(task));
    }

    /**
     * @Description 等待线程池的任务执行完成
     * @Author sunshaoyu
     * @Date 2020/7/5-19:51
     * @param threadPool
     * @param timeout
     * @return boolean  正常完成返回true,超时返回false
     */
    public static boolean awaitThreadPoolFinish(ExecutorService threadPool, long timeout){
        threadPool.shutdown();
        long start = System.currentTimeMillis();
        while(!threadPool.isTerminated()){
            if(timeout != 0 && (System.currentTimeMillis() - start) > timeout){
                threadPool.shutdownNow();
                return false;
            }
        }
        return true;
    }

    /**
     * @Description 检查当前组各个子系统的的批量任务是否正常执行完成
     * @Author sunshaoyu
     * @Date 2020/7/5-20:50
     * @return boolean
     */
    public static boolean checkCurrentCallResult(){
        awaitThreadPoolFinish(batchTaskScanner, 0);
        for(Future<SdCallBatchOut> future : batchFutureList){
            try {
                SdCallBatchOut callBatchOut = future.get();
                if(CommUtil.isNotNull(callBatchOut.getErrorMessage()) || callBatchOut.getBatchExeStatus() != E_BATCHEXESTATUS.success){
                    return false;
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to obtain batch thread execution result", e);
            }
        }
        return true;
    }
}
