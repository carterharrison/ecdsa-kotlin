package com.carterharrison.ecdsa

import java.math.BigInteger

object PointMath {
    fun multiply (a : BigInteger, b : BigInteger, curve: EcCurve) : BigInteger {
        return (a * b) % curve.p
    }

    fun divide (num : BigInteger, dom : BigInteger, curve: EcCurve) : BigInteger {
        val inverseDen = dom.modInverse(curve.p)
        return multiply(num % curve.p, inverseDen, curve)
    }

    fun tangent (point: EcPoint, curve: EcCurve) : BigInteger {
        return divide(point.x * point.x*EcConstants.THREE+curve.a, point.y * EcConstants.TWO, curve)
    }

    fun identity (point: EcPoint) : EcPoint {
        return EcPoint(point.curve.p, EcConstants.ZERO, point.curve)
    }

    fun dot (p1: EcPoint, p2: EcPoint, m : BigInteger, curve: EcCurve) : EcPoint {
        val v = (p1.y + curve.p - (m * p1.x) % curve.p) % curve.p
        val x = (m*m + curve.p - p1.x + curve.p - p2.x) % curve.p
        val y = (curve.p - (m*x) % curve.p + curve.p - v) % curve.p
        return EcPoint(x, y, curve)
    }

    fun double (point: EcPoint, curve: EcCurve) : EcPoint {
        if (point.x == curve.p) {
            return point
        }

        return dot(point, point, tangent(point, curve), curve)
    }
}