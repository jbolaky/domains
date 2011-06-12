package com.javaid.bolaky.domain.hibernate.interceptor.impl;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.event.LoadEventListener;
import org.hibernate.event.MergeEventListener;
import org.hibernate.event.PostLoadEventListener;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEventListener;
import org.hibernate.event.SaveOrUpdateEventListener;
import org.hibernate.event.def.DefaultLoadEventListener;
import org.hibernate.event.def.DefaultMergeEventListener;
import org.hibernate.event.def.DefaultPostLoadEventListener;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;

public class ConfigurableHibernatePersistence extends HibernatePersistence {
	private Interceptor interceptor;
	private LoadEventListener loadEventListener;
	private SaveOrUpdateEventListener saveOrUpdateEventListener;
	private MergeEventListener mergeEventListener;
	private PostLoadEventListener postLoadEventListener;
	private PreInsertEventListener preInsertEventListener;
	private PreUpdateEventListener preUpdateEventListener;

	public Interceptor getInterceptor() {
		return this.interceptor;
	}

	public void setInterceptor(Interceptor interceptor) {
		this.interceptor = interceptor;
	}

	public LoadEventListener getLoadEventListener() {
		return this.loadEventListener;
	}

	public void setLoadEventListener(LoadEventListener loadEventLister) {
		this.loadEventListener = loadEventLister;
	}

	public SaveOrUpdateEventListener getSaveOrUpdateEventListener() {
		return this.saveOrUpdateEventListener;
	}

	public void setSaveOrUpdateEventListener(
			SaveOrUpdateEventListener saveOrUpdateEventListener) {
		this.saveOrUpdateEventListener = saveOrUpdateEventListener;
	}

	public MergeEventListener getMergeEventListener() {
		return this.mergeEventListener;
	}

	public void setMergeEventListener(MergeEventListener mergeEventListener) {
		this.mergeEventListener = mergeEventListener;
	}

	public void setPostLoadEventListener(
			PostLoadEventListener postLoadEventListener) {
		this.postLoadEventListener = postLoadEventListener;
	}

	public PostLoadEventListener getPostLoadEventListener() {
		return this.postLoadEventListener;
	}

	public void setPreInsertEventListener(
			PreInsertEventListener preInsertEventListener) {
		this.preInsertEventListener = preInsertEventListener;
	}

	public PreInsertEventListener getPreInsertEventListener() {
		return this.preInsertEventListener;
	}

	public void setPreUpdateEventListener(
			PreUpdateEventListener preUpdateEventListener) {
		this.preUpdateEventListener = preUpdateEventListener;
	}

	public PreUpdateEventListener getPreUpdateEventListener() {
		return this.preUpdateEventListener;
	}

	public EntityManagerFactory createContainerEntityManagerFactory(
			PersistenceUnitInfo info, @SuppressWarnings("rawtypes") Map map) {
		Ejb3Configuration cfg = new Ejb3Configuration();
		Ejb3Configuration configured = cfg.configure(info, map);
		postprocessConfiguration(info, map, configured);
		return ((configured != null) ? configured.buildEntityManagerFactory()
				: null);
	}

	protected void postprocessConfiguration(PersistenceUnitInfo info,
			@SuppressWarnings("rawtypes") Map map, Ejb3Configuration configured) {
		if (this.interceptor != null) {
			if ((configured.getInterceptor() == null)
					|| (EmptyInterceptor.class.equals(configured
							.getInterceptor().getClass()))) {
				configured.setInterceptor(this.interceptor);
			} else
				throw new IllegalStateException(
						"Hibernate interceptor already set in persistence.xml ("
								+ configured.getInterceptor() + ")");

		}

		if (getLoadEventListener() != null) {
			LoadEventListener[] lel = { new DefaultLoadEventListener(),
					getLoadEventListener() };
			configured.getEventListeners().setLoadEventListeners(lel);
		}
		if (getSaveOrUpdateEventListener() != null) {
			SaveOrUpdateEventListener[] lel = {
					new DefaultSaveOrUpdateEventListener(),
					getSaveOrUpdateEventListener() };
			configured.getEventListeners().setSaveOrUpdateEventListeners(lel);
		}
		if (getMergeEventListener() != null) {
			MergeEventListener[] el = { new DefaultMergeEventListener(),
					getMergeEventListener() };
			configured.getEventListeners().setMergeEventListeners(el);
		}
		if (getPostLoadEventListener() != null) {
			PostLoadEventListener[] el = { new DefaultPostLoadEventListener(),
					getPostLoadEventListener() };
			configured.getEventListeners().setPostLoadEventListeners(el);
		}
		if (getPreInsertEventListener() != null) {
			PreInsertEventListener[] el = { getPreInsertEventListener() };
			configured.getEventListeners().setPreInsertEventListeners(el);
		}
		if (getPreUpdateEventListener() != null) {
			PreUpdateEventListener[] el = { getPreUpdateEventListener() };
			configured.getEventListeners().setPreUpdateEventListeners(el);
		}
	}
}