package com.carterharrison.ecdsa

import java.math.BigInteger
import java.security.PrivateKey
import java.security.SecureRandom

/**
 * A set of functions to help create EcKeyPairs
 */
object EcKeyGenerator {

    /**
     * Creates a random KeyPair on a curve.
     *
     * @param curve The curve to create the keypair on.
     * @return A EcKeyPair with a public and private key.
     */
    fun newInstance (curve: EcCurve) : EcKeyPair {
        val privateKey = random32BytePrivateKey % curve.p
        val publicKey = (curve.g * privateKey)
        return EcKeyPair(publicKey, privateKey)
    }

    /**
     * Creates a keypair from a private key.
     *
     * @param privateKey The private key to create a public key from.
     * @param curve The curve to create the public key on.
     */
    fun newInstance (privateKey: BigInteger, curve: EcCurve) : EcKeyPair {
        val publicKey = (curve.g * privateKey)
        return EcKeyPair(publicKey, privateKey)
    }

    /**
     * Gets a random 32 byte (256 bit) BigInteger.
     */
    private val random32BytePrivateKey : BigInteger
        get() {
            val privateKeyBytes = ByteArray(32)
            SecureRandom().nextBytes(privateKeyBytes)
            return (BigInteger(privateKeyBytes).abs())
        }
}