package com.carterharrison.ecdsa

import java.math.BigInteger


class EcPoint (val x : BigInteger, val y : BigInteger, val curve: EcCurve) {

    operator fun plus (other: EcPoint) : EcPoint {
        return curve.add(this, other)
    }

    operator fun times(n: BigInteger): EcPoint {
        return curve.multiply(this, n)
    }

    override fun toString(): String {
        return "$x, $y"
    }

    override fun equals(other: Any?): Boolean {
        if (other is EcPoint) {
            return (x == other.x && y == other.y)
        }

        return false
    }

    override fun hashCode(): Int {
        return x.hashCode() + y.hashCode()
    }
}