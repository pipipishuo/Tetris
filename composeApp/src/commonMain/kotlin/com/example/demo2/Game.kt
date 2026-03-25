package com.example.demo2

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



object Game {
    var first:Boolean = true
    val lock = Any()
    val row:Int = 20
    val colum:Int = 13

    var score:Int = 0

    var paused:Boolean = false
    val grids: Array<Array<Grid>>  =Array(colum) {
        Array(row) {
            Grid(false, Color.Yellow)
        }
    }

    var isOver:Boolean =false
    fun isBottom():Boolean{
        var isBottom: Boolean=false
        val shape1:Array<Point> = Element.getShape()
        for (i in shape1.indices) {
            var point:Point =shape1[i]
            if((point.y+1)== Game.row){
                isBottom = true
                break;
            }

            if(Game.grids[point.x][point.y+1].fill){
                isBottom = true
                break;
            }

        }
        return isBottom;
    }
}
expect fun Game.getGrid(): Array<Array<Grid>>
expect fun Game.update(): Unit
expect fun Game.rotate(): Unit
expect fun Game.move(offset:Int): Unit
expect fun Game.getShape(): Array<Point>
expect fun Game.down(): Unit
expect fun Game.getScore(): Int
expect fun Game.getIsOver(): Boolean
expect fun Game.restart(): Unit
@Composable
expect fun Game.quit(): Unit

expect fun Game.getPause(): Boolean;

expect fun Game.setPause(flag: Boolean): Unit;