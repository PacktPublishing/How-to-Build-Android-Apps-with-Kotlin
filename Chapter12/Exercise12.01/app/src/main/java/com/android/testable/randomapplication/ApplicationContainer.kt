package com.android.testable.randomapplication

import java.util.*

class ApplicationContainer {

    val numberRepository: NumberRepository = NumberRepositoryImpl(Random())
}