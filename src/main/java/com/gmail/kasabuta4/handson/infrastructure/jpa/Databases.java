package com.gmail.kasabuta4.handson.infrastructure.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class Databases {

  @JsfDemoDB
  @Produces
  @PersistenceContext(unitName = "jsfdemoPU")
  private EntityManager jsfdemoEntityManager;
}
