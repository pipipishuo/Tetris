package com.example.demo2

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

import demo2.composeapp.generated.resources.Res
import demo2.composeapp.generated.resources.compose_multiplatform
import com.example.demo2.Grid
import kotlinx.coroutines.sync.withLock


@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var val1 by remember{mutableStateOf(0)}
        var score by remember { mutableStateOf("分数:"+Game.score.toString()) }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {


//                Canvas(modifier = Modifier.fillMaxWidth().weight(0.1f)){
//
//                    drawText()
//                }

// 单个子项右对齐
                Column(
                    modifier =  Modifier.fillMaxHeight(0.1f).fillMaxWidth()
                ) {
                    Text("", modifier = Modifier.align(Alignment.Start))

                    Text("", modifier = Modifier.align(Alignment.CenterHorizontally))

                    Text(modifier = Modifier.align(Alignment.End), text = score)

                }

                var y by remember { mutableStateOf(0) }
                Canvas(modifier = Modifier.fillMaxHeight(0.8f).fillMaxWidth()) {
                    score="分数:"+Game.score.toString()
                    val unit: Float =size.width/Game.colum
                    val grids: Array<Array<Grid>> = Game.getGrid()

                    for (y in 0..Game.row - 1) {
                        for (x in 0..Game.colum - 1) {
                            val grid = grids[x][y]
                            drawRect(
                                color = grid.color,
                                topLeft = Offset(x * unit+2, y * unit+2),
                                size = Size(unit-4, unit-4)
                            )
                        }
                    }

                    val shape: Array<Point> = Game.getShape()
                    for (i in shape.indices) {
                        var point: Point = shape[i]
                        val color: Color = Element.getColor()
                        drawRect(
                            color = color,
                            topLeft = Offset(point.x * unit+2, point.y * unit+2),
                            size = Size(unit-8, unit-8)
                        )
                    }
                    // 在这里进行绘制
                    y++
                }
                Row(modifier = Modifier.fillMaxHeight(0.2f).fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {

                    Button(onClick = {
                        Game.move(-1)
                    }) {
                        Text("left")
                    }
                    Button(onClick = {
                        Game.move(1)
                    }) {
                        Text("right")
                    }
                    Button(onClick = {
                        Game.down()
                    }) {
                        Text("down")
                    }
                    Button(onClick = {
                        Game.rotate()
                    }) {
                        Text("rotate")
                    }
                }
            }

    }
}