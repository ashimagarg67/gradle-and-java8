package com.cmartin

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DummyClassTest {
    val N = 1
    val HUNDRED = 100
    val KOTLIN = "kotlin"

    @Test
    fun testConstructor() {
        val dc = DummyClass(N, KOTLIN)
        assertEquals(N, dc.n)
        assertEquals(KOTLIN, dc.s)
    }

    @Test
    fun testConstructorDefaultValue() {
        val dc = DummyClass(N)
        assertEquals(N, dc.n)
        assertTrue(dc.s.isEmpty())
    }

    @Test
    fun testConsExpr() {
        val expr = Const(1.0)
        assertEquals(1.toDouble(), eval(expr))
    }

    @Test
    fun testSimpleSumExpr() {
        val expr = Sum(Const(1.0), Const(1.0))
        assertEquals(2.toDouble(), eval(expr))
    }

    @Test
    fun testSumExpr() {
        val expr = Sum(Const(1.0),
                Sum(Const(2.0), Const(3.0)))
        assertEquals(6.toDouble(), eval(expr))
    }

    @Test
    fun testSimpleSubtractionExpr() {
        val expr = Sub(Const(1.0), Const(1.0))
        assertEquals(0.toDouble(), eval(expr))
    }

    @Test
    fun testSubtractionExpr() {
        val expr = Sub(Const(11.0),
                Sum(Const(2.0), Const(1.0)))

        assertEquals(8.toDouble(), eval(expr))
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