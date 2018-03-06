package com.cmartin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DummyClassTest {
    val N = 1
    val HUNDRED = 100
    val KOTLIN = "kotlin"

    @Test
    fun testConstructor() {
        val dc = DummyClass(N, KOTLIN)
        assertThat(dc.n).isEqualTo(N)
        assertThat(dc.s).isEqualToIgnoringWhitespace(KOTLIN)
        assertThat(dc.s2).isEmpty()
    }

    @Test
    fun testConstructorDefaultValue() {
        val dc = DummyClass(N)
        assertThat(dc.n).isEqualTo(N)
        assertThat(dc.s).isEmpty()
        assertThat(dc.s2).isEmpty()
    }

    @Test
    fun testConstructorNameArg() {
        val dc = DummyClass(1, s2 = KOTLIN)
        assertThat(dc.n).isEqualTo(N)
        assertThat(dc.s).isEmpty()
        assertThat(dc.s2).isEqualTo(KOTLIN)
    }

    @Test
    fun testConsExpr() {
        val expr = Const(1.0)
        assertThat(1.toDouble()).isEqualTo(eval(expr))
    }

    @Test
    fun testSimpleSumExpr() {
        val expr = Sum(Const(1.0), Const(1.0))
        assertThat(2.toDouble()).isEqualTo(eval(expr))
    }

    @Test
    fun testSumExpr() {
        val expr = Sum(Const(1.0),
                Sum(Const(2.0), Const(3.0)))
        assertThat(6.toDouble()).isEqualTo(eval(expr))
    }

    @Test
    fun testSimpleSubtractionExpr() {
        val expr = Sub(Const(1.0), Const(1.0))
        assertThat(0.toDouble()).isEqualTo(eval(expr))
    }

    @Test
    fun testSubtractionExpr() {
        val expr = Sub(Const(11.0),
                Sum(Const(2.0), Const(1.0)))
        assertThat(8.toDouble()).isEqualTo(eval(expr))
    }

    @Test
    fun `should return Some Int`() {
        val d = createDigit(N)
        assertThat(d).isEqualTo(optionOf(N))
    }

    @Test
    fun `should return None`() {
        val d = createDigit(HUNDRED)
        assertThat(d).isEqualTo(None)
    }

    @Test
    fun `should be digit`() {
        val d = createDigit(N)
        assertThat(isDigit(d)).isTrue()
    }

    @Test
    fun `should not be digit`() {
        val d = createDigit(HUNDRED)
        assertThat(isDigit(d)).isFalse()
    }
}