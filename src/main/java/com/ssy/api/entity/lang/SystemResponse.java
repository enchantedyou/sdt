package com.ssy.api.entity.lang;

import com.ssy.api.entity.constant.ErrCodeDef;
import com.ssy.api.entity.enums.E_STATUS;
import com.ssy.api.utils.system.CommUtil;

/**
 * @Description 系统响应区
 * @Author sunshaoyu
 * @Date 2020年07月14日-14:54
 */
public class SystemResponse {

    private String erortx;
    private String erorcd;
    private E_STATUS status;

    public SystemResponse(String erortx, String erorcd, E_STATUS status) {
        if(CommUtil.isNotNull(erortx)){
            this.erortx = CommUtil.equals(erorcd, ErrCodeDef.SUCCESS) ? erortx : String.format("[%s]%s", erorcd, erortx);
        }else{
            this.erortx = "系统错误";
        }
        this.erorcd = erorcd;
        this.status = status;
    }

    public String getErortx() {
        return erortx;
    }

    public void setErortx(String erortx) {
        this.erortx = erortx;
    }

    public String getErorcd() {
        return erorcd;
    }

    public void setErorcd(String erorcd) {
        this.erorcd = erorcd;
    }

    public E_STATUS getStatus() {
        return status;
    }

    public void setStatus(E_STATUS status) {
        this.status = status;
    }
}
