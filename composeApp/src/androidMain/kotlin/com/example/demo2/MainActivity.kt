package com.example.demo2

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.demo2.Element.rotate
import kotlin.concurrent.thread


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val job = thread {
            Element.x=2
            Element.y=0

            while(true){
                Thread.sleep(100)



                if (Game.isBottom()){      //到达底部时
                    val shape:Array<Point> = Element.getShape()
                    for (i in shape.indices) {
                        var point:Point =shape[i]
                        Game.grids[point.x][point.y].fill=true
                        Game.grids[point.x][point.y].color=Color(0,255,0)
                    }
                    Element.y=0
                    Element.rotate=0
                    Element.randomType()
                }else{
                    Element.y++
                }



                //Log.d(TAG, "这是 Debug 日志，开发调试用")
            }

        }
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}