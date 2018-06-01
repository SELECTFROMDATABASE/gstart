package com.gstart.upms.client.shiro.session;

import com.gstart.common.util.RedisFactory;
import com.gstart.common.util.SerializableUtil;
import com.gstart.upms.common.constant.UpmsConstant;
import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.Set;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-19 16:47
 */
public class UpmsSessionDao extends CachingSessionDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsSessionDao.class);
    // 会话key
    private final static String GSTART_UPMS_SHIRO_SESSION_ID = "gstart-upms-shiro-session-id";
    // 全局会话key
    private final static String GSTART_UPMS_SERVER_SESSION_ID = "gstart-upms-server-session-id";
    // 全局会话列表key
    private final static String GSTART_UPMS_SERVER_SESSION_IDS = "gstart-upms-server-session-ids";
    // code key
    private final static String GSTART_UPMS_SERVER_CODE = "gstart-upms-server-code";
    // 局部会话key
    private final static String GSTART_UPMS_CLIENT_SESSION_ID = "gstart-upms-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String GSTART_UPMS_CLIENT_SESSION_IDS = "gstart-upms-client-session-ids";
    @Override
    protected void doUpdate(Session session) {
        // 如果会话过期/停止 没必要再更新了
        System.out.println(this.getClass() +" ----------------- doUpdate");
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return;
        }
        // 更新session的最后一次访问时间
        UpmsSession upmsSession = (UpmsSession) session;
        UpmsSession cacheUpmsSession = (UpmsSession) doReadSession(session.getId());
        if (null != cacheUpmsSession) {
            upmsSession.setStatus(cacheUpmsSession.getStatus());
            upmsSession.setAttribute("FORCE_LOGOUT", cacheUpmsSession.getAttribute("FORCE_LOGOUT"));
        }
        RedisFactory.set(GSTART_UPMS_SHIRO_SESSION_ID + "_" + session.getId(), SerializableUtil.serialize(session),(int) session.getTimeout() / 1000);
        // 更新GSTART_UPMS_SERVER_SESSION_ID、GSTART_UPMS_SERVER_CODE过期时间 TODO
        LOGGER.info("doUpdate >>>>> sessionId={}", session.getId());
    }

    @Override
    protected void doDelete(Session session) {
        System.out.println(this.getClass() +" ----------------- doDelete");
        String sessionId = session.getId().toString();
        String upmsType = ObjectUtils.toString(session.getAttribute(UpmsConstant.UPMS_TYPE));
        if ("client".equals(upmsType)) {
            // 删除局部会话和同一code注册的局部会话
            String code = RedisFactory.get(GSTART_UPMS_CLIENT_SESSION_ID + "_" + sessionId);
            RedisFactory.del(GSTART_UPMS_CLIENT_SESSION_ID + "_" + sessionId);
            RedisFactory.srem(GSTART_UPMS_CLIENT_SESSION_IDS + "_" + code, sessionId);
        }
        if ("server".equals(upmsType)) {
            // 当前全局会话code
            String code = RedisFactory.get(GSTART_UPMS_SERVER_SESSION_ID + "_" + sessionId);
            // 清除全局会话
            RedisFactory.del(GSTART_UPMS_SERVER_SESSION_ID + "_" + sessionId);
            // 清除code校验值
            RedisFactory.del(GSTART_UPMS_SERVER_CODE + "_" + code);
            // 清除所有局部会话
            Set<String> clientSessionIds = RedisFactory.smembers(GSTART_UPMS_CLIENT_SESSION_IDS + "_" + code);
            for (String clientSessionId : clientSessionIds) {
                RedisFactory.del(GSTART_UPMS_CLIENT_SESSION_ID + "_" + clientSessionId);
                RedisFactory.srem(GSTART_UPMS_CLIENT_SESSION_IDS + "_" + code, clientSessionId);
            }
            LOGGER.info("当前code={}，对应的注册系统个数：{}个", code, RedisFactory.scard(GSTART_UPMS_CLIENT_SESSION_IDS + "_" + code));
            // 维护会话id列表，提供会话分页管理
            RedisFactory.lrem(GSTART_UPMS_SERVER_SESSION_IDS, 1, sessionId);
        }
        // 删除session
        RedisFactory.del(GSTART_UPMS_SHIRO_SESSION_ID + "_" + sessionId);
        LOGGER.info("doUpdate >>>>> sessionId={}", sessionId);

    }

    @Override
    protected Serializable doCreate(Session session) {
        System.out.println(this.getClass() +" ----------------- doCreate");
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        RedisFactory.set(GSTART_UPMS_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(session),(int) session.getTimeout() / 1000);
        LOGGER.info("doCreate >>>>> sessionId={}", sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println(this.getClass() +" ----------------- doReadSession");
        String session = RedisFactory.get(GSTART_UPMS_SHIRO_SESSION_ID + "_" + sessionId);
        LOGGER.info("doReadSession >>>>> sessionId={}", sessionId);
        return SerializableUtil.deserialize(session);
    }
}
