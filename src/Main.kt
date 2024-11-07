import java.sql.Time
import java.util.*
import kotlin.math.pow
import kotlin.random.Random

fun sum(a: Int, b: Int) : Int {
    return a+b
}

fun sum2(a: Int, b: Int) : Int = a+b

fun toSecond(time: String): (Int) -> Int = when(time) {
    "hour" -> {value->value*60*60}
    "minute" -> {value->value*60}
    "second" -> {value->value}
    else -> {value->value}
}

fun repeatN(n: Int, action: () -> Unit) {
    // Write your code here
    for (i in 1..5)
    	action()
}

class Contact1(val id: Int, var email: String)
class Contact(val id: Int, var email: String){
    val category: String = "work"
}

data class Employee(val name: String, var salary: Int)

class RandomEmployeeGenerator(var minSalary: Int, var maxSalary: Int) {
    private val names = listOf("a", "b", "c", "d", "e")

    // Fonksiyon, Employee döndüren bir lambda fonksiyonu döndürür.
    fun generateEmployee(): Employee {
        val name = names.random()
        val salary = Random.nextInt(minSalary, maxSalary)
        return Employee(name, salary)
    }
}

open class Animal(val name: String) {
    open fun makeNoise() {
        println("$name bir ses çıkarıyor.")
    }
}

class Dog(name: String): Animal(name){
    override fun makeNoise(){
        println("Hav Hav!")
    }
}

fun dayOfWeek(){
    print("Haftanın hangi günündeyiz?: ")
    val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    val dayname = when(day){
        1 -> "Pazar"
        2 -> "Pazartesi"
        3 -> "Salı"
        4 -> "Çarşamba"
        5 -> "Perşembe"
        6 -> "Cuma"
        7 -> "Cumartesi"
        else -> "Zaman durdu herhalde"
    }
    println(dayname)

}

fun userInput(){
    // -*-*-*-*-* User Input *-*-*-*-*-
    print("What is your name?: ")
    val yourWord = readln()
    println("Hello $yourWord")
}

fun main(args: Array<String>) {
    println("Hello World");

    val uppercase = {test: String -> test.uppercase()}
    println(uppercase("measdrklersASDSGsdgsdfgSDFGSDFHSDFh"))
    println({test: String -> test.uppercase()}("Hello World"))

    val numbers = listOf(1, -2, 3, -4, 5, -6)
    val evens = numbers.filter({x->x%2==0})
    val positiveEvens = numbers.filter({x->x>0})
    val isNegatives = {x: Int -> x < 0}
    val negatives = numbers.filter(isNegatives)

    val isTripled: (Int) -> Int = { x: Int -> x * 3}
    val tripled = numbers.map (isTripled)

    val printThings: (String) -> Unit = { str: String -> println(str)}

    println("positiveEvens: $positiveEvens")
    println("negatives: $negatives")
    println("isTripled: $isTripled")
    println("tripled: $tripled")


    val timesInMinutes = listOf(2,10,15,1)
    val min2sec = toSecond("minute")
    val totalTimeInSeconds = timesInMinutes.map(min2sec).sum()
    println("totalTimeInSeconds: $totalTimeInSeconds")

    val contact = Contact(1, "asfasdf")

    data class User(val name: String, val id: Int)

    val user1 = User("Mustafa", 1)
    val user2 = User("Selçuk", 2)
    val user3 = User("Çağlar", 3)
    val usersList = listOf(user1, user2, user3)
    val usersMap = usersList.associateBy { it.id }
    println("usersList: $usersList")
    println("usersMap: $usersMap")

    println("$user1 == $user1")
    println("$user1 == $user2")
    println("$user1")
    println(user1.toString())
    val user4 = user1.copy() // copy same object
    val user5 = user1.copy("Ali") // copy with Ali
    val user6 = user1.copy(id = 111) // copy with id 111

    println("user4: $user4, user5: $user5, user6: $user6")



    // RandomEmployeeGenerator nesnesi oluşturuluyor.
    val empGen = RandomEmployeeGenerator(10, 30)

    // Çalışanlar oluşturuluyor.
    println(empGen.generateEmployee())
    println(empGen.generateEmployee())
    println(empGen.generateEmployee())

    // Minimum ve maksimum maaş güncelleniyor.
    empGen.minSalary = 50
    empGen.maxSalary = 100

    // Yeni maaşlarla çalışan oluşturuluyor.
    println(empGen.generateEmployee())

    val swarm = listOf(15, 17)
    val bigSwarm = arrayOf(swarm, arrayOf("aaa", "bbb", "ccc"))
    println(Arrays.toString(bigSwarm))
    println( Array(5){it*2} ) // indexes=[0,1,2,3,4] => 0->0, 1->2, 2->4, 3->6, 4->8

    for ((index, element) in swarm.withIndex()){
        println("Fish at $index is $element")
    }
    for (i in 'a'..'e') print(i)
    println()
    for (i in 1..5) print(i)
    println()
    for (i in 5 downTo  1) print(i)
    println()
    for (i in 10 downTo  3 step 3) print(i)
    println()
    repeat(5){
        println("Hello")
    }
    println()
    repeatN(5, {println("Hello")})
    repeatN(5) {
        println("Hello")
    }
    println()
    // labeled loop: dışarıdaki etiketli döngüden çıkar. Akış dış döngünün sonuna gelir.
    outer@ for (i in 1..3) {
    for (j in 1..3) {
        if (i == 2 && j == 2) {
            break@outer // outer döngüsünü sonlandırır
           }
            println("i: $i, j: $j")
        }
    }
    println()

    val sizes = arrayOf("byte", "kilobyte", "megabyte", "gigabyte", "terabyte", "petabyte", "exabyte")
    val array = Array(sizes.size) {1000.0.pow(it)}
    for ((index, element) in array.withIndex()) {
        println("1 ${sizes[index]} = ${element.toLong()} bytes")
    }

    dayOfWeek()

    for ((index, arg) in args.withIndex())
        print("arg[$index]: $arg, ")
    println()


    //userInput()


    /*
    filter: Bir listeyi filtrelemek için kullanılır.
    map: Her bir eleman üzerinde dönüşüm uygulamak için kullanılır.
    forEach: Her bir elemanı teker teker işler.
    find: Belirli bir şartı sağlayan ilk öğeyi bulur.
    sort: Listeyi sıralamak için kullanılır.
    */
    val numbers2 = (1..5).toList()
//    println(numbers2.filter({x -> x>3}))
    println("filter: " + numbers2.filter{ it > 3})
    println("map: " + numbers2.map{ it * 3})
    numbers2.forEach { print("$it, ") }
    println()
    println("find: " + numbers2.find { it > 4 })
    println("sorted: " + numbers2.sorted())
    println("sortedDescending: " + numbers2.sortedDescending())




}
