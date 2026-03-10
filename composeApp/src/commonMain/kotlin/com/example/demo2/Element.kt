package com.example.demo2

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.example.demo2.ElementType.*
import kotlin.math.*
data class Point(var x: Int, var y: Int)
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
    var updateRotate: Int =0       //方向 顺时针旋转 可能值 为0 90 180 270

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
        var temp: Array<Point> =arrayOf()
        for (i in 0..offset.size-1){
            var point:Point =offset[i]
            point= computeRoate(point,rotate.toDouble())
            point=Point(x+point.x,y+point.y)
            temp=temp.plus(point)
        }
        for (i in 0..offset.size-1){
            var point:Point =offset[i]
            point= computeRoate(point,updateRotate.toDouble())
            point=Point(x+point.x,y+point.y)
            offset[i]=point
        }
        //如果不偏移就满足条件  那就不偏移
        if(!checkBound(offset,0,0)){
            rotate=updateRotate
            return offset
        }

//        if(!checkBound(offset,1,0)){
//            rotate=updateRotate
//            return setoffset(offset,1,0)
//        }
//
//        if(!checkBound(offset,2,0)){
//            rotate=updateRotate
//            return setoffset(offset,2,0)
//        }
//
//        if(!checkBound(offset,-1,0)){
//            rotate=updateRotate
//            return setoffset(offset,1,0)
//        }
//
//        if(!checkBound(offset,-2,0)){
//            rotate=updateRotate
//            return setoffset(offset,-2,0)
//        }
//        if(!checkBound(offset,0,-1)){
//            rotate=updateRotate
//            return setoffset(offset,0,-1)
//        }
//        if(!checkBound(offset,0,-2)){
//            rotate=updateRotate
//            return setoffset(offset,0,-2)
//        }

        return temp
    }
    fun rotate(): Unit{
        updateRotate=(updateRotate+90)%360

    }
    fun randomType(): Unit {

        val values = enumValues<ElementType>()
        val randomIndex = (0 until values.size).random()
        type = values[randomIndex]
        type=LINE
    }
    fun checkBound(shape: Array<Point>, xoffset:Int, yoffset:Int):Boolean{

        for(i in 0 until shape.size-1){
            var point = shape[i]
            point.x=point.x + xoffset
            point.y=point.y + yoffset
            if(0<=point.x&&point.x<Game.colum){
                if(0<=point.y&&point.x<Game.row){
                    if(Game.grids[point.x][point.y].fill){
                        return true
                    }
                }else{
                    return false
                }
            }else{
                return false
            }

        }
        return false
    }
    fun setoffset(shape: Array<Point>, xoffset:Int, yoffset:Int): Array<Point>{
        var ps: Array<Point> = arrayOf()
        for(i in 0 until shape.size-1){
            var point = shape[i]
            point.x=point.x + xoffset
            point.y=point.y + yoffset
            ps=ps.plus(point)
        }
        return ps
    }
}