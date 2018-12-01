package org.ak80.adventofcode.year2018.day01

import java.io.File

val debug = false

fun day01Part1() {
    var frequency = 0

    File("src/org/ak80/adventofcode/year2018/day01/input")
        .forEachLine { frequency += toInt(it) }

    println()
    println("The solution for day01 problem 1 is $frequency")

}


fun day01Part2() {
    var frequency = 0
    val frequencySet = mutableSetOf<Int>()
    var pass = 1

    var reader = File("src/org/ak80/adventofcode/year2018/day01/input").bufferedReader()

    if (debug) println("starting at beginning of file, pass=$pass")
    while (true) {
        val line = reader.readLine()
        if (line.isNullOrEmpty()) {
            pass += 1
            if (debug) println("continue at beginning of file, pass=$pass")
            reader = File("src/org/ak80/adventofcode/year2018/day01/input").bufferedReader()
        } else {
            frequencySet.add(frequency)
            frequency += toInt(line)
            if (frequencySet.contains(frequency)) {
                break
            }
        }

    }
    println()
    println("The solution for day01 problem 2 is $frequency")

}

fun toInt(number: String): Int {
    val sign = if (number[0] == '-') -1 else +1
    return sign * number.substring(1).toInt()
}


fun main(args: Array<String>) {
    day01Part1()
    day01Part2()
}