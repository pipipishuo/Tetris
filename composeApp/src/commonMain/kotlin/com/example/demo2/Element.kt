package com.example.demo2

import androidx.compose.ui.graphics.Color

enum class ElementType {
    LINE,SHAPE
}
object Element {
    var x:Int = 0
    var y:Int = 0
    var type:ElementType=ElementType.LINE
    var color: Color =Color.Green
}