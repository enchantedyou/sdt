package com.ssy.api.servicetype;

import java.io.IOException;

/**
 * @Description 回调
 * @Author sunshaoyu
 * @Date 2020/8/12-15:39
 */
public interface Callback {

    public Object run() throws IOException;
}
