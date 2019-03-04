package com.gstart.upms.rpc.api;

import com.gstart.common.bean.Message;
import com.gstart.upms.rpc.api.pojo.User;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-15 15:33
 */
public interface SSOService {
    Message login(User user,Object auth);
}
