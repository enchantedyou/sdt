package com.ssy.api.serviceimpl;

import com.ssy.api.dao.mapper.local.SdbUserMapper;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.session.UserInfo;
import com.ssy.api.entity.table.local.SdbUser;
import com.ssy.api.entity.type.local.SdLoginIn;
import com.ssy.api.entity.type.local.SdLoginOut;
import com.ssy.api.entity.type.local.SdMntUser;
import com.ssy.api.exception.ApPubErr;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.servicetype.UserService;
import com.ssy.api.utils.http.HttpServletUtil;
import com.ssy.api.utils.security.Md5Encrypt;
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

        SdbUser user = userMapper.selectByPrimaryKey(loginIn.getUserAcct(), false);
        if(CommUtil.isNull(user)){
            //用户名不存在
            throw SdtServError.E0010(loginIn.getUserAcct());
        }else if(!CommUtil.equals(user.getUserPwd(), loginIn.getUserPwd())){
            //密码错误
            throw SdtServError.E0011(loginIn.getUserAcct());
        }else{
            //检查是否被锁定
            if(CommUtil.equals(user.getLockInd(), "Y")){
                throw SdtServError.E0024(user.getUserAcct());
            }

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

    @Override
    public SdbUser register(SdbUser user) {
        BizUtil.fieldNotNull(user.getUserAcct(), SdtDict.A.user_acct.getId(), SdtDict.A.user_acct.getLongName());
        BizUtil.fieldNotNull(user.getUserPwd(), SdtDict.A.user_pwd.getId(), SdtDict.A.user_pwd.getLongName());

        SdbUser dbUser = userMapper.selectByPrimaryKey(user.getUserAcct(), false);
        if(CommUtil.isNotNull(dbUser)){
            throw SdtServError.E0025(user.getUserAcct());
        }
        user.setLockInd("N");
        user.setUserPermission("NORMAL");
        if(!CommUtil.equals(user.getUserPwd().length(), 2 << 4)){
            user.setUserPwd(Md5Encrypt.md5EncodeStr(user.getUserPwd()));
        }

        userMapper.insert(user);
        return user;
    }

    @Override
    public void modifyUserInfo(SdMntUser mntUser) {
        UserInfo userInfo = SpringContextUtil.getSessionAttribute(SdtConst.USER_INFO, UserInfo.class);
        if(CommUtil.isNull(userInfo)){
            throw SdtServError.E0026();
        }
        SdbUser user = userMapper.selectByPrimaryKey(userInfo.getUserAcct(), false);
        if(CommUtil.isNull(user)){
            //用户名不存在
            throw SdtServError.E0010(userInfo.getUserAcct());
        }else if(CommUtil.isNotNull(mntUser.getUserPwd()) && !CommUtil.equals(user.getUserPwd(), mntUser.getOriginalPwd())) {
            //密码错误
            throw SdtServError.E0011(user.getUserAcct());
        }else if(CommUtil.isNotNull(mntUser.getUserPwd()) && !CommUtil.equals(mntUser.getUserPwd(), mntUser.getConfirmPwd())){
            //两次输入的新密码不一致
            throw SdtServError.E0027();
        }

        SdbUser newUser = BizUtil.clone(SdbUser.class, user);
        boolean isPwdModify = false;
        if(CommUtil.isNotNull(mntUser.getUserPwd())){
            newUser.setUserPwd(mntUser.getUserPwd());

            int len = mntUser.getUserPwd().length();
            final int minLen = 6;
            if(len < minLen){
                throw SdtServError.E0002(len, minLen, "minimum password length");
            }
            isPwdModify = true;
        }

        if(CommUtil.isNotNull(mntUser.getGitlabSession())){
            newUser.setGitlabSession(mntUser.getGitlabSession());
        }

        int updateNum = BizUtil.auditOnUpdate(user, newUser);
        if(updateNum > 0){
            userMapper.updateByPrimaryKeySelective(newUser);
            if(isPwdModify){
                SpringContextUtil.getRequest().getSession().removeAttribute(SdtConst.USER_INFO);
            }
        }else{
            throw ApPubErr.E0004();
        }
    }
}
