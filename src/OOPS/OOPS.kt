package OOPS

open class Vehicle(val brand: String, val model: String){
    init {
        println("Vehicle: $brand, $model")
    }

    constructor(brand: String) : this(brand, "Unknown"){
        println("Model Unknown")
    }
    open fun makeSound(){
        println("VVVVVVVVVVVVVVVVVVVVVVV")
    }
}

class Car(brand: String, model: String, val doors: Int) : Vehicle(brand, model) {
    init {
        println("Number of doors: $doors")
    }

    constructor(brand: String, doors: Int) : this(brand, "Unkown", doors) {
        println("Model unknown, using default model")
    }
    override fun makeSound(){
        println("CCCCCCCCCCCCCCCCCCCCCCC")
    }
}

class Plane(brand: String, model: String, val doors: Int) : Vehicle(brand, model) {
    init {
        println("Number of doors: $doors")
    }

    constructor(brand: String, doors: Int) : this(brand, "Unkown", doors) {
        println("Model unknown, using default model")
    }
    override fun makeSound(){
        println("PPPPPPPPPPPPPPPPPPPPPPP")
    }
}

fun main(args: Array<String>){
    val vehicle1 = Vehicle("Toyota", "Corolla")
    val vehicle2 = Vehicle("Honda")

    val car1 = Car("Ford", "Focus", 4)
    val car2 = Car("BMW", 2)

    val plane1 = Plane("Airbus", "300A", 4)
    val plane2 = Plane("Airbus", 4)

    // all classes makes their own sound
    val vehicleList = listOf(vehicle1, vehicle2, car1, car2, plane1, plane2)
    for (vehicle in vehicleList)
        vehicle.makeSound()

}
