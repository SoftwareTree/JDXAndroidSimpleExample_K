package com.softwaretree.jdxandroidsimpleexample.model

import java.util.Date

class ClassA {
    var aId: Int = 0
    var aString: String? = null
    var aDate: Date? = null
    var aBoolean: Boolean = false
    var aFloat: Float = 0.toFloat()

    /**
     * Default no-arg constructor needed for JDX
     */
    constructor() {
    }

    constructor(aId: Int, aString: String, aDate: Date, aBoolean: Boolean, aFloat: Float) {
        this.aId = aId
        this.aString = aString
        this.aDate = aDate
        this.aBoolean = aBoolean
        this.aFloat = aFloat
    }

}
