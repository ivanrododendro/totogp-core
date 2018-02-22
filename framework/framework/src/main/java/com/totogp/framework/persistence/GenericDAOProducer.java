package com.totogp.framework.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.totogp.framework.annotation.GenericDAO;
import com.totogp.framework.annotation.SpecificLiteral;

//http://stackoverflow.com/questions/29029827/cdi-genericdao-and-custom-implementations/33724756#33724756

@SuppressWarnings("serial")
@ApplicationScoped
public class GenericDAOProducer implements Serializable {

  private static Logger logger = LoggerFactory.getLogger(GenericDAOProducer.class);

  // TODO Vérifier si ici on ne ralenti pas trop, du fait qu'on instancie, je
  // pense, tout les DAOs spécifques. Si c'est le cas déplacer l'instanciation
  // dans
  // le corps de la méthode.
  // TODO ici l'injection et la création des DAOs risque de se faire souvent, à
  // vérifier et à étudier le meilleur scope à tuliser
  @Produces
  @Dependent
  public <T, PK> DAO<T, PK> producesDAO(
      final InjectionPoint injectionPoint,
      @GenericDAO final GenericDAOI<T, PK> genericDAO,
      @Any Instance<DAO<T, PK>> specDAOInstance) {

    // JPA Class (T)
    final ParameterizedType type = (ParameterizedType) injectionPoint.getType();
    final Class<T> entityClass = (Class) type.getActualTypeArguments()[0];

    logger.debug("Searching DAO ", entityClass);

    // Search specific DAO
    specDAOInstance = specDAOInstance.select(new SpecificLiteral(entityClass));

    if ((specDAOInstance != null) && !specDAOInstance.isAmbiguous() && !specDAOInstance.isUnsatisfied()) {
      final DAO<T, PK> specificDAO = specDAOInstance.get();

      logger.debug("Implementation found for : ", entityClass, ", ", specificDAO.getClass());

      return specificDAO;
    } else {
      logger.debug("Implementation not found, returning generic DAO for : ", entityClass);

      genericDAO.setEntityClass(entityClass);

      return genericDAO;
    }
  }
}
