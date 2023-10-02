fun main() {

    //1
    val n = 1
    val m = 1000
    val result = findPalindromeSquaresInRange(n, m)

    println("Number-palindromes whose squares are also palindromes in the range $n to $m:")
    println(result.joinToString(", "))


    //2
    val number = 11403221009L
    val k = 3
    val result1 = maximizeNumber(number, k)
    println("The maximum number after removing $k digits is $result1")


    //3
    val n1 = 60
    val divisors = primeDivisors(n1)
    println("Prime divisors of $n1: ${divisors.joinToString(", ")}")
}


//1
fun isPalindrome(number: Int): Boolean {
    val numberStr = number.toString()
    return numberStr == numberStr.reversed()
}

fun findPalindromeSquaresInRange(n: Int, m: Int): List<Int> {
    val palindromeSquares = mutableListOf<Int>()

    for (i in n..m) {
        if (isPalindrome(i)) {
            val square = i * i
            if (isPalindrome(square)) {
                palindromeSquares.add(i)
            }
        }
    }

    return palindromeSquares
}

//2
fun maximizeNumber(number: Long, k: Int): Long {
    val numStr = number.toString()
    val stack = mutableListOf<Char>()
    var removedDigits = 0

    for (digit in numStr) {
        while (removedDigits < k && stack.isNotEmpty() && stack.last() < digit) {
            stack.removeAt(stack.size - 1)
            removedDigits++
        }
        stack.add(digit)
    }

    while (removedDigits < k) {
        stack.removeAt(stack.size - 1)
        removedDigits++
    }

    val resultStr = stack.joinToString("")
    return resultStr.toLong()
}


//3
fun sieveOfEratosthenes(maxValue: Int): BooleanArray {
    val isPrime = BooleanArray(maxValue + 1) { true }
    isPrime[0] = false
    isPrime[1] = false

    var p = 2
    while (p * p <= maxValue) {
        if (isPrime[p]) {
            for (i in p * p..maxValue step p) {
                isPrime[i] = false
            }
        }
        p++
    }

    return isPrime
}

fun primeDivisors(n: Int): List<Int> {
    val isPrime = sieveOfEratosthenes(n)
    val divisors = mutableListOf<Int>()

    for (i in 2..n) {
        if (isPrime[i] && n % i == 0) {
            divisors.add(i)
        }
    }

    return divisors
}
