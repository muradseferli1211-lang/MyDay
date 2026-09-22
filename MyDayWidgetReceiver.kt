package com.myday.app

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.glance.unit.dp
import androidx.glance.appwidget.provideContent

class MyDayWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(ColorProvider(android.graphics.Color.rgb(15,16,23)))
                    .padding(16.dp),
                verticalAlignment = Alignment.Vertical.CenterVertically
            ) {
                Text("MY DAY", style = TextStyle(color = ColorProvider(android.graphics.Color.WHITE)))
                Spacer(GlanceModifier.height(8.dp))
                Row(GlanceModifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Metric("STUDY", "42%")
                    Metric("WATER", "1.25 L")
                }
                Spacer(GlanceModifier.height(12.dp))
                Text("₼ 8.50     ·     3 meals",
                    style = TextStyle(color = ColorProvider(android.graphics.Color.LTGRAY)))
            }
        }
    }

    @androidx.compose.runtime.Composable
    private fun Metric(label: String, value: String) {
        Column {
            Text(value, style = TextStyle(color = ColorProvider(android.graphics.Color.WHITE)))
            Text(label, style = TextStyle(color = ColorProvider(android.graphics.Color.GRAY)))
        }
    }
}

class MyDayWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MyDayWidget()
}
