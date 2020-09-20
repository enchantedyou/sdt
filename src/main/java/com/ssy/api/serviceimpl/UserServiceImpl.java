package com.ssy.api.serviceimpl;

import com.ssy.api.dao.mapper.local.SdbUserMapper;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.session.UserInfo;
import com.ssy.api.entity.table.local.SdbUser;
import com.ssy.api.entity.type.local.SdLoginIn;
import com.ssy.api.entity.type.local.SdLoginOut;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.servicetype.UserService;
import com.ssy.api.utils.http.HttpServletUtil;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import com.ssy.api.utils.system.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * @Description 用户相关服务的实现
 * @Author sunshaoyu
 * @Date 2020年07月14日-11:28
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {

    @Autowired
    private SdbUserMapper userMapper;

    @Override
    public SdLoginOut login(SdLoginIn loginIn) {
        BizUtil.fieldNotNull(loginIn.getUserAcct(), SdtDict.A.user_acct.getId(), SdtDict.A.user_acct.getLongName());
        BizUtil.fieldNotNull(loginIn.getUserPwd(), SdtDict.A.user_pwd.getId(), SdtDict.A.user_pwd.getLongName());
        //BizUtil.fieldNotNull(loginIn.getDatasourceId(), SdtDict.A.datasource_id.getId(), SdtDict.A.datasource_id.getLongName());

        SdbUser user = userMapper.selectByPrimaryKey(loginIn.getUserAcct());
        if(CommUtil.isNull(user)){
            //用户名不存在
            throw SdtServError.E0010(loginIn.getUserAcct());
        }else if(!CommUtil.equals(user.getUserPwd(), loginIn.getUserPwd())){
            //密码错误
            throw SdtServError.E0011(loginIn.getUserAcct());
        }else{
            //设置session信息
            HttpSession session = SpringContextUtil.getRequest().getSession();
            session.setAttribute(SdtConst.USER_INFO, new UserInfo(user.getUserAcct(), loginIn.getDatasourceId()));
            session.setMaxInactiveInterval(SdtConst.userMaxInactiveInterval);

            //更新用户信息
            String requestIp = HttpServletUtil.getRemoteHostAddr(SpringContextUtil.getRequest());
            user.setLoginIp(requestIp);
            user.setLoginTime(BizUtil.getRunEnvs().getRequestStartTime());
            userMapper.updateByPrimaryKey(user);

            return new SdLoginOut(BizUtil.getRunEnvs().getRequestStartTime(), requestIp);
        }
    }

    @Override
    public void logout() {
        HttpSession session = SpringContextUtil.getRequest().getSession();
        session.removeAttribute(SdtConst.USER_INFO);
    }
}
