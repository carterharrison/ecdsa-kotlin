package com.carterharrison.ecdsa

import com.carterharrison.ecdsa.curves.Secp256k1
import org.junit.Assert
import org.junit.Test
import java.math.BigInteger
import java.security.SecureRandom


class EcPointTest {

    @Test
    fun tangent () {
        val g = EcPoint(Secp256k1.x, Secp256k1.y, Secp256k1)

        Assert.assertEquals(
            PointMath.tangent(g, Secp256k1),
            BigInteger("91914383230618135761690975197207778399550061809281766160147273830617914855857")
        )
    }

//    @Test
//    fun mutpily () {
//        val g = EcPoint(Secp256k1.x, Secp256k1.y, Secp256k1)
//        val privateKeyBytes = ByteArray(32)
//        SecureRandom().nextBytes(privateKeyBytes)
//        val privateKey = (BigInteger("ad02e7aee082ac9d1f6a2e613f514879880fad8a971160fe811b5a4719fc1849", 16).abs()) % Secp256k1.p
//        println((g * privateKey).x.toString())
//    }

}