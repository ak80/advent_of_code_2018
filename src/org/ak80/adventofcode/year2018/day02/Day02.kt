package org.ak80.adventofcode.year2018.day02

import java.io.File

val debug = true

var lettersTwiceCount = 0
var lettersThriceCount = 0
val charMap = mutableMapOf<Char, Int>()

fun day02Part1() {
    File("src/org/ak80/adventofcode/year2018/day02/input")
        .forEachLine { processChecksum(it) }


    val checksum = lettersTwiceCount * lettersThriceCount;
    println()
    println("The solution for day02 problem 1 is $checksum")

}


fun processChecksum(line: String) {
    charMap.clear()
    for (char in line) {
        val newCount = charMap.getOrDefault(char, 0) + 1
        charMap.put(char, newCount)
    }

    if (charMap.values.contains(2)) {
        lettersTwiceCount += 1
    }

    if (charMap.values.contains(3)) {
        lettersThriceCount += 1
    }

}

val checksums = mutableSetOf<String>()
val similarChecksums = mutableSetOf<String>()

fun day02Part2() {
    File("src/org/ak80/adventofcode/year2018/day02/input")
        .forEachLine { checksums.add(it) }

    checksums.forEach { findValidChecksum(it) }
    val validPart = findValidPart(similarChecksums)

    println()
    println("The solution for day02 problem 2 is $validPart")
}

fun findValidPart(similarChecksums: MutableSet<String>): String {
    val checksumsToScan = similarChecksums.toList();
    var validPart = ""
    for (i in 0..checksumsToScan[0].length - 1) {
        val char = checksumsToScan[0][i]
        if (sameInAll(char, similarChecksums, i)) {
            validPart = validPart + char
        }
    }
    return validPart
}

fun sameInAll(char: Char, similarChecksums: MutableSet<String>, i: Int): Boolean {
    for (checksum in similarChecksums) {
        if (checksum[i] != char) {
            return false
        }
    }
    return true
}

fun findValidChecksum(checksum: String) {
    for (otherChecksum in checksums) {
        if (checksum == otherChecksum) {
            continue
        }
        val differentChars = findDifferentChars(checksum, otherChecksum)
        if (differentChars == 1) {
            if (debug) println("add $checksum")
            similarChecksums.add(checksum)
        }
    }
}

fun findDifferentChars(checksum: String, otherChecksum: String): Int {
    if (debug) println("find diff in $checksum vs $otherChecksum")
    var differentChars = 0
    for (i in 0..checksum.length - 1) {
        if (checksum[i] != otherChecksum[i]) {
            differentChars += 1
        }
    }
    return differentChars
}

fun main(args: Array<String>) {
    day02Part1()
    day02Part2()

}