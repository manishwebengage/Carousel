package com.webengage.carousel.main

import java.nio.charset.Charset
import java.util.Random


object Hash {
    @JvmStatic
    fun main(args: Array<String>) {
        val lowerRange = 4 //
        val higherRange = 4
        val totalUserids = 2
        val userIdLength = 3
        val userIds: MutableList<String> = ArrayList()
        while (userIds.size < totalUserids) {
            val userId = RandomString.getAlphaNumericString(userIdLength)
            val _hash = userId.hashCode() % 100
            if (_hash >= lowerRange && _hash <= higherRange) {
                userIds.add(userId)
            }
        }
        for (i in userIds.indices) {
            println("Userids " + userIds[i])
        }
    }

    internal object RandomString {
        fun getAlphaNumericString(n: Int): String {
            // length is bounded by 256 Character

            var n = n
            val array = ByteArray(256)
            Random().nextBytes(array)

            val randomString = String(array, Charset.forName("UTF-8"))

            // Create a StringBuffer to store the result
            val r = StringBuffer()

            // Append first 20 alphanumeric characters
            // from the generated random String into the result
            for (k in 0 until randomString.length) {
                val ch = randomString[k]

                if (((ch >= 'a' && ch <= 'z')
                            || (ch >= 'A' && ch <= 'Z')
                            || (ch >= '0' && ch <= '9'))
                    && (n > 0)
                ) {
                    r.append(ch)
                    n--
                }
            }

            // return the resultant string
            return r.toString()
        }
    }
}
