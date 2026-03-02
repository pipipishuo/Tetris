package com.example.demo2

import androidx.compose.ui.graphics.Color

object Game {
    val row:Int = 20
    val colum:Int = 5
    val grids: Array<Array<Grid>>  =Array(colum) {
        Array(row) {
            Grid(false, Color.Yellow)
        }
    }
}