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
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

import demo2.composeapp.generated.resources.Res
import demo2.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var val1 by remember{mutableStateOf(0)}
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me1232!")
            }
            Button(onClick = { showContent = !showContent
                val1++
            }) {
                Text("bottom!")
            }


            var y by remember { mutableStateOf(0) }
            var count by remember { mutableStateOf(0) }
            val textMeasurer = rememberTextMeasurer()

            Canvas(modifier = Modifier.size(200.dp)) {
                // 在这里进行绘制
                drawRect(
                    color = Color.Yellow,
                    topLeft = Offset(50f, y.toFloat()),
                    size = Size(100f, 100f)
                )
                y++
                if(y>800-(count+1)*100){
                    count++
                    y=0

                }

                for (i in 1 .. count) {
                    drawRect(
                        color = Color.Yellow,
                        topLeft = Offset(50f, 800-i*100.toFloat()),
                        size = Size(100f, 100f)
                    )
                }
                drawLine(
                    color = Color.Green,
                    start = Offset.Zero,
                    end = Offset(size.width, size.height),
                    strokeWidth = 5f
                )

                // 2. 测量文字：定义样式并计算布局
                val textLayoutResult = textMeasurer.measure(
                    text = y.toString(),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                )

                // 3. 绘制文字：将其放置在画布的中心
                val canvasCenter = Offset(size.width / 2, size.height / 2)
                drawText(
                    textLayoutResult = textLayoutResult,
                    topLeft = canvasCenter
                )
            }
        }
    }
}