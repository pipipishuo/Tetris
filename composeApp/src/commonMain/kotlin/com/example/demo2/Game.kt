package com.example.demo2

import androidx.compose.ui.graphics.Color



object Game {
    val lock = Any()
    val row:Int = 20
    val colum:Int = 5
    val grids: Array<Array<Grid>>  =Array(colum) {
        Array(row) {
            Grid(false, Color.Yellow)
        }
    }

    fun isBottom():Boolean{
        return true;
    }

}
expect fun Game.getGrid(): Array<Array<Grid>>
expect fun Game.update(): Unit
expect fun Game.rotate(): Unit
expect fun Game.move(offset:Int): Unit
expect fun Game.getShape(): Array<Point>