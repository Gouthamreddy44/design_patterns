package com.iluwatar.datatransferenum;

/**
 * The entity class for product entity. This class act as entity in the demo.
 */
public class Product {
  private Long id;
  private String name;
  private Double price;
  private Double cost;
  private String supplier;

  public Long getId() {
    return id;
  }

  public Product setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Product setName(String name) {
    this.name = name;
    return this;
  }

  public Double getPrice() {
    return price;
  }

  public Product setPrice(Double price) {
    this.price = price;
    return this;
  }

  public Double getCost() {
    return cost;
  }

  public Product setCost(Double cost) {
    this.cost = cost;
    return this;
  }

  public String getSupplier() {
    return supplier;
  }

  public Product setSupplier(String supplier) {
    this.supplier = supplier;
    return this;
  }

  @Override
  public String toString() {
    return "Product{"
        + "id=" + id
        + ", name='" + name + '\''
        + ", price=" + price
        + ", cost=" + cost
        + ", supplier='" + supplier + '\''
        + '}';
  }
}
