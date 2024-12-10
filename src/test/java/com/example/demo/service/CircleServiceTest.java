package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.demo.dto.ShapeRequestDto;
import com.example.demo.dto.ShapeResponseDto;
import com.example.demo.exception.ValidationCheckException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CircleServiceTest {

  @InjectMocks
  private CircleService circleService;

  @Test
  public void shouldReturnResponseSuccessfully() throws ValidationCheckException {
    ShapeRequestDto requestDto = new ShapeRequestDto();
    requestDto.setRadius(BigDecimal.valueOf(5));

    ShapeResponseDto response = circleService.calculate(requestDto);

    assertNotNull(response);
    assertEquals("circle", response.getShapeType());
    BigDecimal expectedPerimeter = BigDecimal.valueOf(5).multiply(BigDecimal.valueOf(5))
        .multiply(BigDecimal.valueOf(3.14));
    assertEquals(0, response.getPerimeter().compareTo(expectedPerimeter));
  }

  @Test
  public void shouldThrowValidationCheckException_whenRadiusIsNotProvided() {
    ShapeRequestDto requestDto = new ShapeRequestDto();
    requestDto.setRadius(null);

    Exception exception = assertThrows(ValidationCheckException.class, () -> {
      circleService.calculate(requestDto);
    });

    assertEquals("Radius field should be provided to calculate perimeter of Circle", exception.getMessage());
  }
}
