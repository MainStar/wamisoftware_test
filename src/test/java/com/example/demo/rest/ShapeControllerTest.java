package com.example.demo.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.dto.ShapeRequestDto;
import com.example.demo.dto.ShapeResponseDto;
import com.example.demo.exception.BeanValidationException;
import com.example.demo.exception.ValidationCheckException;
import com.example.demo.service.ShapeFactory;
import com.example.demo.service.ShapeService;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class ShapeControllerTest {

  @InjectMocks
  private ShapeController shapeController;

  @Mock
  private ShapeFactory shapeFactory;

  @Mock
  private ShapeService shapeService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void shouldReturnCorrectResult_whenProvidedNecessaryFields()
      throws ValidationCheckException, BeanValidationException {
    String shapeType = "circle";
    ShapeRequestDto requestDto = new ShapeRequestDto();
    requestDto.setRadius(BigDecimal.valueOf(5));

    ShapeResponseDto expectedResponse = new ShapeResponseDto();
    expectedResponse.setArea(BigDecimal.valueOf(78.54));

    when(shapeFactory.getShapeService(shapeType)).thenReturn(shapeService);
    when(shapeService.calculate(requestDto)).thenReturn(expectedResponse);

    ResponseEntity<ShapeResponseDto> response = shapeController.calculate(shapeType, requestDto);

    assertNotNull(response);
    assertEquals(200, response.getStatusCodeValue());
    assertEquals(expectedResponse, response.getBody());

    verify(shapeFactory, times(1)).getShapeService(shapeType);
    verify(shapeService, times(1)).calculate(requestDto);
  }

  @Test
  public void shouldThrowBeanValidationException_whenProvidedIncorrectShapeType()
      throws BeanValidationException, ValidationCheckException {
    String shapeType = "unknownShape";
    ShapeRequestDto requestDto = new ShapeRequestDto();

    when(shapeFactory.getShapeService(shapeType)).thenThrow(
        new BeanValidationException("Shape type " + shapeType + " does not exist"));

    Exception exception = assertThrows(BeanValidationException.class, () -> {
      shapeController.calculate(shapeType, requestDto);
    });

    assertEquals("Shape type " + shapeType + " does not exist", exception.getMessage());

    verify(shapeFactory, times(1)).getShapeService(shapeType);
    verify(shapeService, never()).calculate(any());
  }

  @Test
  public void shouldThrowValidationCheckException_whenNecessaryFieldsNotProvided()
      throws ValidationCheckException, BeanValidationException {
    String shapeType = "circle";
    ShapeRequestDto requestDto = new ShapeRequestDto();

    when(shapeFactory.getShapeService(shapeType)).thenReturn(shapeService);
    when(shapeService.calculate(requestDto)).thenThrow(
        new ValidationCheckException("Radius field should be provided to calculate perimeter of Circle"));

    Exception exception = assertThrows(ValidationCheckException.class, () -> {
      shapeController.calculate(shapeType, requestDto);
    });

    assertEquals("Radius field should be provided to calculate perimeter of Circle", exception.getMessage());

    verify(shapeFactory, times(1)).getShapeService(shapeType);
    verify(shapeService, times(1)).calculate(requestDto);
  }

  @Test
  public void shouldNotThrowException_whenShapeServiceReturnNull()
      throws ValidationCheckException, BeanValidationException {
    String shapeType = "circle";
    ShapeRequestDto requestDto = new ShapeRequestDto();

    when(shapeFactory.getShapeService(shapeType)).thenReturn(shapeService);
    when(shapeService.calculate(requestDto)).thenReturn(null);

    ResponseEntity<ShapeResponseDto> response = shapeController.calculate(shapeType, requestDto);

    assertNotNull(response);
    assertNull(response.getBody());
    assertEquals(200, response.getStatusCodeValue());

    verify(shapeFactory, times(1)).getShapeService(shapeType);
    verify(shapeService, times(1)).calculate(requestDto);
  }
}
