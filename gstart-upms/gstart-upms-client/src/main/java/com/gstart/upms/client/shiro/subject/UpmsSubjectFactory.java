package com.gstart.upms.client.shiro.subject;

import com.gstart.upms.client.shiro.token.UserToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2019-03-10 13:30
 */
public class UpmsSubjectFactory extends DefaultWebSubjectFactory {
    private final DefaultSessionStorageEvaluator storageEvaluator;

    /**
     * DefaultSessionStorageEvaluator是否持久化SESSION的开关
     */
    public UpmsSubjectFactory(DefaultSessionStorageEvaluator storageEvaluator){
        this.storageEvaluator = storageEvaluator;
    }

    public Subject createSubject(SubjectContext context) {
        this.storageEvaluator.setSessionStorageEnabled(Boolean.TRUE);
        AuthenticationToken token = context.getAuthenticationToken();
        if(token instanceof UserToken){
            // 不创建 session
            context.setSessionCreationEnabled(false);
            // 不持久化session
            this.storageEvaluator.setSessionStorageEnabled(Boolean.FALSE);
        }
        return super.createSubject(context);
    }
}
