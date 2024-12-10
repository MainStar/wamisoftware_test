package com.example.demo.service;

import com.example.demo.dto.ShapeRequestDto;
import com.example.demo.dto.ShapeResponseDto;
import com.example.demo.exception.ValidationCheckException;
import java.math.BigDecimal;

public interface ShapeService {

  ShapeResponseDto calculate(ShapeRequestDto requestDto) throws ValidationCheckException;

}
