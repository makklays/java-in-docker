package com.techmatrix18.services.Impl;

import com.techmatrix18.services.CalculatorService;
import org.springframework.stereotype.Service;

/**
 * Implementation of the Calculator for counting numbers of service Calculator.
 *
 * @author Alexander Kuziv
 * @since 13.03.2025
 * @version 0.0.1
 */

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
    @Override
    public int subtract(int a, int b) {
        return a - b;
    }
}

