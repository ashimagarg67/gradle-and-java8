package com.cmartin.learn.calculator.api;


import io.vavr.control.Try;

import java.math.BigDecimal;

/**
 * Created by cmartin on 09/07/16.
 */
public interface CalculatorService {
    /**
     * @param a
     * @param b
     * @return
     */
    Try<BigDecimal> sum(BigDecimal a, BigDecimal b);

    /**
     * @param a
     * @param b
     * @return
     */
    Try<BigDecimal> subtract(BigDecimal a, BigDecimal b);

    /**
     * @param a
     * @param b
     * @return
     */
    Try<BigDecimal> divide(BigDecimal a, BigDecimal b);

    /**
     * @param a
     * @param b
     * @return
     */
    Try<BigDecimal> multiply(BigDecimal a, BigDecimal b);
}
