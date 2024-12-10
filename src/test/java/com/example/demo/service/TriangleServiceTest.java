package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.demo.dto.ShapeRequestDto;
import com.example.demo.dto.ShapeResponseDto;
import com.example.demo.exception.ValidationCheckException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TriangleServiceTest {

  @InjectMocks
  private TriangleService triangleService;

  @Test
  public void should_ReturnResponseSuccessfully() throws ValidationCheckException {
    ShapeRequestDto requestDto = new ShapeRequestDto();
    requestDto.setSideA(BigDecimal.valueOf(8));
    requestDto.setSideB(BigDecimal.valueOf(6));

    ShapeResponseDto response = triangleService.calculate(requestDto);

    assertNotNull(response);
    assertEquals("triangle", response.getShapeType());
    BigDecimal expectedArea = BigDecimal.valueOf(8).add(BigDecimal.valueOf(6))
        .divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);
    assertEquals(0, response.getArea().compareTo(expectedArea));
  }

  @Test
  public void shouldThrowValidationCheckException_whenSideAFieldIsNotProvided() {
    ShapeRequestDto requestDto = new ShapeRequestDto();
    requestDto.setSideA(null);
    requestDto.setSideB(BigDecimal.valueOf(6));

    Exception exception = assertThrows(ValidationCheckException.class, () -> {
      triangleService.calculate(requestDto);
    });

    assertEquals("SideA field should be provided for calculating triangle are", exception.getMessage());
  }

  @Test
  public void shouldThrowValidationCheckException_whenSideBFieldIsNotProvided() {
    ShapeRequestDto requestDto = new ShapeRequestDto();
    requestDto.setSideA(BigDecimal.valueOf(8));
    requestDto.setSideB(null);

    Exception exception = assertThrows(ValidationCheckException.class, () -> {
      triangleService.calculate(requestDto);
    });

    assertEquals("SideB field should be provided for calculating triangle are", exception.getMessage());
  }

}
