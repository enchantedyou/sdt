package com.ssy.api.serviceimpl;

import com.ssy.api.servicetype.SumpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description sump相关服务实现
 * @Author sunshaoyu
 * @Date 2020年06月22日-20:34
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class SumpServiceImpl implements SumpService {
}
