package com.kardani.masgiphy.ui

import androidx.lifecycle.LiveData
import com.jraska.livedata.TestObserver
import com.kardani.masgiphy.domain.model.Giph
import kotlin.random.Random

internal fun <T> LiveData<T>.createTestObserver() : TestObserver<T>{
    val observer = TestObserver.create<T>()
    this.observeForever(observer)
    return observer
}

fun getRandomGiph() : Giph{
    return Giph(
       id = "id" + Random(1000),
       url = "http://randomUrl.com",
       title = "Random Giph Title",
       previewUrl = "http://randomUrl.com/preview",
       animatedUrl = "http://randomUrl.com/animated",
       favorite = true
    )
}

fun getGiphList(count: Int): List<Giph>{

    val list = ArrayList<Giph>()

    for (i in 1..count){
        list.add(getRandomGiph())
    }

    return list
}

fun getSearchGiphList(): List<Giph>{
    return listOf(getRandomGiph())
}


val trendingList = listOf(
    Giph(
        id = "id1",
        url = "http://randomUrl.com1",
        title = "Random Giph Title1",
        previewUrl = "http://randomUrl.com/preview1",
        animatedUrl = "http://randomUrl.com/animated1",
        favorite = true
    ),
    Giph(
        id = "id2",
        url = "http://randomUrl.com2",
        title = "Random Giph Title2",
        previewUrl = "http://randomUrl.com/preview2",
        animatedUrl = "http://randomUrl.com/animated2",
        favorite = false
    )
)


val searchedList = listOf(
    Giph(
        id = "ids1",
        url = "http://randomUrl.com1",
        title = "Random Giph Title1",
        previewUrl = "http://randomUrl.com/preview1",
        animatedUrl = "http://randomUrl.com/animated1",
        favorite = true
    ),
    Giph(
        id = "ids2",
        url = "http://randomUrl.com2",
        title = "Random Giph Title2",
        previewUrl = "http://randomUrl.com/preview2",
        animatedUrl = "http://randomUrl.com/animated2",
        favorite = false
    )
)