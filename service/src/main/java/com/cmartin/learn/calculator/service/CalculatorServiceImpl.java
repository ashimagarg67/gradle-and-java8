package com.cmartin.learn.calculator.service;

import com.cmartin.learn.calculator.api.CalculatorService;
import io.vavr.control.Try;

import java.math.BigDecimal;

public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public Try<BigDecimal> sum(final BigDecimal a, final BigDecimal b) {
        return Try.success(a.add(b));
    }

    @Override
    public Try<BigDecimal> subtract(final BigDecimal a, final BigDecimal b) {
        return Try.success(a.subtract(b));
    }

    @Override
    public Try<BigDecimal> divide(final BigDecimal a, final BigDecimal b) {
        return b.equals(BigDecimal.ZERO) ?
                Try.failure(new ArithmeticException("Division by zero")) :
                Try.success(a.divide(b));
    }

    @Override
    public Try<BigDecimal> multiply(final BigDecimal a, final BigDecimal b) {
        return Try.success(a.multiply(b));
    }
}
