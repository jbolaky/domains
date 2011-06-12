package com.javaid.bolaky.domain.hibernate.interceptor.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.CallbackException;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

public class ChainedInterceptor implements Interceptor, Serializable {
	private static final long serialVersionUID = -4493683654287275034L;
	private Interceptor[] interceptors;

	public void setInterceptors(Interceptor[] interceptors) {
		this.interceptors = interceptors;
	}

	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) throws CallbackException {
		boolean result = false;
		for (int i = 0; i < this.interceptors.length; ++i) {
			if (this.interceptors[i].onLoad(entity, id, state, propertyNames,
					types)) {
				result = true;
			}
		}
		return result;
	}

	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) throws CallbackException {
		boolean result = false;
		for (int i = 0; i < this.interceptors.length; ++i) {
			if (this.interceptors[i].onFlushDirty(entity, id, currentState,
					previousState, propertyNames, types)) {
				result = true;
			}
		}
		return result;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) throws CallbackException {
		boolean result = false;
		for (int i = 0; i < this.interceptors.length; ++i) {
			if (!(this.interceptors[i].onSave(entity, id, state, propertyNames,
					types))) {
				continue;
			}

			result = true;
		}

		return result;
	}

	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) throws CallbackException {
		for (int i = 0; i < this.interceptors.length; ++i)
			this.interceptors[i].onDelete(entity, id, state, propertyNames,
					types);
	}

	public void onCollectionRecreate(Object collection, Serializable key)
			throws CallbackException {
		for (int i = 0; i < this.interceptors.length; ++i)
			this.interceptors[i].onCollectionRecreate(collection, key);
	}

	public void onCollectionRemove(Object collection, Serializable key)
			throws CallbackException {
		for (int i = 0; i < this.interceptors.length; ++i)
			this.interceptors[i].onCollectionRemove(collection, key);
	}

	public void onCollectionUpdate(Object collection, Serializable key)
			throws CallbackException {
		for (int i = 0; i < this.interceptors.length; ++i)
			this.interceptors[i].onCollectionUpdate(collection, key);
	}

	public void preFlush(@SuppressWarnings("rawtypes") Iterator entities)
			throws CallbackException {
		@SuppressWarnings("rawtypes")
		List entityList = createList(entities);
		for (int i = 0; i < this.interceptors.length; ++i)
			this.interceptors[i].preFlush(entityList.iterator());
	}

	public void postFlush(@SuppressWarnings("rawtypes") Iterator entities)
			throws CallbackException {
		@SuppressWarnings("rawtypes")
		List entityList = createList(entities);
		for (int i = 0; i < this.interceptors.length; ++i)
			this.interceptors[i].postFlush(entityList.iterator());
	}

	public Boolean isTransient(Object entity) {
		Boolean result = null;
		for (int i = 0; i < this.interceptors.length; ++i) {
			result = this.interceptors[i].isTransient(entity);
			if (result != null) {
				break;
			}
		}
		return result;
	}

	public int[] findDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		int[] result = null;
		for (int i = 0; i < this.interceptors.length; ++i) {
			result = this.interceptors[i].findDirty(entity, id, currentState,
					previousState, propertyNames, types);
			if (result != null) {
				break;
			}
		}
		return result;
	}

	public Object instantiate(String clazzName, EntityMode entityMode,
			Serializable id) throws CallbackException {
		Object result = null;
		for (int i = 0; i < this.interceptors.length; ++i) {
			result = this.interceptors[i]
					.instantiate(clazzName, entityMode, id);
			if (result != null) {
				break;
			}
		}
		return result;
	}

	public String getEntityName(Object object) {
		String result = null;
		for (int i = 0; i < this.interceptors.length; ++i) {
			result = this.interceptors[i].getEntityName(object);
			if (result != null) {
				break;
			}
		}
		return result;
	}

	public Object getEntity(String entityName, Serializable id)
			throws CallbackException {
		Object result = null;
		for (int i = 0; i < this.interceptors.length; ++i) {
			result = this.interceptors[i].getEntity(entityName, id);
			if (result != null) {
				break;
			}
		}
		return result;
	}

	public void afterTransactionBegin(Transaction tx) {
		for (int i = 0; i < this.interceptors.length; ++i)
			this.interceptors[i].afterTransactionBegin(tx);
	}

	public void beforeTransactionCompletion(Transaction tx) {
		for (int i = 0; i < this.interceptors.length; ++i)
			this.interceptors[i].beforeTransactionCompletion(tx);
	}

	public void afterTransactionCompletion(Transaction tx) {
		for (int i = 0; i < this.interceptors.length; ++i)
			this.interceptors[i].afterTransactionCompletion(tx);
	}

	public String onPrepareStatement(String sql) {
		String result = null;
		for (int i = 0; i < this.interceptors.length; ++i) {
			result = this.interceptors[i].onPrepareStatement(sql);
			if (result != null) {
				break;
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private List<?> createList(@SuppressWarnings("rawtypes") Iterator iterator) {
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}
}
