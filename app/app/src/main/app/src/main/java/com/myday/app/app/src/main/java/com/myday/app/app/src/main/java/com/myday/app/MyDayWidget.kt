package com.myday.app

import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.glance.unit.dp
import androidx.glance.unit.sp
import androidx.glance.appwidget.cornerRadius

class MyDayWidget : GlanceAppWidget() {

    override suspend fun provideGlance(
        context: android.content.Context,
        id: GlanceId
    ) {
        provideContent {
            WidgetContent()
        }
    }

    @Composable
    private fun WidgetContent() {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .cornerRadius(24.dp)
                .padding(16.dp),
            verticalAlignment = Alignment.Vertical.CenterVertically
        ) {
            Text(
                text = "MY DAY",
                style = TextStyle(
                    color = ColorProvider(android.graphics.Color.WHITE),
                    fontSize = 18.sp
                )
            )

            Spacer(
                modifier = GlanceModifier.height(8.dp)
            )

            Text(
                text = "Today's plan",
                style = TextStyle(
                    color = ColorProvider(android.graphics.Color.LTGRAY),
                    fontSize = 13.sp
                )
            )

            Spacer(
                modifier = GlanceModifier.height(14.dp)
            )

            Row {
                Text(
                    text = "📚  Study",
                    style = TextStyle(
                        color = ColorProvider(android.graphics.Color.WHITE),
                        fontSize = 14.sp
                    )
                )
            }

            Spacer(
                modifier = GlanceModifier.height(8.dp)
            )

            Row {
                Text(
                    text = "💧  Water",
                    style = TextStyle(
                        color = ColorProvider(android.graphics.Color.WHITE),
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}
