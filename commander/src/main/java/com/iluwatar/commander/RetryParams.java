package com.iluwatar.commander;

/**
 * Record to hold the parameters related to retries
 * @param numOfRetries
 * @param retryDuration
 */
public record RetryParams(int numOfRetries, long retryDuration) {
  public static final RetryParams DEFAULT = new RetryParams(3, 30000L);
}