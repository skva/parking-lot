package parking

fun main() {
    var spots: MutableList<String>? = null

    while (true) {
        val input: String = readln()
        val command = input.substringBefore(" ")

        when (command) {
            "create" -> createParkingLot(input).also { spots = it }
            "status" -> printParkingStatus(spots)
            "park" -> parkCar(input, spots)
            "leave" -> leaveSpot(input, spots)
            "reg_by_color" -> printRegByColor(input, spots)
            "spot_by_color" -> printSpotByColor(input, spots)
            "spot_by_reg" -> printSpotByReg(input, spots)
            "exit" -> break
            else -> println("Unknown command.")
        }
    }
}

fun createParkingLot(input: String): MutableList<String> {
    val n = input.substringAfter(" ").toInt()
    println("Created a parking lot with $n spots.")
    return MutableList(n) { "" }
}

fun printParkingStatus(spots: MutableList<String>?) {
    if (spots == null) {
        println("Sorry, a parking lot has not been created.")
    } else if (spots.all { it.isEmpty() }) {
        println("Parking lot is empty.")
    } else {
        for (i in spots.indices) {
            if (spots[i].isNotEmpty()) {
                println("${i + 1} ${spots[i]}")
            }
        }
    }
}

fun parkCar(input: String, spots: MutableList<String>?) {
    if (spots == null) {
        println("Sorry, a parking lot has not been created.")
    } else if (spots.all { it.isNotEmpty() }) {
        println("Sorry, the parking lot is full.")
    } else {
        val color = input.substringAfterLast(" ")
        val number = input.substring("park ".length, input.length - color.length - 1)
        val freeSpot = spots.indexOfFirst { it.isEmpty() }
        if (freeSpot != -1) {
            spots[freeSpot] = "$number $color"
            println("$color car parked in spot ${freeSpot + 1}.")
        }
    }
}

fun leaveSpot(input: String, spots: MutableList<String>?) {
    if (spots == null) {
        println("Sorry, a parking lot has not been created.")
    } else {
        val spotNumber: Int = input.substringAfterLast(" ").toInt()
        if (spotNumber <= spots.size) {
            spots[spotNumber - 1] = ""
            println("Spot $spotNumber is free.")
        } else {
            println("Spot $spotNumber does not exist.")
        }
    }
}

fun printRegByColor(input: String, spots: MutableList<String>?) {
    if (spots == null) {
        println("Sorry, a parking lot has not been created.")
    } else {
        val color = input.substringAfterLast(" ")
        var number: String
        var print = ""
        for (i in spots.indices) {
            if (color.equals(spots[i].substringAfter(" "), ignoreCase = true)) {
                number = spots[i].substringBefore(" ")
                print += "$number, "
            }
        }
        if (print != "") {
            println(print.dropLast(2))
        } else {
            println("No cars with color $color were found.")
        }
    }
}

fun printSpotByColor(input: String, spots: MutableList<String>?) {
    if (spots == null) {
        println("Sorry, a parking lot has not been created.")
    } else {
        val color = input.substringAfterLast(" ")
        var print = ""
        for (i in spots.indices) {
            if (color.equals(spots[i].substringAfter(" "), ignoreCase = true)) {
                print += "${i + 1}, "
            }
        }
        if (print != "") {
            println(print.dropLast(2))
        } else {
            println("No cars with color $color were found.")
        }
    }
}

fun printSpotByReg(input: String, spots: MutableList<String>?) {
    if (spots == null) {
        println("Sorry, a parking lot has not been created.")
    } else {
        val number = input.substringAfterLast(" ")
        var found = false
        for (i in spots.indices) {
            if (number == spots[i].substringBefore(" ")) {
                println(i + 1)
                found = true
                break
            }
        }
        if (!found) {
            println("No cars with registration number $number were found.")
        }
    }
}
