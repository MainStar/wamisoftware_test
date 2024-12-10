package com.example.demo.service;

import static com.example.demo.utils.Constants.CIRCLE_SHAPE_TYPE;

import com.example.demo.dto.ShapeRequestDto;
import com.example.demo.dto.ShapeResponseDto;
import com.example.demo.exception.ValidationCheckException;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class CircleService implements ShapeService {

  private static final BigDecimal PI_VALUE = BigDecimal.valueOf(3.14);

  @Override
  public ShapeResponseDto calculate(ShapeRequestDto requestDto) throws ValidationCheckException {
    BigDecimal radius = requestDto.getRadius();
    validateFields(radius);

    ShapeResponseDto responseDto = new ShapeResponseDto();
    responseDto.setShapeType(CIRCLE_SHAPE_TYPE);
    responseDto.setPerimeter(radius.multiply(radius).multiply(PI_VALUE));

    return responseDto;
  }

  private void validateFields(BigDecimal radius) throws ValidationCheckException {
    if (radius == null) {
      throw new ValidationCheckException("Radius field should be provided to calculate perimeter of Circle");
    }
  }
}
