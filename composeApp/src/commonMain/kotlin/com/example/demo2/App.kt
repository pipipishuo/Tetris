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
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val unit: Dp = maxWidth / Game.row
            val width: Dp = maxWidth;
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {


                var y by remember { mutableStateOf(0) }

                Canvas(modifier = Modifier.size(width)) {

                    val grids: Array<Array<Grid>> = Game.getGrid()

                    for (y in 0..Game.row - 1) {
                        for (x in 0..Game.colum - 1) {
                            val grid = grids[x][y]
                            drawRect(
                                color = grid.color,
                                topLeft = Offset(x * unit.toPx(), y * unit.toPx()),
                                size = Size(unit.toPx(), unit.toPx())
                            )
                        }
                    }

                    val shape: Array<Point> = Game.getShape()
                    for (i in shape.indices) {
                        var point: Point = shape[i]
                        drawRect(
                            color = Element.color,
                            topLeft = Offset(point.x * unit.toPx(), point.y * unit.toPx()),
                            size = Size(unit.toPx(), unit.toPx())
                        )
                    }
                    // 在这里进行绘制
                    y++
                }
                Row {
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
                        Game.rotate()
                    }) {
                        Text("rotate")
                    }
                }
            }
        }
    }
}