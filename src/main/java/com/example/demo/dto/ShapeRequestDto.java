package com.example.demo.dto;

import java.math.BigDecimal;

public class ShapeRequestDto {

  private BigDecimal sideA;
  private BigDecimal sideB;
  private BigDecimal radius;

  public BigDecimal getSideA() {
    return sideA;
  }

  public void setSideA(BigDecimal sideA) {
    this.sideA = sideA;
  }

  public BigDecimal getSideB() {
    return sideB;
  }

  public void setSideB(BigDecimal sideB) {
    this.sideB = sideB;
  }

  public BigDecimal getRadius() {
    return radius;
  }

  public void setRadius(BigDecimal radius) {
    this.radius = radius;
  }
}
