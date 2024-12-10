package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.exception.BeanValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

@ExtendWith(MockitoExtension.class)
public class ShapeFactoryTest {

  @InjectMocks
  private ShapeFactory shapeFactory;

  @Mock
  private ApplicationContext applicationContext;

  @Test
  public void should_ReturnResponseSuccessfully() throws BeanValidationException {
    String shapeType = "Circle";
    ShapeService mockShapeService = mock(ShapeService.class);

    when(applicationContext.getBean("CircleService", ShapeService.class)).thenReturn(mockShapeService);

    ShapeService result = shapeFactory.getShapeService(shapeType);

    assertNotNull(result);
    assertEquals(mockShapeService, result);
    verify(applicationContext, times(1)).getBean("CircleService", ShapeService.class);
  }

  @Test
  public void shouldThrowsBeanValidationException_whenBeanNotFoundByProvidedShapeType() {
    String shapeType = "NonExistingShape";

    when(applicationContext.getBean("NonExistingShapeService", ShapeService.class))
        .thenThrow(new BeansException("No bean found") {});

    Exception exception = assertThrows(BeanValidationException.class, () -> {
      shapeFactory.getShapeService(shapeType);
    });

    assertEquals("Shape type NonExistingShape does not exist", exception.getMessage());
    verify(applicationContext, times(1))
        .getBean("NonExistingShapeService", ShapeService.class);
  }
}
