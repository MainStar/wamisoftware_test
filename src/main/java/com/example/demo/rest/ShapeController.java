package com.example.demo.rest;

import com.example.demo.dto.ShapeRequestDto;
import com.example.demo.dto.ShapeResponseDto;
import com.example.demo.exception.BeanValidationException;
import com.example.demo.exception.ValidationCheckException;
import com.example.demo.service.ShapeFactory;
import com.example.demo.service.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shape")
public class ShapeController {

  @Autowired
  private ShapeFactory shapeFactory;

  @PostMapping("/{shapeType}/calculate-are")
  public ResponseEntity<ShapeResponseDto> calculate(@PathVariable String shapeType,
                                                    @RequestBody ShapeRequestDto shapeRequestDto)
      throws ValidationCheckException, BeanValidationException {
    ShapeService shapeService = shapeFactory.getShapeService(shapeType);
    ShapeResponseDto responseDto = shapeService.calculate(shapeRequestDto);
    return ResponseEntity.ok(responseDto);
  }
}
