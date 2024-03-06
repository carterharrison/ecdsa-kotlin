package com.carterharrison.ecdsa.curves

import com.carterharrison.ecdsa.EcCurve
import com.ionspin.kotlin.bignum.integer.BigInteger


object Secp256r1 : EcCurve() {
    override val a: BigInteger = BigInteger.parseString("ffffffff00000001000000000000000000000000fffffffffffffffffffffffc", 16)
    override val b: BigInteger = BigInteger.parseString("5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b", 16)
    override val n: BigInteger = BigInteger.parseString("ffffffff00000000ffffffffffffffffbce6faada7179e84f3b9cac2fc632551", 16)
    override val p: BigInteger = BigInteger.parseString("ffffffff00000001000000000000000000000000ffffffffffffffffffffffff", 16)
    override val x: BigInteger = BigInteger.parseString("6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", 16)
    override val y: BigInteger = BigInteger.parseString("4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5", 16)
}