package com.ssy.api.serviceimpl;

import com.github.pagehelper.PageInfo;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.enums.E_RESTRICTION;
import com.ssy.api.entity.type.local.SdSearchDictOut;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.meta.defaults.DefaultEnumerationType;
import com.ssy.api.meta.defaults.Element;
import com.ssy.api.servicetype.MetaService;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 元数据相关服务实现
 * @Author sunshaoyu
 * @Date 2020年07月16日-13:40
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class MetaServiceImpl implements MetaService {

    @Override
    public PageInfo<SdSearchDictOut> queryDictListFuzzy(String key) {
        BizUtil.fieldNotNull(key, SdtDict.A.key.getId(), SdtDict.A.key.getLongName());
        key = key.toLowerCase();
        Map<String, Element> dictMap = OdbFactory.getDictMap();
        List<SdSearchDictOut> dictList = new ArrayList<>();

        for(String k : dictMap.keySet()){
            Element e = dictMap.get(k);
            if(CommUtil.isNotNull(e.getType())
                    && (e.getId().contains(key)
                    || (CommUtil.isNotNull(e.getDesc()) && e.getDesc().contains(key))
                    || (CommUtil.isNotNull(e.getLongName()) && e.getLongName().contains(key)))){

                StringBuilder builder = new StringBuilder();
                if(e.getType().getRestriction() == E_RESTRICTION.BASETYPE){
                    builder.append(e.getType().getFullId());
                }else{
                    Map<String, DefaultEnumerationType> map = e.getType().getEnumerationMap();
                    builder.append(e.getType().getFullId()).append(";");
                    for(String enumKey : map.keySet()){
                        builder.append(map.get(enumKey).getEnumSelect()).append(";");
                    }
                }
                dictList.add(new SdSearchDictOut(e.getId(), e.getRef(), e.getLongName(), e.getDesc(), builder.toString()));
            }
        }
        return BizUtil.listToPage(dictList);
    }
}
