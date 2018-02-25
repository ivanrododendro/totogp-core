package com.totogp.framework.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.LockModeType;

public interface DAO<T, PK> extends Serializable {

	public T find(PK key);

	public List<T> findAll();

	public T getSingleResult(String queryName, final Object... params);

	public T make();

	public T merge(T arg0);

	public T persist(T entity);

	public void refresh(T entity);

	public void refresh(T entity, LockModeType arg1);

	public void remove(T entity);

	public List<T> runQuery(String queryName, final Object... params);

	public T unproxy(T proxied);

}
