package com.example.demo.service;

import com.example.demo.exception.BeanValidationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ShapeFactory {

  private static final String SERVICE = "Service";

  @Autowired
  private ApplicationContext applicationContext;

  public ShapeService getShapeService(String shapeType) throws BeanValidationException {
    try {
      return applicationContext.getBean(shapeType + SERVICE, ShapeService.class);
    } catch (BeansException exception) {
      throw new BeanValidationException("Shape type " + shapeType + " does not exist");
    }
  }
}
