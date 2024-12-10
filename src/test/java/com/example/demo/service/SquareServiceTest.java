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
public class SquareServiceTest {

  @InjectMocks
  private SquareService squareService;

  @Test
  public void shouldReturnResponseSuccessfully() throws ValidationCheckException {
    ShapeRequestDto requestDto = new ShapeRequestDto();
    requestDto.setSideA(BigDecimal.valueOf(4));
    requestDto.setSideB(BigDecimal.valueOf(4));

    ShapeResponseDto response = squareService.calculate(requestDto);

    assertNotNull(response);
    assertEquals("square", response.getShapeType());
    BigDecimal expectedArea = BigDecimal.valueOf(4).multiply(BigDecimal.valueOf(4));
    assertEquals(0, response.getArea().compareTo(expectedArea));
  }

  @Test
  public void shouldThrowValidationCheckException_whenSideAFieldIsNotProvided() {
    ShapeRequestDto requestDto = new ShapeRequestDto();
    requestDto.setSideA(null);
    requestDto.setSideB(BigDecimal.valueOf(4));

    Exception exception = assertThrows(ValidationCheckException.class, () -> {
      squareService.calculate(requestDto);
    });

    assertEquals("SideA field should be provided for calculating square are", exception.getMessage());
  }

  @Test
  public void shouldThrowValidationCheckException_whenSideBFieldIsNotProvided() {
    ShapeRequestDto requestDto = new ShapeRequestDto();
    requestDto.setSideA(BigDecimal.valueOf(4));
    requestDto.setSideB(null);

    Exception exception = assertThrows(ValidationCheckException.class, () -> {
      squareService.calculate(requestDto);
    });

    assertEquals("SideB field should be provided for calculating square are", exception.getMessage());
  }
}
