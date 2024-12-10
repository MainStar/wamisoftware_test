package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShapeResponseDto {

  private String shapeType;
  private BigDecimal perimeter;
  private BigDecimal area;

  public String getShapeType() {
    return shapeType;
  }

  public void setShapeType(String shapeType) {
    this.shapeType = shapeType;
  }

  public BigDecimal getPerimeter() {
    return perimeter;
  }

  public void setPerimeter(BigDecimal perimeter) {
    this.perimeter = perimeter;
  }

  public BigDecimal getArea() {
    return area;
  }

  public void setArea(BigDecimal are) {
    this.area = are;
  }
}
