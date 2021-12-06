package com.iluwatar.daofactory;

import lombok.Getter;
import lombok.Setter;

/**
 * User entity.
 */
@Getter
@Setter
public class User implements java.io.Serializable {

  /**
   * UserId for this User.
   */
  private int userId;

  /**
   * Name for this User.
   */
  private String name;

  /**
   * Address for this User.
   */
  private String streetAddress;

  /**
   * city for this User.
   */
  private String city;

  private static final long serialVersionUID = 42L;


  /**
   * Instantiates a User.
   */
  public User() {
    // This constructor is intentionally empty. Nothing special is needed here.
  }
}