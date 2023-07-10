package com.example.mycoroutines

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import java.util.Random

class PersonRepository {

    fun generateItemsFlow(): Flow<Item> = flow {
        for (i in 1..50){
            val random = Random().nextInt(2)
            val item: Item = if (random == 0) {
                createRandomPeople()
            } else {
                createRandomSheep()
            }
            emit(item)
            delay(1000) // Задержка в 1 секунду
        }
    }
    private fun createRandomPeople(): Item.PeopleItem {
        val names = listOf("John", "Alice", "Bob", "Emma", "Mike")
        val ages = (18..60).random()
        return  Item.PeopleItem(People(names.random(), ages))
    }

    private fun createRandomSheep(): Item.ShipItem {
        val names = listOf("Fluffy", "Snowball", "Woolly", "Baa Baa", "Lambchop")
        val lengths = (1..10).random()
        return Item.ShipItem(Ship(names.random(), lengths))
    }
    val ships = mutableListOf<Ship>(
        Ship("Aurora", 25, 1),
        Ship("Minsk", 25, 2),
        Ship("Ranger", 25, 3),
        Ship("Adventure", 25, 4)
    )
    val peoples = mutableListOf<People>(
        People("Ivan", 25, 1),
        People("Anton", 25, 2),
        People("Dmitriy", 25, 3),
        People("Oleg", 25, 4),
    )
}
