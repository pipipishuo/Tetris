package com.example.demo2

import androidx.compose.material3.DividerDefaults.color
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
    TU,    //土
    LZ,
    RZ
}
fun degToRad(degrees: Double): Double = degrees * PI / 180
fun computeRoate( rotate:Int,type:ElementType): Array<Point> {
    when(type) {
        LINE->{     //这个就两种
            if(rotate%2==0){
                var offsets: Array<Point> =arrayOf(
                    Point(-1,0),
                    Point(0,0),
                    Point(1,0),
                    Point(2,0)
                )
                return offsets
            }else{
                var offsets: Array<Point> =arrayOf(
                    Point(0,-1),
                    Point(0,0),
                    Point(0,1),
                    Point(0,2)
                )
                return offsets
            }
        }
        SHAPE -> { //这个就一种
            var offsets: Array<Point> =arrayOf(
                Point(0,0),
                Point(1,0),
                Point(1,1),
                Point(0,1)
            )
            return offsets
        }
        LF -> {
            when(rotate%4){
                0 ->{
                    var offsets: Array<Point> =arrayOf(
                        Point(0,0),
                        Point(0,1),
                        Point(0,-1),
                        Point(1,-1)
                    )
                    return offsets
                }
                1 ->{
                    var offsets: Array<Point> =arrayOf(
                        Point(0,0),
                        Point(1,0),
                        Point(-1,0),
                        Point(1,1)
                    )
                    return offsets
                }
                2 ->{
                    var offsets: Array<Point> =arrayOf(
                        Point(0,0),
                        Point(0,1),
                        Point(0,-1),
                        Point(-1,1)
                    )
                    return offsets
                }
                3 ->{
                    var offsets: Array<Point> =arrayOf(
                        Point(0,0),
                        Point(-1,0),
                        Point(1,0),
                        Point(-1,-1)
                    )
                    return offsets
                }
            }
        }
        RF -> {
            when(rotate%4){
                0 ->{
                    var offsets: Array<Point> =arrayOf(
                        Point(0,0),
                        Point(0,1),
                        Point(0,-1),
                        Point(-1,-1)
                    )
                    return offsets
                }
                1 ->{
                    var offsets: Array<Point> =arrayOf(
                        Point(0,0),
                        Point(1,0),
                        Point(-1,0),
                        Point(-1,1)
                    )
                    return offsets
                }
                2 ->{
                    var offsets: Array<Point> =arrayOf(
                        Point(0,0),
                        Point(0,1),
                        Point(0,-1),
                        Point(1,1)
                    )
                    return offsets
                }
                3 ->{
                    var offsets: Array<Point> =arrayOf(
                        Point(0,0),
                        Point(-1,0),
                        Point(1,0),
                        Point(1,-1)
                    )
                    return offsets
                }
            }
        }
        TU -> {
            when(rotate%4){
                0 ->{
                    var offsets: Array<Point> =arrayOf(
                        Point(0,0),
                        Point(0,1),
                        Point(1,1),
                        Point(-1,1)
                    )
                    return offsets
                }
                1 ->{
                    var offsets: Array<Point> =arrayOf(
                        Point(0,0),
                        Point(0,1),
                        Point(0,-1),
                        Point(1,0)
                    )
                    return offsets
                }
                2 ->{
                    var offsets: Array<Point> =arrayOf(
                        Point(0,0),
                        Point(0,1),
                        Point(1,0),
                        Point(-1,0)
                    )
                    return offsets
                }
                3 ->{
                    var offsets: Array<Point> =arrayOf(
                        Point(0,0),
                        Point(0,1),
                        Point(0,-1),
                        Point(-1,0)
                    )
                    return offsets
                }
            }

        }
        LZ -> {
            when(rotate%2) {
                0 -> {
                    var offsets: Array<Point> = arrayOf(
                        Point(0, 0),
                        Point(1, 0),
                        Point(0, 1),
                        Point(-1, 1)
                    )
                    return offsets
                }

                1 -> {
                    var offsets: Array<Point> = arrayOf(
                        Point(-1, -1),
                        Point(-1, 0),
                        Point(0, 0),
                        Point(0, 1)
                    )
                    return offsets
                }
            }
        }
        RZ -> {
            when(rotate%2) {
                0 -> {
                    var offsets: Array<Point> = arrayOf(
                        Point(0, 0),
                        Point(-1, 0),
                        Point(0, 1),
                        Point(1, 1)
                    )
                    return offsets
                }
                1 -> {
                    var offsets: Array<Point> = arrayOf(
                        Point(-1, 1),
                        Point(-1, 0),
                        Point(0, 0),
                        Point(0, -1)
                    )
                    return offsets
                }
            }
        }
    }
    var offsets: Array<Point> =arrayOf(
        Point(0,0)
    )
    return offsets
}
object Element {
    var x:Int = 0
    var y:Int = 0
    var type:ElementType=ElementType.TU


    var rotate: Int =0       //方向 顺时针旋转 可能值 为0 90 180 270
    var updateRotate: Int =0       //方向 顺时针旋转 可能值 为0 90 180 270

    fun getShape(): Array<Point>{

        //var offset: Array<Point> =arrayOf()
//        when(type){
//            LINE -> {
//                var offsets: Array<Point> =arrayOf(
//                    Point(-1,0),
//                    Point(0,0),
//                    Point(1,0),
//                    Point(2,0)
//                )
//                offset=offsets
//            }
//            SHAPE -> {
//
//                offset=offsets
//            }
//            LF -> {
//
//                offset=offsets
//            }
//            RF -> {
//                var offsets: Array<Point> =arrayOf(
//                    Point(0,0),
//                    Point(-1,0),
//                    Point(0,1),
//                    Point(0,2)
//                )
//                offset=offsets
//            }
//            TU -> {
//                var offsets: Array<Point> =arrayOf(
//                    Point(0,0),
//                    Point(0,1),
//                    Point(1,1),
//                    Point(-1,1)
//                )
//                offset=offsets
//            }
//        }
        var offset: Array<Point> = computeRoate(rotate,type)
        var temp: Array<Point> =arrayOf()
        for (i in 0..offset.size-1){
            var point:Point =offset[i]

            point=Point(x+point.x,y+point.y)
            temp=temp.plus(point)
        }
        offset = computeRoate(updateRotate,type)
        for (i in 0..offset.size-1){
            var point:Point =offset[i]

            point=Point(x+point.x,y+point.y)
            offset[i]=point
        }
        if (!checkBound(offset,0,0)){
            rotate=updateRotate
            return offset
        }
        if (!checkBound(offset,-1,0)){
            rotate=updateRotate
            x=x-1
            offset=setoffset(offset,-1,0)
            return offset
        }
        if (!checkBound(offset,-2,0)){
            rotate=updateRotate
            x=x-2
            offset=setoffset(offset,-2,0)
            return offset
        }
        if (!checkBound(offset,1,0)){
            rotate=updateRotate
            x=x+1
            offset=setoffset(offset,1,0)
            return offset
        }
        if (!checkBound(offset,2,0)){
            rotate=updateRotate
            x=x+2
            offset=setoffset(offset,2,0)
            return offset
        }
        return temp
    }
    fun rotate(): Unit{
        updateRotate++
        updateRotate=updateRotate%4
    }
    fun randomType(): Unit {

        val values = enumValues<ElementType>()
        val randomIndex = (0 until values.size).random()
        type = values[randomIndex]
       // type = ElementType.LINE
    }
    fun checkBound(shape: Array<Point>, xoffset:Int, yoffset:Int):Boolean{

        for(i in 0 .. shape.size-1){
            val src = shape.get(i)
            var point:Point=Point(0,0)
            point.x=src.x + xoffset
            point.y=src.y + yoffset
            if(0<=point.x&&point.x<Game.colum){
                if(0<=point.y&&point.y<Game.row){
                    if(Game.grids[point.x][point.y].fill){
                        return true
                    }
                }else{
                    return true
                }
            }else{
                return true
            }

        }
        return false
    }
    fun setoffset(shape: Array<Point>, xoffset:Int, yoffset:Int): Array<Point>{
        var ps: Array<Point> = arrayOf()
        for(i in 0 .. shape.size-1){
            var point = shape[i]
            point.x=point.x + xoffset
            point.y=point.y + yoffset
            ps=ps.plus(point)
        }
        return ps
    }
    fun getColor():Color{
        when(type){
            TU -> {
                return Color(255,105, 180)
            }

            LINE -> {
                return Color(0,255,0)
            }
            SHAPE -> {
                return Color(0,0,255)
            }
            LF -> {
                return Color(139, 69, 19)
            }
            RF -> {
                return Color(0,255,255)
            }
            LZ -> {
                return Color(255,0,255)
            }
            RZ -> {
                return Color(255,125,125)
            }
        }
    }
}