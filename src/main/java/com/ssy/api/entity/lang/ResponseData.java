package com.ssy.api.entity.lang;

import com.ssy.api.entity.constant.ErrCodeDef;
import com.ssy.api.entity.enums.E_STATUS;
import com.ssy.api.exception.SdtException;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;

import java.io.Serializable;

/**
 * @Description 响应数据
 * @Author sunshaoyu
 * @Date 2020年07月13日-13:09
 */
public class ResponseData implements Serializable {

    private SystemResponse sys;
    private CommonResponse commRes;
    private Object output;

    /**
     * @Description 封装成功的响应(有返回数据)
     * @Author sunshaoyu
     * @Date 2020/7/13-14:06
     */
    public ResponseData(Object output) {
        this.output = CommUtil.nvl(output, new Object());
        this.sys = new SystemResponse(null, ErrCodeDef.SUCCESS, E_STATUS.S);
        this.commRes = new CommonResponse(BizUtil.getRunEnvs().getTrxnSeq(), BizUtil.getCurSysTime());
    }

    /**
     * @Description 封装成功的响应(无返回数据)
     * @Author sunshaoyu
     * @Date 2020/7/13-14:06
     */
    public ResponseData() {
        this.output = new Object();
        this.sys = new SystemResponse(null, ErrCodeDef.SUCCESS, E_STATUS.S);
        this.commRes = new CommonResponse(BizUtil.getRunEnvs().getTrxnSeq(), BizUtil.getCurSysTime());
    }

    /**
     * @Description 封装失败的响应
     * @Author sunshaoyu
     * @Date 2020/7/13-14:10
     */
    public ResponseData(Throwable e) {
        if(e instanceof SdtException){
            this.sys = new SystemResponse(((SdtException) e).getErrorMsg(), ((SdtException) e).getErrorCode(), E_STATUS.F);
        }else{
            this.sys = new SystemResponse(e.getMessage(), ErrCodeDef.UNKNOWN_ERROR, E_STATUS.F);
        }
        this.commRes = new CommonResponse(BizUtil.getRunEnvs().getTrxnSeq(), BizUtil.getCurSysTime());
        this.output = new Object();
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }

    public SystemResponse getSys() {
        return sys;
    }

    public void setSys(SystemResponse sys) {
        this.sys = sys;
    }

    public CommonResponse getCommRes() {
        return commRes;
    }

    public void setCommRes(CommonResponse commRes) {
        this.commRes = commRes;
    }
}
