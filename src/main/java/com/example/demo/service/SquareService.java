package com.example.demo.service;

import static com.example.demo.utils.Constants.SQUARE_SHAPE_TYPE;

import com.example.demo.dto.ShapeRequestDto;
import com.example.demo.dto.ShapeResponseDto;
import com.example.demo.exception.ValidationCheckException;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class SquareService implements ShapeService {

  @Override
  public ShapeResponseDto calculate(ShapeRequestDto requestDto) throws ValidationCheckException {
    BigDecimal sideA = requestDto.getSideA();
    BigDecimal sideB = requestDto.getSideB();
    validateFields(sideA, sideB);

    ShapeResponseDto responseDto = new ShapeResponseDto();
    responseDto.setShapeType(SQUARE_SHAPE_TYPE);
    responseDto.setArea(sideA.multiply(sideB));

    return responseDto;
  }

  private void validateFields(BigDecimal sideA, BigDecimal sideB) throws ValidationCheckException {
    if (sideA == null) {
      throw new ValidationCheckException("SideA field should be provided for calculating square are");
    }
    if (sideB == null) {
      throw new ValidationCheckException("SideB field should be provided for calculating square are");
    }
  }
}
