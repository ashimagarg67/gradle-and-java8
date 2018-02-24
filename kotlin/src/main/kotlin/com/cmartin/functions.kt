package com.cmartin

fun createDigit(d: Int): Option<Int> = if (d in 0..9) Some(d) else None

fun isDigit(d: Option<Int>): Boolean {
    return when (d) {
        is Some -> true
        is None -> false
    }
}