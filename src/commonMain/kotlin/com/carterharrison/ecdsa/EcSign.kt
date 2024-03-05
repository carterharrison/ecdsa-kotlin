package com.carterharrison.ecdsa

import com.carterharrison.ecdsa.hash.EcHasher
import java.math.BigInteger
import java.security.SecureRandom

object EcSign {

    /**
     * Gets a secure random k value, that is between 1 and n - 1
     *
     * @param n The upper ceiling of [1 to n - 1]  to generate a random number
     * @return A secure random number between [1 and n - 1]
     */
    private fun getRandomK (n : BigInteger) : BigInteger {
        val randomValue = BigInteger(256, SecureRandom())

        if (randomValue >= n || randomValue <= BigInteger.ONE) {
            return getRandomK(n)
        }

        return randomValue
    }

    /**
     * Signs a message given a key pair, data, and a hashing algorithm to hash the message before signing it.
     *
     * @param keyPair The keypair to sign the message with
     * @param data tThe data to sign
     * @param hasher The hasher to hash the data with before signing
     * @return The signer of the data, keypair, and hasher
     */
    fun signData (keyPair: EcKeyPair, data : ByteArray, hasher : EcHasher) : EcSignature {
        // todo range from 1 to n-1
        val hash = BigInteger(1, hasher.hash(data))
        val g = keyPair.publicKey.curve.g
        val n = keyPair.publicKey.curve.n
        val k = getRandomK(n) % n
        val p1 = g * k
        val r = p1.x

        if (r == EcConstants.ZERO) {
            signData(keyPair, data, hasher)
        }
        val s = (k.modInverse(n)) * (hash + (keyPair.privateKey * r) % n) % n

        if (s == EcConstants.ZERO) {
            signData(keyPair, data, hasher)
        }

        return EcSignature(r, s)
    }

    /**
     * Verify that the public key signed that data
     *
     * @param publicKey The public key used in the signature
     * @param data The data signed
     * @param hasher The hasher used to sign the data
     * @param signature The signature signed by the public key
     * @return If the signature is valid
     */
    fun verifySignature (publicKey : EcPoint, data: ByteArray, hasher: EcHasher, signature: EcSignature) : Boolean {
        val hash = BigInteger(1, hasher.hash(data))
        val g = publicKey.curve.g
        val n = publicKey.curve.n
        val r = signature.r
        val s = signature.s

        if (r < BigInteger.ONE || r > n - BigInteger.ONE) {
            return false
        }

        if (s < BigInteger.ONE || s > n - BigInteger.ONE) {
            return false
        }

        val c = s.modInverse(n)
        val u1 = (hash * c) % n
        val u2 = (r * c) % n
        val xy = g * u1 + publicKey * u2
        val v = xy.x % n

        return v == r
    }
}