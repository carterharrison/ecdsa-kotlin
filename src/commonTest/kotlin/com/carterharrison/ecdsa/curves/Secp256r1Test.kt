package com.carterharrison.ecdsa.curves

import com.carterharrison.ecdsa.EcKeyGenerator
import com.carterharrison.ecdsa.EcPoint
import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals

class Secp256r1Test {

  private fun testKeyPair(x: String, y: String, p: String) {
    val expectedPublic = EcPoint(BigInteger.parseString(x, 16), BigInteger.parseString(y, 16), Secp256r1)
    val privateKey = BigInteger.parseString(p, 16)
    val keypair = EcKeyGenerator.newInstance(privateKey, Secp256r1)

    assertEquals(expectedPublic, keypair.publicKey)
  }

  @OptIn(ExperimentalUnsignedTypes::class)
  fun ByteArray.toHexString(): String = asUByteArray().joinToString("") { it.toString(radix = 16).padStart(2, '0') }

  @Test
  fun testCase1() {
    testKeyPair(
      "f4f10e4429b739340395b34cb6b43ca91ccda3ff401dd4b9fada200979d0ac8f",
      "efab5e6617ea2576c013584968eeef2eae400f9ce7bf815d3e175c445a56a931",
      "1daacf568c1c423b002458d97593deec24209f8a0d3061b10813d5500db8257d"
    )
  }

  @Test
  fun testCase2() {
    testKeyPair(
      "34d625adcdd16d870173afd6ecb672b65f1fa7778401fd62f50bea19112dd6e9",
      "12acee435ad603287629aa6a443f2cb23d40acfc07ff5cdcb316a2b736a979d6",
      "00e5a23ad910238dcc6fef75da1a0b0e0ed1128461a6773d41ff44302c4da7f668"
    )
  }

}