package com.carterharrison.ecdsa

import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals

class EcConstantsTest {

  @Test
  fun testConstants() {
    assertEquals(EcConstants.ZERO, BigInteger.fromInt(0))
    assertEquals(EcConstants.ONE, BigInteger.fromInt(1))
    assertEquals(EcConstants.TWO, BigInteger.fromInt(2))
    assertEquals(EcConstants.THREE, BigInteger.fromInt(3))
  }

}