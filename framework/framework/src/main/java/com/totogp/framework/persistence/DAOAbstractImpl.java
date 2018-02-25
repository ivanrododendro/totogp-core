package com.totogp.framework.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import com.totogp.framework.exception.TechnicalException;

@SuppressWarnings("serial")
public abstract class DAOAbstractImpl<T, PK> implements DAO<T, PK> {

	@PersistenceContext
	protected EntityManager em;

	protected Class<T> entityClass;

	@Override
	public T find(final PK key) {
		return em.find(entityClass, key);
	}

	/**
	 * Cette méthode remonte la liste complète des entités
	 *
	 * @return la liste des entités
	 */
	@Override
	public List<T> findAll() {
		final Query query = em.createQuery("select t from " + entityClass.getSimpleName() + " t ");

		query.setHint("org.hibernate.cacheable", true);
		query.setHint("org.hibernate.cacheMode", "NORMAL");

		@SuppressWarnings("unchecked")
		final List<T> list = query.getResultList();

		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected final Class<T> getEntityClass() {
		if (this.entityClass == null) {
			this.entityClass = ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass())
					.getActualTypeArguments()[0]);
		}
		return this.entityClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getSingleResult(final String queryName, final Object... params) {
		final Query query = em.createNamedQuery(queryName);
		int position = 1;

		for (final Object param : params) {
			query.setParameter(position++, param);
		}

		try {
			return (T) query.getSingleResult();
		} catch (final Exception e) {
			return null;
		}
	}

	@Override
	public T make() {
		try {
			return entityClass.newInstance();
		} catch (final Exception e) {
			throw new TechnicalException("erreur pendant la création de l'entité : " + entityClass.getClass().getName(),
					e);
		}
	}

	@Override
	public T merge(final T arg0) {
		return em.merge(arg0);
	}

	@Override
	public T persist(final T entity) {
		em.persist(entity);

		return entity;
	}

	@Override
	public void refresh(final T entity) {
		em.refresh(entity);
	}

	@Override
	public void refresh(final T entity, final LockModeType lockModeType) {
		em.refresh(entity, lockModeType);
	}

	@Override
	public void remove(final T entity) {
		em.remove(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> runQuery(final String queryName, final Object... params) {
		final Query query = em.createNamedQuery(queryName);
		int position = 1;

		if (params != null) {
			for (final Object param : params) {
				query.setParameter(position++, param);
			}
		}

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public T unproxy(T entity) {
		if (entity == null) {
			return null;
		}

		Hibernate.initialize(entity);

		if (entity instanceof HibernateProxy) {
			entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
		}
		return entity;
	}

}