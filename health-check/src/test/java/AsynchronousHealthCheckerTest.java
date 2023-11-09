import static org.junit.jupiter.api.Assertions.*;

import com.iluwatar.health.check.AsynchronousHealthChecker;
import java.util.concurrent.*;
import java.util.function.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

/**
 * Tests for {@link AsynchronousHealthChecker}.
 *
 * @author ydoksanbir
 */
public class AsynchronousHealthCheckerTest {

  /** The {@link AsynchronousHealthChecker} instance to be tested. */
  private AsynchronousHealthChecker healthChecker;

  /**
   * Sets up the test environment before each test method.
   *
   * <p>Creates a new {@link AsynchronousHealthChecker} instance.
   */
  @BeforeEach
  public void setUp() {
    healthChecker = new AsynchronousHealthChecker();
  }

  /**
   * Tears down the test environment after each test method.
   *
   * <p>Shuts down the {@link AsynchronousHealthChecker} instance to prevent resource leaks.
   */
  @AfterEach
  public void tearDown() {
    healthChecker.shutdown();
  }

  /**
   * Tests that the {@link performCheck()} method completes normally when the health supplier
   * returns a successful health check.
   *
   * <p>Given a health supplier that returns a healthy status, the test verifies that the {@link
   * performCheck()} method completes normally and returns the expected health object.
   */
  @Test
  public void whenPerformCheck_thenCompletesNormally()
      throws ExecutionException, InterruptedException {
    // Given
    Supplier<Health> healthSupplier = () -> Health.up().build();

    // When
    CompletableFuture<Health> healthFuture = healthChecker.performCheck(healthSupplier, 3);

    // Then
    Health health = healthFuture.get();
    assertEquals(Health.up().build(), health);
  }

  /**
   * Tests that the {@link performCheck()} method returns a healthy health status when the health
   * supplier returns a healthy status.
   *
   * <p>Given a health supplier that returns a healthy status, the test verifies that the {@link
   * performCheck()} method returns a health object with a status of UP.
   */
  @Test
  public void whenHealthCheckIsSuccessful_ReturnsHealthy()
      throws ExecutionException, InterruptedException {
    // Arrange
    Supplier<Health> healthSupplier = () -> Health.up().build();

    // Act
    CompletableFuture<Health> healthFuture = healthChecker.performCheck(healthSupplier, 4);

    // Assert
    assertEquals(Status.UP, healthFuture.get().getStatus());
  }

  /**
   * Tests that the {@link performCheck()} method rejects new tasks after the {@link shutdown()}
   * method is called.
   *
   * <p>Given the {@link AsynchronousHealthChecker} instance is shut down, the test verifies that
   * the {@link performCheck()} method throws a {@link RejectedExecutionException} when attempting
   * to submit a new health check task.
   */
  @Test
  public void whenShutdown_thenRejectsNewTasks() {
    // Given
    healthChecker.shutdown();

    // When/Then
    assertThrows(
        RejectedExecutionException.class,
        () -> healthChecker.performCheck(() -> Health.up().build(), 2),
        "Expected to throw RejectedExecutionException but did not");
  }

  /**
   * Tests that the {@link performCheck()} method returns a healthy health status when the health
   * supplier returns a healthy status.
   *
   * <p>Given a health supplier that throws a RuntimeException, the test verifies that the {@link
   * performCheck()} method returns a health object with a status of DOWN and an error message
   * containing the exception message.
   */
  @Test
  public void whenHealthCheckThrowsException_thenReturnsDown() {
    // Arrange
    Supplier<Health> healthSupplier =
        () -> {
          throw new RuntimeException("Health check failed");
        };
    // Act
    CompletableFuture<Health> healthFuture = healthChecker.performCheck(healthSupplier, 10);
    // Assert
    Health health = healthFuture.join();
    assertEquals(Status.DOWN, health.getStatus());
    String errorMessage = health.getDetails().get("error").toString();
    assertTrue(errorMessage.contains("Health check failed"));
  }
}