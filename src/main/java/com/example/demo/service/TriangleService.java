package com.example.demo.service;

import static com.example.demo.utils.Constants.TRIANGLE_SHAPE_TYPE;

import com.example.demo.dto.ShapeRequestDto;
import com.example.demo.dto.ShapeResponseDto;
import com.example.demo.exception.ValidationCheckException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class TriangleService implements ShapeService {

  private static final BigDecimal TWO_DECIMAL = BigDecimal.valueOf(2);
  private static final Integer SCALE = 2;

  @Override
  public ShapeResponseDto calculate(ShapeRequestDto requestDto) throws ValidationCheckException {
    BigDecimal sideA = requestDto.getSideA();
    BigDecimal sideB = requestDto.getSideB();
    validateFields(sideA, sideB);

    ShapeResponseDto responseDto = new ShapeResponseDto();
    responseDto.setShapeType(TRIANGLE_SHAPE_TYPE);
    responseDto.setArea(sideA.add(sideB).divide(TWO_DECIMAL, SCALE, RoundingMode.HALF_UP));

    return responseDto;
  }

  private void validateFields(BigDecimal sideA, BigDecimal sideB) throws ValidationCheckException {
    if (sideA == null) {
      throw new ValidationCheckException("SideA field should be provided for calculating triangle are");
    }
    if (sideB == null) {
      throw new ValidationCheckException("SideB field should be provided for calculating triangle are");
    }
  }
}
