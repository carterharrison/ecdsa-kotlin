package com.carterharrison.ecdsa

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.toBigInteger


/**
 * A cryptographic elliptical curve to preform cryptography on.
 */
abstract class EcCurve {
    /**
     * The prime modulus of the curve.
     */
    abstract val p : BigInteger

    /**
     * The prime order of the curve.
     */
    abstract val n : BigInteger

    /**
     * The a coefficient of the curve.
     */
    abstract val a : BigInteger

    /**
     * The b coefficient of the curve.
     */
    abstract val b : BigInteger

    /**
     * X cord of the generator point -> G.
     */
    abstract val x : BigInteger

    /**
     * Y cord of the generator point -> G.
     */
    abstract val y : BigInteger

    /**
     * The generator point of the curve.
     */
    val g : EcPoint
        get() = EcPoint(x, y, this)

    /**
     * The identify of the curve.
     *
     * (PRIME MODULUS, 0)
     */
    val identity : EcPoint
        get() = PointMath.identity(g)

    /**
     * Adds two points that belong to the curve.
     *
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The sum of the two points.
     */
    fun add (p1 : EcPoint, p2: EcPoint) : EcPoint {
        if (p1.x == p) {
            return p2
        } else if (p2.x == p) {
            return p1
        }

        if (p1.x == p2.x) {
            if (p1.y == p2.y) {
                return PointMath.double(p1)
            }
            return PointMath.identity(p1)
        }

        val m = PointMath.divide(p1.y + p - p2.y, p1.x + p - p2.x, p)
        return PointMath.dot(p1, p2, m, this)
    }

    /**
     * Finds the product of a point on the curve. (Scalar multiplication)
     *
     * @param g The generator point to start at.
     * @param n The number of times to dot the curve from g.
     * @return The point ended up on the curve.
     */
    fun multiply (g : EcPoint, n : BigInteger) : EcPoint {
        var r = identity
        var q = g
        var m = n

        while (m != EcConstants.ZERO) {


            if (m and EcConstants.ONE != EcConstants.ZERO) {
                r = add(r, q)
            }

            m = m shr 1

            if (m != EcConstants.ZERO) {
                q = PointMath.double(q)
            }

        }

        return r
    }

    /**
     * Adds two points that belong to the curve.
     *
     * @param point The point to add to the g point.
     * @return The sum of the two points.
     */
    operator fun plus (point : EcPoint) : EcPoint {
        return add(g, point)
    }

    /**
     * Finds the product of a point on the curve and its generator point. (Scalar multiplication)
     *
     * @param n The number of times to dot the curve from g.
     * @return The product of the point.
     */
    operator fun times(n : BigInteger) : EcPoint {
        return multiply(g, n)
    }
}
