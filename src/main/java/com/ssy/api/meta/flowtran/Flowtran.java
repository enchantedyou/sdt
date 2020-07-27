package com.ssy.api.meta.flowtran;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.enums.E_TRANKIND;
import com.ssy.api.meta.abstracts.AbstractMetaData;

import java.util.List;

/**
 * @Description 交易流
 * @Author sunshaoyu
 * @Date 2020年07月22日-17:15
 */
public class Flowtran extends AbstractMetaData {

    private E_TRANKIND kind;
    private String tranPackage;
    private IntfAssemble input;
    private IntfAssemble output;

    private IntfAssemble property;
    private IntfAssemble printer;
    private List<IntfService> serviceList;

    public Flowtran(String location, String id, String longName) {
        super(location, id, longName, SdtConst.FLOWTRAN_SUFFIX);
    }

    public E_TRANKIND getKind() {
        return kind;
    }

    public void setKind(E_TRANKIND kind) {
        this.kind = kind;
    }

    public String getTranPackage() {
        return tranPackage;
    }

    public void setTranPackage(String tranPackage) {
        this.tranPackage = tranPackage;
    }

    public IntfAssemble getInput() {
        return input;
    }

    public void setInput(IntfAssemble input) {
        this.input = input;
    }

    public IntfAssemble getOutput() {
        return output;
    }

    public void setOutput(IntfAssemble output) {
        this.output = output;
    }

    public IntfAssemble getProperty() {
        return property;
    }

    public void setProperty(IntfAssemble property) {
        this.property = property;
    }

    public IntfAssemble getPrinter() {
        return printer;
    }

    public void setPrinter(IntfAssemble printer) {
        this.printer = printer;
    }

    public List<IntfService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<IntfService> serviceList) {
        this.serviceList = serviceList;
    }
}
