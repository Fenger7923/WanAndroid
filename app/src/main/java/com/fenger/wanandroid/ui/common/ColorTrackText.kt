package com.fenger.wanandroid.ui.common

import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * @author fengerzhang
 * @date 2021/10/14 20:00
 */

enum class Direction {
    RTL, LTR
}

@Composable
fun DrawGradientText(
    text: String,
    modifier: Modifier = Modifier,
    fromColor: Color,
    endColor: Color,
    percent: Float,
    direction: Direction,
    fontSize: TextUnit = TextUnit.Unspecified
) {

    val fontSizeF = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        fontSize.value,
        LocalContext.current.resources.displayMetrics
    )

    val paint = Paint().asFrameworkPaint().apply {
        textAlign = android.graphics.Paint.Align.CENTER
        textSize = fontSizeF
    }

    val textWidth = (paint.measureText(text) / Resources.getSystem().displayMetrics.density).dp
    val widthF = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        textWidth.value,
        LocalContext.current.resources.displayMetrics
    )

    val gradientShader: Shader = LinearGradientShader(
        from = Offset(0f, 0f),
        to = Offset(widthF, 0f),
        colors = if (direction == Direction.LTR) listOf(fromColor, endColor) else listOf(
            endColor,
            fromColor
        ),
        colorStops = listOf(percent, percent)
    )
    paint.shader = gradientShader

    Canvas(
        modifier
            .height(fontSizeF.toDp.dp)
            .width(textWidth)
    ) {
        paint.apply {
            textAlign = android.graphics.Paint.Align.CENTER
            textSize = fontSizeF
            shader = gradientShader
        }

        // 获取字体宽度
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        // 基线
        val fontMetricsInt = paint.fontMetricsInt
        val dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom
        val baseLine = size.height / 2 + dy
        drawIntoCanvas { canvas ->
            canvas.save()
            canvas.nativeCanvas.drawText(text, center.x, baseLine, paint)
            canvas.restore()
        }
        paint.reset()
    }
}

@Composable
@Preview(showBackground = false, showSystemUi = false)
fun TestColorTrackText() {

        DrawGradientText(
            "1234567890",
            fromColor = Color.Red,
            endColor = Color.Blue,
            percent = 1f,
            direction = Direction.LTR,
            fontSize = 25.sp
        )
}
val Float.toDp get() = this / Resources.getSystem().displayMetrics.density

