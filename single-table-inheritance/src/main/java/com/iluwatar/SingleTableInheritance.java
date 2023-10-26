package com.iluwatar;

import com.iluwatar.entity.Car;
import com.iluwatar.entity.Truck;
import com.iluwatar.entity.Vehicle;
import com.iluwatar.service.VehicleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

/**
 * Single Table Inheritance pattern :
 * <br>
 * It maps each instance of class in an inheritance tree into a single table.
 * <br>
 * <p>
 * In case of current project, in order to specify the Single Table Inheritance to Hibernate
 * we annotate the main Vehicle root class with @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
 * due to which a single root <b>Vehicle</b> class table will be created
 * in the database and it will have columns for all the fields of
 * it's subclasses(Car, Freighter, Train, Truck). <br>
 * Additional to that, a new separate <b>"vehicle_id"</b> column would be added
 * to the Vehicle table to save the type of the subclass object that
 * is being stored in the database. This value is specified by the @DiscriminatorValue annotation
 * value for each subclass in case of Hibernate. <br>
 * </p><br>
 * Below is the main Spring Boot Application class from where the Program Runs.
 * <p>
 * It implements the CommandLineRunner to run the statements at the
 * start of the application program.
 * </p>
 */
@SpringBootApplication
@AllArgsConstructor
public class SingleTableInheritance implements CommandLineRunner {

  //Autowiring the VehicleService class to execute the business logic methods
  private final VehicleService vehicleService;

  /**
   * The entry point of the Spring Boot Application.
   *
   * @param args program runtime arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(SingleTableInheritance.class, args);
  }

  /**
   * The starting point of the CommandLineRunner
   * where the main program is run.
   *
   * @param args program runtime arguments
   */
  @Override
  public void run(String... args) throws Exception {

    Logger log = LoggerFactory.getLogger(SingleTableInheritance.class);

    log.info("Saving Vehicles :- ");

    // Saving Car to DB as a Vehicle
    Vehicle vehicle1 = new Car("Tesla", "Model S", 4, 825);
    Vehicle car1 = vehicleService.saveVehicle(vehicle1);
    log.info("Vehicle 1 saved : {}", car1);

    // Saving Truck to DB as a Vehicle
    Vehicle vehicle2 = new Truck("Ford", "F-150", 3325, 14000);
    Vehicle truck1 = vehicleService.saveVehicle(vehicle2);
    log.info("Vehicle 2 saved : {}\n", truck1);


    log.info("Fetching Vehicles :- ");

    // Fetching the Car from DB
    Car savedCar1 = (Car) vehicleService.getVehicle(vehicle1.getVehicleId());
    log.info("Fetching Car1 from DB : {}", savedCar1);

    // Fetching the Truck from DB
    Truck savedTruck1 = (Truck) vehicleService.getVehicle(vehicle2.getVehicleId());
    log.info("Fetching Truck1 from DB : {}\n", savedTruck1);

    log.info("Fetching All Vehicles :- ");

    // Fetching the Vehicles present in the DB
    List<Vehicle> allVehiclesFromDb = vehicleService.getAllVehicles();
    allVehiclesFromDb.forEach( s -> log.info(s.toString()));
  }
}