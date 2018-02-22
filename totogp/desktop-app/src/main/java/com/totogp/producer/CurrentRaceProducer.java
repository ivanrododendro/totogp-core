package com.totogp.producer;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.totogp.model.Enrollment;
import com.totogp.model.Race;

public class CurrentRaceProducer implements Serializable {

  @Inject
  private Enrollment enrollment;

  @Produces
  @ProducedCurrentRace
  public Race produces() {
    return enrollment.getContest().getCurrentRace();
  }
}
