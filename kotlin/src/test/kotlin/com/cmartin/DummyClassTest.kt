package com.cmartin

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DummyClassTest {
    val N = 1
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
        val c = Const(1.0)
        assertEquals(1.toDouble(), N.toDouble())
    }

    @Test
    fun testSimpleSumExpr() {
        val s = Sum(Const(1.0), Const(1.0))
        assertEquals(2.toDouble(), eval(s))
    }

    @Test
    fun testSumExpr() {
        val s = Sum(Const(1.0),
                Sum(Const(2.0), Const(3.0)))
        assertEquals(6.toDouble(), eval(s))
    }

    @Test
    fun testSimpleSubtractionExpr() {
        val s = Sub(Const(1.0), Const(1.0))
        assertEquals(0.toDouble(), eval(s))
    }

    @Test
    fun testSubtractionExpr() {
        val s = Sub(Const(11.0),
                Sum(Const(2.0), Const(1.0)))

        assertEquals(8.toDouble(), eval(s))
    }

}