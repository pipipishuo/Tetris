package com.example.demo2

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.example.demo2.ElementType.*
import kotlin.math.*
data class Point(val x: Int, val y: Int)
enum class ElementType {
    LINE,//直线
    SHAPE,//方块
    LF,   //朝左的F
    RF,   //朝右的F
    TU    //土
}
fun degToRad(degrees: Double): Double = degrees * PI / 180
fun computeRoate( p:Point, rotate:Double): Point {
    var cos: Int=cos(degToRad(rotate)).toInt()
    var sin: Int=sin(degToRad(rotate)).toInt()
    var x:Int = p.x
    var y:Int = p.y
    var rx=(x * cos - y * sin).toInt()
    var ry=(x * sin + y * cos).toInt()
    var p:Point = Point(rx, ry)
    return p
}
object Element {
    var x:Int = 0
    var y:Int = 0
    var type:ElementType=ElementType.TU
    var color: Color =Color.Green

    var rotate: Int =0       //方向 顺时针旋转 可能值 为0 90 180 270

    fun getShape(): Array<Point>{

        var offset: Array<Point> =arrayOf()
        when(type){
            LINE -> {
                var offsets: Array<Point> =arrayOf(
                    Point(-1,0),
                    Point(0,0),
                    Point(1,0),
                    Point(2,0)
                )
                offset=offsets
            }
            SHAPE -> {
                var offsets: Array<Point> =arrayOf(
                    Point(0,0),
                    Point(1,0),
                    Point(1,1),
                    Point(0,1)
                )
                offset=offsets
            }
            LF -> {
                var offsets: Array<Point> =arrayOf(
                    Point(0,0),
                    Point(1,0),
                    Point(0,1),
                    Point(0,2)
                )
                offset=offsets
            }
            RF -> {
                var offsets: Array<Point> =arrayOf(
                    Point(0,0),
                    Point(-1,0),
                    Point(0,1),
                    Point(0,2)
                )
                offset=offsets
            }
            TU -> {
                var offsets: Array<Point> =arrayOf(
                    Point(0,0),
                    Point(0,1),
                    Point(1,1),
                    Point(-1,1)
                )
                offset=offsets
            }
        }

        for (i in 0..offset.size-1){
            var point:Point =offset[i]
            point= computeRoate(point,rotate.toDouble())
            point=Point(x+point.x,y+point.y)
            offset[i]=point
        }

        return offset
    }
    fun rotate(): Unit{
        rotate=(rotate+90)%360
    }
    fun randomType(): Unit {

        val values = enumValues<ElementType>()
        val randomIndex = (0 until values.size).random()
        type = values[randomIndex]
        type=LINE
    }
}