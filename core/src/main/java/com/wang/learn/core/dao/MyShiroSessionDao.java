/**
 * MyShiroSessionDao 2017/9/11 14:04
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.wang.learn.core.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * @author gang.wang
 * @Title: MyShiroSessionDao
 * @Description: (描述此类的功能)
 * @date 2017/9/11 14:04
 */
public class MyShiroSessionDao extends CachingSessionDAO {

	private static final Logger LOG = LoggerFactory.getLogger(MyShiroSessionDao.class);
	private static final String SESSION_MANAGER_STORE = "session:SESSION_MANAGER";
	private RedisTemplate redisTemplate;

	/**
	 * Subclass implementation hook to actually persist the {@code Session}'s
	 * state to the underlying EIS.
	 *
	 * @param session
	 *            the session object whose state will be propagated to the EIS.
	 */
	@Override
	protected void doUpdate(Session session) {
		//如果会话过期/停止 没必要再更新了
		if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
			return;
		}
		HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
		try {
			hashOperations.delete(SESSION_MANAGER_STORE, session.getId().toString());
			hashOperations.put(SESSION_MANAGER_STORE, session.getId().toString(), JSON.toJSONString(session));
		} catch (Exception ex) {
			LOG.debug("update session failed beacuse:{}", ex.getMessage());
		}
	}

	/**
	 * Subclass implementation hook to permanently delete the given Session from
	 * the underlying EIS.
	 *
	 * @param session
	 *            the session instance to permanently delete from the EIS.
	 */
	@Override
	protected void doDelete(Session session) {
		HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
		hashOperations.delete(SESSION_MANAGER_STORE, session.getId().toString());
	}

	/**
	 * Subclass hook to actually persist the given <tt>Session</tt> instance to
	 * the underlying EIS.
	 *
	 * @param session
	 *            the Session instance to persist to the EIS.
	 * @return the id of the session created in the EIS (i.e. this is almost
	 *         always a primary key and should be the value returned from
	 *         {@link Session#getId() Session.getId()}.
	 */
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
		try {
			hashOperations.put(SESSION_MANAGER_STORE, session.getId().toString(), JSON.toJSONString(session));
		} catch (Exception ex) {
			LOG.debug("update session failed beacuse:{}", ex.getMessage());
		}
		return sessionId;
	}

	/**
	 * Subclass implementation hook that retrieves the Session object from the
	 * underlying EIS or {@code null} if a session with that ID could not be
	 * found.
	 *
	 * @param sessionId
	 *            the id of the <tt>Session</tt> to retrieve.
	 * @return the Session in the EIS identified by <tt>sessionId</tt> or
	 *         {@code null} if a session with that ID could not be found.
	 */
	@Override
	protected Session doReadSession(Serializable sessionId) {
		HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
		String value = hashOperations.get(SESSION_MANAGER_STORE, sessionId.toString());
		if (StringUtils.isBlank(value))
			return null;
		return JSONObject.parseObject(value, Session.class);
	}

	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public MyShiroSessionDao setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		return this;
	}
}
