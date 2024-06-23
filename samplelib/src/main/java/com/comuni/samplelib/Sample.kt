package com.comuni.samplelib

import com.comuni.samplelib.Sample


fun main() {

    val sam = Sample()
    val detils = sam.getDetails()
    println(detils)

    sam.setDetails("Charles", 25, false)
//    sam.name = "Leo"
    val detils2 = sam.getDetails()
    println(detils2)

    sam.addPerson()
    println("Hello, World!")
}

// String , Char
// Int , Long
// float, Doubt
// Boolean


abstract class Person{
    var height: Int = 0
    var weight: Int = 0
    var bmi: Double = 0.0


    // Array : Fixed size
    // Lists : Immutable
    // MutableList : Mutable



    init {



    }

    open fun calculateBms(height: Int, weight: Int)  {
        this.height = height
        this.weight = weight

        val bmi =  height + height
        this.bmi = bmi.toDouble()
        println(bmi)
    }

    abstract fun UsePerson()
}

interface PersonInterface {
    fun addPerson()
    fun removePerson()
    fun updatePerson()
}


class Sample : Person() , PersonInterface {
    private var name: String = "Raj"


    val number = arrayOf(1,2,3,4,5) // 0,1,2,3,4 : size = 5
    var list = listOf(1,2,3,4,5) // 1,2,3,4,5 : size = 5
    var mutableList = mutableListOf(1,2,3,4,5) // 1,2,3,4,5 : size = 5
    init {

//        for (i in 0 until  number.size){
//            if (!(number[i] > 3)) {
//                println(number[i])
//            }
//        }
//        for (i in 0..10){
//            println(i * 2)
//        }
//        for (index in number.indices){
//            println(index)
//        }
//        for ((index, value)  in number.withIndex()){
//            println(" Index : $index value : $value")
//        }
//        number.forEachIndexed{ index, value ->
//            println(" Index : $index value : $value")
//        }
//        number.forEach {
//            println(it)
//        }

        var i = 0
        var limit = 10

        do {
            if ((i % 2) == 0){
                println("The value is $i")
            }
            i++
        } while (i <= limit)

        while (i <= limit){
            if ((i % 2) == 0){
                println("The value is $i")
            }
            i++
        }

        println(getMaxValue(4,9))
        println(findType(0))
    }


    fun getMaxValue(a: Int, b: Int) :
            Int = if (a > b) {

             a
        } else {
             b
        }

    fun findType(obj : Any) :
           String = when (obj) {
               1 -> "One"
        is Int -> "Int"
        is Long -> "Long"
        is String -> "String"
        else -> "Unknown"
           }



    public fun getName(): String {
        return name
    }

    public fun setName(name: String) {
        this.name = name
    }


    var age: Int = 23
    var isMarried: Boolean = false


    override fun UsePerson() {

    }

    fun setDetails(name: String, age: Int, isMarried: Boolean) {
        this.name = name
        this.age = age
        this.isMarried = isMarried

//        println(if (name == "raj" ) "Name is Raj" else if (name == "muthu") "Name is Muthu" else "Name is not Raj")
//
//        if (name == "raj") println("Name is Raj") else if (name == "muthu") println("Name is Muthu") else println("Name is not Raj")
//
//        when (name) {
//            "raj" -> {
//                println("Name is Raj")
//            }
//            "muthu" -> {
//                println("Name is Muthu")
//            }
//            "leo" -> println("Name is Leo")
//            else -> println("Name is not Raj")
//        }
//        when  {
//             5 + 5 = 10 -> {
//                println("Name is Raj")
//            }
//            "muthu" -> {
//                println("Name is Muthu")
//            }
//            "leo" -> println("Name is Leo")
//            else -> println("Name is not Raj")
//        }


    }

    fun getDetails(): String {
        calculateBms(56,79)
//        val detils = "Name : " + name + "Age : $age Is Married : $isMarried"
        val detils = "Name : $name Age : $age Is Married : $isMarried  BMI : $bmi, Height : $height, Weight : $weight"

        return detils
    }

    override fun addPerson() {

    }

    override fun removePerson() {

    }

    override fun updatePerson() {

    }

}



