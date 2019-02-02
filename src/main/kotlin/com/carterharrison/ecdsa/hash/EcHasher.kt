package com.carterharrison.ecdsa.hash

interface EcHasher {
    fun hash (data : ByteArray) : ByteArray
}