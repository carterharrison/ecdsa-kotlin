```
               _           
              | |          
   ___  ___ __| |___  __ _ 
  / _ \/ __/ _` / __|/ _` |
 |  __/ (_| (_| \__ \ (_| |
  \___|\___\__,_|___/\__,_|              
                           
```

# ecdsa-kotlin


[![Maintainability](https://api.codeclimate.com/v1/badges/110f823fa82cda743eb2/maintainability)](https://codeclimate.com/github/carterharrison/ecdsa-kotlin/maintainability) [![Test Coverage](https://api.codeclimate.com/v1/badges/110f823fa82cda743eb2/test_coverage)](https://codeclimate.com/github/carterharrison/ecdsa-kotlin/test_coverage)
[![CircleCI](https://circleci.com/gh/carterharrison/ecdsa-kotlin.svg?style=shield)](https://circleci.com/gh/carterharrison/ecdsa-kotlin) [![](https://jitpack.io/v/carterharrison/ecdsa-kotlin.svg)](https://jitpack.io/#carterharrison/ecdsa-kotlin)


A simple, yet lightweight, fast elliptical curve cryptography library in kotlin.

## Supported Curves
This library comes with a plethora of curves, but do not worry! You can create your own curve to fit your cryptographic needs. Below are listed the curves that come out of the box. 

* `Secp256k1`

## Hashing
This library comes with some hashing algorithms to create signatures, you can implement your own if your favorite hashing algorithm is not included. Below are listed the hashing algorithms that come out of the box. 

* `SHA256`

## Creating a Key Pair
Creating a key pair is very simple, you may generate a random key pair, or generate one from a private key. The private key is simply a very large number.

```kotlin
// generates a random key pair on the secp256k1 curve
val randomKeys = EcKeyGenerator.newInstance(Secp256k1) 

// generates a random key pair on the secp256k1 curve with a private key
val privateKey = BigInteger(...)
val fromPrivateKey = EcKeyGenerator.newInstance(privateKey, Secp256k1) 
```

## Signing
Signing is just as easy as creating a key pair. You may sign whatever data you please.

```kotlin
// signs data [0x13, 0x37]
val data = byteArrayOf(0x13, 0x37)
val keyPair = EcKeyGenerator.newInstance(Secp256k1) 
val signature = EcSign.signData(keyPair, data, EcSha256)
```

## Verifying
After creating a signature, you can verify that the public key signed the data.

```kotlin
// verifies that the public key signed the data
val publicKey = EcPoint(...)
val signature = EcSignature(...)
val data = byteArrayOf(0x13, 0x37)
val isValid = EcSign.verifySignature(publicKey, data, EcSha256, signature)
```
