package OOPS

import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.index
import java.lang.classfile.Attributes.code
import java.net.HttpURLConnection.HTTP_OK
import javax.print.attribute.standard.MediaSize
import kotlin.math.sqrt

open class Vehicle(val brand: String, val model: String){
    companion object{
        /*
        // static değişkenler ve fonksiyonlar
        * Üye fonksiyon (member function) veya genişletme fonksiyonu (extension function) olmalıdır.
        * Tek bir parametre almalıdır.
        * infix anahtar kelimesiyle tanımlanmalıdır.
        */
        const val className: String = "Vehicle"
        fun getClassName() = className
    }
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

/*
* Operator overloading like feature
*
* plus (+)
* minus (-)
* times (*)
* div (/)
* rem (%)
* equals (==)
* compareTo (<, >, <=, >=)
* get ([] erişimi)
* set ([] ile atama)
*/
data class Complex(
    val x: Double,
    val y: Double)
{
    operator fun plus(other: Complex): Complex = Complex(x+other.x, y+other.y)
    operator fun plus(other: Double): Complex = Complex(x+other, y+other)
    operator fun minus(other: Complex): Complex = Complex(x-other.x, y-other.y)
    operator fun minus(other: Double): Complex = Complex(x-other, y-other)
}

class CustomList<T> : Iterable<T>{
    private val items = mutableListOf<T>()

    operator fun get(index: Int): T{
        return items[index]
    }
    operator fun set(index: Int, value: T){
        if (index >= items.size){
            // pseudo code. not for real world purpose
            while (index <= items.size) {
                items.add(value)
            }
        } else {
            items[index] = value
        }
    }
    override fun hashCode(): Int {
        return items.hashCode()
    }
    fun equals(other: CustomList<T>): Boolean {
        // CustomList'lerin elemanları eşitse, eşit kabul edilir
        return this.items == other.items
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CustomList<*>) return false
        return items == other.items
    }
    fun add(item: T){
        items.add(item)
    }
    override fun toString(): String {
        return items.toString()
    }

    override fun iterator() : Iterator<T>{
        return items.iterator()
    }
}

//const val HTTP_OK = 200
const val HTTP_ERROR = 500
const val HTTP_NOT_FOUND = 404
enum class Status(val code: Int){
    Success(HTTP_OK), Error(HTTP_ERROR), NOT_FOUND(HTTP_NOT_FOUND);

    fun isSuccessful() = code == HTTP_OK
    fun isError() = code == HTTP_ERROR
    fun isNotFound() = code == HTTP_NOT_FOUND

    fun desctription(status : Status): String {
        return when(status){
            Status.Success -> "Success"
            Status.Error -> "Error"
            Status.NOT_FOUND -> "Not found"
            else -> "Not defined"
        }
    }

    fun desctription(): String {
        return when {
            this == Success -> "Success"
            this == Error -> "Error"
            else -> "Not found"
        }
    }
}

fun Int.isPrime(): Boolean {
    when{
        this<=1 -> return false
        this==2 -> return true
        this%2==0 -> return false
    }
    for (i in 3 until sqrt(this.toDouble()).toInt() step 2) {
        if (this % i == 0) return false
    }
    return true
}
operator fun String.get(range: IntRange) : String = substring(range)

fun main(args: Array<String>){
    println("Vehicle.className: ${ Vehicle.className }")
    println("Vehicle.getClassName(): ${ Vehicle.getClassName() }")
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

    println("-*-*-*-*-* Upcast - Downcast *-*-*-*-*-")
    // upcast(Car -> Vehicle)
    val myCar = Car("mycar", 3)
    val myVehicle = myCar
    myVehicle.makeSound()
    // downcast(Vehicle -> Car)
    // safe cast
    val myCar2 : Car? = myVehicle as? Car
    myCar2?.makeSound()
    if (myVehicle is Car){
        val myCar3 : Car = myVehicle as Car
        myCar3.makeSound()
    }
    // unsafe cast - without checking anything
    val myCar4 : Car = myVehicle as Car
    myCar4.makeSound()

    println("-*-*-*-*-* Operator functions (Operator overloading) *-*-*-*-*-")
    val v1 = Complex(3.0, 5.0)
    val v2 = Complex(5.0, 12.0)
    println("$v1 + $v2 = ${v1+v2}")
    println("$v1 - $v2 = ${v1-v2}")
    // specific to data class
    val (x1, y1) = v1
    val (x2, y2) = v2
    println("v1 = ($x1, $y1)")
    println("v2 = ($x2, $y2)")

    val stringList = CustomList<String>()
    val stringList2 = CustomList<String>()
    stringList.add("aaa")
    stringList.add("bbb")
    stringList.add("ccc")
    for ((index, value) in stringList.withIndex()) {
        stringList[index]= "$index-$value"
        println("stringList[$index] = ${stringList[index]} | ${stringList[index] == value}")
        println("stringList.get($index) = ${stringList.get(index)} | ${stringList.get(index) == value}")
    }
    println("stringList==stringList2 -> ${stringList==stringList2}")

    // Enum
    println("-*-*-*-*-* Enum Class *-*-*-*-*-")
    val stat = Status.Success
    println("stat: $stat")
    println("stat.desctription: ${stat.desctription()}")

    println("-*-*-*-*-* Extention functions *-*-*-*-*-")
    val a1: Int = 2000
    println("x1.isPrime(): ${a1.isPrime()}")
    val a2: Int = 997
    println("x2.isPrime(): ${a2.isPrime()}")

    // extention function - operator
    println("Mustafa Selçuk Çağlar"[2..5])

    println("-*-*-*-*-* Varargs *-*-*-*-*-")
    // varargs is just an array at runtime
    fun printAll(vararg messages : String){
        for (message in messages) print("$message, ")
    }
    fun log(vararg messages : String){
        // "*" is an expansion operator for arrays and varargs
        print("Log: ")
        printAll(*messages)
        println()
    }
    log("ytuıo", "dfghj", "ghfcjm", "HelLO", "JOhenson")

}
