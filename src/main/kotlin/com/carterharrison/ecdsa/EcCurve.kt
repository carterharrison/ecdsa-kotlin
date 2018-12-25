package com.carterharrison.ecdsa

import java.math.BigInteger

abstract class EcCurve {
    // prime modulus
    abstract val p : BigInteger

    // prime order
    abstract val n : BigInteger

    // coefficient a
    abstract val a : BigInteger

    // coefficient b
    abstract val b : BigInteger

    // x cord of base point G
    abstract val x : BigInteger

    // y cord of base point G
    abstract val y : BigInteger

    fun add(p1 : EcPoint, p2: EcPoint) : EcPoint {
        if (p1.x == p) {
            return p2
        } else if (p2.x == p) {
            return p1
        }

        if (p1.x == p2.x) {
            if (p1.y == p2.y) {
                return PointMath.double(p1, this)
            }
            return PointMath.identity(p1)
        }

        val m = PointMath.divide(p1.y + p - p2.y, p1.x + p - p2.x, this)
        return PointMath.dot(p1, p2, m, this)
    }

    fun multiply (g : EcPoint, n : BigInteger) : EcPoint {
        var q = g
        var r = PointMath.identity(g)
        var m = n

        while (m != EcConstants.ZERO) {
            if (m and EcConstants.ONE != 0.toBigInteger()) {
                r = add(r, q)
            }

            m = m shr 1

            if (m != 0.toBigInteger()) {
                q = PointMath.double(q, g.curve)
            }

        }

        return r
    }
}