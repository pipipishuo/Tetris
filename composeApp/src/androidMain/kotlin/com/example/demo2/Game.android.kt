package com.example.demo2

import androidx.compose.ui.graphics.Color

actual fun Game.update(): Unit {
    synchronized(Game.lock) {
        var isBottom: Boolean =false;
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

        if (isBottom) {
            val shape: Array<Point> = Element.getShape()
            for (i in shape.indices) {
                var point: Point = shape[i]
                if(Game.grids[point.x][point.y].fill){
                    var i:Int =0;
                }
                Game.grids[point.x][point.y].fill = true
                Game.grids[point.x][point.y].color = Color(0, 255, 0)
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
                    for(x in 0..Game.colum-1){
                        Game.grids[x][y].fill=false
                        Game.grids[x][y].color = Color.Yellow
                    }
                }
            }
            Element.y = 0
            Element.x = 2
            Element.rotate = 0
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
        Element.x=Element.x+offset
    }
}

actual fun Game.getShape(): Array<Point> {
    synchronized(Game.lock) {
        var shape=Element.getShape()
        return shape
    }
}