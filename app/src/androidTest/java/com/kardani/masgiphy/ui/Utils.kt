package com.kardani.masgiphy.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.kardani.masgiphy.domain.model.Giph
import org.hamcrest.Matcher
import org.hamcrest.core.Is.`is`
import kotlin.random.Random

fun getRandomGiph() : Giph {
    return Giph(
        id = "id" + Random(1000),
        url = "http://randomUrl.com",
        title = "Random Giph Title",
        previewUrl = "http://randomUrl.com/preview",
        animatedUrl = "http://randomUrl.com/animated",
        favorite = true
    )
}

class RecyclerViewItemCountAssertion : ViewAssertion {
    private val matcher: Matcher<Int>

    constructor(expectedCount: Int) {
        matcher = `is`(expectedCount)
    }

    constructor(matcher: Matcher<Int>) {
        this.matcher = matcher
    }

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertThat(adapter!!.itemCount, matcher)
    }
}