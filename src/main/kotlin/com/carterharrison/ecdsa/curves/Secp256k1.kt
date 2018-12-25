package com.carterharrison.ecdsa.curves

import com.carterharrison.ecdsa.EcCurve
import java.math.BigInteger

object Secp256k1 : EcCurve() {
    override val a: BigInteger = BigInteger("00")
    override val b: BigInteger = BigInteger("07")
    override val n: BigInteger = BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16)
    override val p: BigInteger = BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16)
    override val x: BigInteger = BigInteger("79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798", 16)
    override val y: BigInteger = BigInteger("483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8", 16)
}