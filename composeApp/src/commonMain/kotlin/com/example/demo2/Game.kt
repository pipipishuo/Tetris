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

    fun isBottom():Boolean{
        val shape:Array<Point> = Element.getShape()
        for (i in shape.indices) {
            var point:Point =shape[i]
            if((point.y+1)== Game.row){
                return true
            }

            if(Game.grids[point.x][point.y+1].fill){
                return true
            }

        }
        return false
    }
}