package com.example.demo2

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.SwipeToDismissBoxValue.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DoneSegment
import androidx.compose.ui.input.pointer.pointerInput
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.currentStateAsState
import org.jetbrains.compose.resources.painterResource

import demo2.composeapp.generated.resources.Res
import demo2.composeapp.generated.resources.compose_multiplatform
import com.example.demo2.Grid
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.withLock


@Composable
@Preview
fun App() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateAsState()

    when (lifecycleState) {
        Lifecycle.State.RESUMED -> Text("前台活跃中")
        Lifecycle.State.STARTED -> Text("可见但无焦点")
        Lifecycle.State.CREATED -> {
            println("已创建但不可见")
        }
        else -> Text("其他状态")
    }

    MaterialTheme {

        var score by remember { mutableStateOf("分数:"+Game.score.toString()) }
        var showDialog by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .safeContentPadding()
                    .fillMaxSize()
                    ,

                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                //SwipeableCardModern()

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
                if(showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("提示") },
                        text = { Text("游戏结束") },
                        confirmButton = {
                            TextButton(onClick = {
                                // 确认操作
                                Game.restart()
                                showDialog=false
                            }) {
                                Text("重新开始")
                            }
                        },
                        dismissButton = {

                            Game.quit()

                        }
                    )
                }

                var y by remember { mutableStateOf(0) }
                Canvas(modifier = Modifier.fillMaxHeight(0.8f).fillMaxWidth().pointerInput(Unit) {
                    // 检测水平拖动
                    detectHorizontalDragGestures(
                        onDragStart = { offset ->
                            // 可选：只有从屏幕左边缘开始的拖动才处理
                            // 这里简化为所有水平拖动
                            println( "这是 DEBUG 日志")
                        },
                        onDragEnd = {
                            // 滑动结束时，如果超过了阈值，执行返回
//                            if (backStack.size > 1) { // 确保不是首页
//                                onSwipeBack()
//                            }
                        }
                    ) { change, dragAmount ->
                        // 这里可以处理滑动过程中的动画（例如页面跟随移动）
                        change.consume()
                    }
                }) {
                    score="分数:"+Game.getScore().toString()
                    showDialog= Game.getIsOver()
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
                    y=y%2
                }
                Row(modifier = Modifier.fillMaxHeight(0.4f).fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {

                    Button(onClick = {
                        Game.move(-1)
                    },
                        modifier = Modifier.weight(0.25f),
                        contentPadding = PaddingValues(
                            horizontal = 2.dp,  // 左右内边距
                        )) {
                        Text("left")
                    }
                    Button(onClick = {
                        Game.move(1)
                    },
                        modifier = Modifier.weight(0.25f),
                        contentPadding = PaddingValues(
                            horizontal = 2.dp,  // 左右内边距
                        )) {
                        Text("right")
                    }
                    Button(onClick = {
                        Game.rotate()
                    },
                        modifier = Modifier.weight(0.25f),
                        contentPadding = PaddingValues(
                            horizontal = 2.dp,  // 左右内边距
                        )) {
                        Text("rotate")
                    }
                    Button(onClick = {
                        Game.down()
                    },
                        modifier = Modifier.weight(0.25f),
                        contentPadding = PaddingValues(
                            horizontal = 2.dp,  // 左右内边距
                        )) {
                        Text("down")
                    }
                    Button(onClick = {
                        val flag= Game.getPause()
                        Game.setPause(!flag)
                    },
                        modifier = Modifier.weight(0.25f),
                        contentPadding = PaddingValues(
                            horizontal = 2.dp,  // 左右内边距
                        )) {
                        Text("pause")
                    }
                }
            }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeableCardModern() {
    var isVisible by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isVisible) {
            // 1. 创建状态 - 注意不要传 confirmValueChange！
            val dismissState = rememberSwipeToDismissBoxState(
                positionalThreshold = { totalDistance -> totalDistance / 2f } // 滑动超过一半触发
            )

            // 2. 监听状态变化（推荐方式）
            LaunchedEffect(dismissState) {
                // 使用 snapshotFlow 监听 currentValue 的变化
                snapshotFlow { dismissState.currentValue }
                    // 过滤掉 Settled 状态，只处理最终滑动方向
                    .map { it }
                    .distinctUntilChanged()
                    .collect { value ->
                        when (value) {
                            SwipeToDismissBoxValue.StartToEnd -> {
                                println("👉 右滑判定 (Like)")
                                // 延迟一下等动画完成
                                delay(300)
                                isVisible = false
                            }
                            SwipeToDismissBoxValue.EndToStart -> {
                                println("👈 左滑判定 (Dislike)")
                                delay(300)
                                isVisible = false
                            }
                            SwipeToDismissBoxValue.Settled -> {
                                // 无操作
                            }

                            StartToEnd -> TODO()
                            EndToStart -> TODO()
                            Settled -> TODO()
                        }
                    }
            }

            // 3. SwipeToDismissBox 组件
            SwipeToDismissBox(
                state = dismissState,
                modifier = Modifier
                    .width(300.dp)
                    .height(400.dp),
                // 注意：这里使用 onDismissed 回调（新 API）
                onDismiss = { dismissValue ->
                    // 这个回调会在滑动完成时立即触发
                    when (dismissValue) {
                        SwipeToDismissBoxValue.StartToEnd -> {
                            println("onDismissed: 右滑")
                            // 可以在这里执行轻量级操作
                        }
                        SwipeToDismissBoxValue.EndToStart -> {
                            println("onDismissed: 左滑")
                        }
                        SwipeToDismissBoxValue.Settled -> {
                            // 无操作
                        }
                    }
                },
                // 背景内容：滑动时露出的部分
                backgroundContent = {
                    BackgroundContent(dismissState)
                },
                // 前景内容：实际滑动的卡片
                content = {
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "滑动我",
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }
                    }
                }
            )
        } else {
            // 重置按钮
            Button(onClick = { isVisible = true }) {
                Text("重置卡片")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BackgroundContent(dismissState: SwipeToDismissBoxState) {
    val direction = dismissState.dismissDirection
    val targetValue = dismissState.targetValue

    val backgroundColor by animateColorAsState(
        targetValue = when {
            direction == SwipeToDismissBoxValue.StartToEnd -> Color.Green
            direction == SwipeToDismissBoxValue.EndToStart -> Color.Red
            else -> Color.Gray
        },
        label = "background color"
    )

    val contentAlignment = when (direction) {
        SwipeToDismissBoxValue.StartToEnd -> Alignment.CenterStart
        SwipeToDismissBoxValue.EndToStart -> Alignment.CenterEnd
        else -> Alignment.Center
    }

    val hintText = when (direction) {
        SwipeToDismissBoxValue.StartToEnd -> "❤️ LIKE"
        SwipeToDismissBoxValue.EndToStart -> "❌ NOPE"
        else -> ""
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 24.dp),
        contentAlignment = contentAlignment
    ) {
        Text(
            text = hintText,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }
}