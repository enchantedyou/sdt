package com.ssy.api.entity.sump.component;

import com.alibaba.fastjson.annotation.JSONField;
import com.ssy.api.entity.enums.E_PTEMODULE;

/**
 * @Description PTE组件
 * @Author sunshaoyu
 * @Date 2020年06月22日-20:29
 */
public class PTEComponent {
    @JSONField(ordinal = 0)
    private E_PTEMODULE module;
    @JSONField(ordinal = 5)
    private PTElayout layout;

    public E_PTEMODULE getModule() {
        return module;
    }

    public void setModule(E_PTEMODULE module) {
        this.module = module;
    }

    public PTElayout getLayout() {
        return layout;
    }

    public void setLayout(PTElayout layout) {
        this.layout = layout;
    }
}
