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
public class RectangleServiceTest {

  @InjectMocks
  private RectangleService rectangleService;

  @Test
  public void should_ReturnResponseSuccessfully() throws ValidationCheckException {
    ShapeRequestDto requestDto = new ShapeRequestDto();
    requestDto.setSideA(BigDecimal.valueOf(4));
    requestDto.setSideB(BigDecimal.valueOf(5));

    ShapeResponseDto response = rectangleService.calculate(requestDto);

    assertNotNull(response);
    assertEquals("rectangle", response.getShapeType());
    BigDecimal expectedArea = BigDecimal.valueOf(4).multiply(BigDecimal.valueOf(5));
    assertEquals(0, response.getArea().compareTo(expectedArea));
  }

  @Test
  public void shouldThrowValidationCheckException_whenSideAFieldIsNotProvided() {
    ShapeRequestDto requestDto = new ShapeRequestDto();
    requestDto.setSideA(null);
    requestDto.setSideB(BigDecimal.valueOf(5));

    Exception exception = assertThrows(ValidationCheckException.class, () -> {
      rectangleService.calculate(requestDto);
    });

    assertEquals("SideA field should be provided for calculating rectangle are", exception.getMessage());
  }

  @Test
  public void shouldThrowValidationCheckException_whenSideBFieldIsNotProvided() {
    ShapeRequestDto requestDto = new ShapeRequestDto();
    requestDto.setSideA(BigDecimal.valueOf(4));
    requestDto.setSideB(null);

    Exception exception = assertThrows(ValidationCheckException.class, () -> {
      rectangleService.calculate(requestDto);
    });

    assertEquals("SideB field should be provided for calculating rectangle are", exception.getMessage());
  }

}
