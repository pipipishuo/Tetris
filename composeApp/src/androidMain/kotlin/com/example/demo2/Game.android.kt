package com.example.demo2

import android.app.Activity
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import kotlin.math.max
import kotlin.math.min
import androidx.compose.ui.platform.LocalContext
actual fun Game.update(): Unit {
    synchronized(Game.lock) {
        var isBottom: Boolean =Game.isBottom();
        if (isBottom) {
            val shape: Array<Point> = Element.getShape()
            val color: Color = Element.getColor()
            for (i in shape.indices) {
                var point: Point = shape[i]
                if(point.x>=0&&point.x < Game.colum&&point.y>=0&&point.y < Game.row) {
                    if(Game.grids[point.x][point.y].fill){
                        isOver=true;
                    }
                    Game.grids[point.x][point.y].fill = true
                    Game.grids[point.x][point.y].color =color
                }else{
                    isOver=true
                }
            }

            for(y in 0..Game.row-1){
                var isFill:Boolean =false;
                var c:Int =0;
                for(x in 0..Game.colum-1){
                    if(Game.grids[x][y].fill){
                        c++;
                    }
                }
                if(c==Game.colum){        //判断某一行是否
                    isFill=true
                }
                if(isFill){
                    for(ty in y downTo  0){
                        for(x in 0..Game.colum-1){
                            if(ty!=0){
                                Game.grids[x][ty].fill=Game.grids[x][ty-1].fill
                                Game.grids[x][ty].color = Game.grids[x][ty-1].color
                            }else{
                                Game.grids[x][ty].fill=false
                                Game.grids[x][ty].color = Color.Yellow
                            }

                        }
                    }
                    score++
                }
            }
            Element.y = 0
            Element.x = 7
            Element.rotate = 0
            Element.updateRotate = 0
            Element.randomType()
        } else {
            Element.y++
        }
    }
}
actual fun Game.getGrid(): Array<Array<Grid>> {
    synchronized(Game.lock) {
        return Game.grids
    }
}

actual fun Game.rotate() {
    synchronized(Game.lock) {
        Element.rotate()
    }
}

actual fun Game.move(offset: Int) {
    synchronized(Game.lock) {
        val temp:Int= Element.x
        Element.x=Element.x+offset
        Element.x=max(0,Element.x);
        Element.x=min(Game.row-1,Element.x);

    }
}

actual fun Game.getShape(): Array<Point> {
    synchronized(Game.lock) {
        var shape=Element.getShape()
        return shape
    }
}

actual fun Game.down() {
    synchronized(Game.lock) {
        var isbottom=Game.isBottom()
        if(!isbottom){
            Element.y++
        }
    }
}

actual fun Game.getScore(): Int {
    synchronized(Game.lock) {
        return score
    }
}

actual fun Game.getIsOver(): Boolean {
    synchronized(Game.lock) {
        return isOver
    }
}

actual fun Game.restart() {
    synchronized(Game.lock) {
        Element.x = 7
        Element.y = 0
        isOver=false
        for(y in 0..Game.row-1) {

            for (x in 0..Game.colum - 1) {
                Game.grids[x][y].fill=false
                Game.grids[x][y].color = Color.Yellow
            }
        }
    }
}
@Composable
actual fun Game.quit() {
    val context = LocalContext.current
    Button(
        onClick = {
            (context as? Activity)?.finish()
        }
    ) {
        Text("退出应用")
    }
}