package com.carterharrison.ecdsa.curves

import com.carterharrison.ecdsa.EcKeyGenerator
import com.carterharrison.ecdsa.EcPoint
import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals

class Secp256k1Test {

  private fun testKeyPair(x: String, y: String, p: String) {
    val expectedPublic = EcPoint(BigInteger.parseString(x, 16), BigInteger.parseString(y, 16), Secp256k1)
    val privateKey = BigInteger.parseString(p, 16)
    val keypair = EcKeyGenerator.newInstance(privateKey, Secp256k1)

    assertEquals(expectedPublic, keypair.publicKey)
  }


  @OptIn(ExperimentalUnsignedTypes::class)
  fun ByteArray.toHexString(): String = asUByteArray().joinToString("") { it.toString(radix = 16).padStart(2, '0') }

  @Test
  fun testCase1() {
    testKeyPair(
      "c1ed21b77a534b4a606de0d429c7bdd5112dd338c98676142598bf59e7f2f398",
      "650a8b8b7f03600e98afecb1be86349f775ac00872b93f00d4a49cec27237030",
      "a02235453ffd4ed5b29c3be7eae70a9701a47da2c47e2296a97132a65ea1a8d4"
    )
  }

  @Test
  fun testCase2() {
    testKeyPair(
      "32e0c671fef6f75bb00d4a010b69977a01f8bb605955e54de99d27b370f7d327",
      "5bf85eb2ea93097642a98388cbdc26d2b9d246535ff12fecbcf0fedb45ce003b",
      "5d335648023f8ec5fe5a3dfd1a6d1e1e463816690ee0d9392138fe04e54b7ea0"
    )
  }

  @Test
  fun testCase3() {
    testKeyPair(
      "20a53a720d34fcb2586b6f7a1b14cc0eb1e4b763f4805465815c0c1c3bd7a06d",
      "b721f75f16b912e2db07af1d155f0d633734a0d2cfc1ecb10fa76dc0f249ca4d",
      "06fdded00ddc6a3250973b1f994375f7208de58b1fc0585e55bc485a9e1c6a3b"
    )
  }

  @Test
  fun testCase4() {
    testKeyPair(
      "F73C65EAD01C5126F28F442D087689BFA08E12763E0CEC1D35B01751FD735ED3",
      "F449A8376906482A84ED01479BD18882B919C140D638307F0C0934BA12590BDE",
      "1B22644A7BE026548810C378D0B2994EEFA6D2B9881803CB02CEFF865287D1B9"
    )
  }

//    @Test
//    fun testSign() {
//        val key = EcKeyGenerator.newInstance(Secp256k1)
//        val sign = EcSign.signData(key, byteArrayOf(0x00, 0x00, 0x00, 0x01, 0x01), EcSha256)
//
//        println(EcSign.verifySignature(key.publicKey, byteArrayOf(0x00, 0x00, 0x00, 0x01, 0x01), EcSha256, sign))
//
//
//        println(key.publicKey.x.toByteArray().toHexString())
//        println(key.publicKey.y.toByteArray().toHexString())
//
//        println(sign.r.toByteArray().toHexString())
//        println(sign.s.toByteArray().toHexString())
}