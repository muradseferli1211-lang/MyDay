package com.myday.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val Bg = Color(0xFF090A0F)
private val Card = Color(0xFF151720)
private val TextMain = Color(0xFFF2F3F7)
private val Muted = Color(0xFF9297A7)
private val Accent = Color(0xFF9B8CFF)

data class Lesson(val title: String, val time: String, val day: String, val done: Boolean = false)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MyDayApp() }
    }
}

@Composable
fun MyDayApp() {
    var tab by remember { mutableStateOf(0) }
    MaterialTheme(colorScheme = darkColorScheme(background = Bg, surface = Card, primary = Accent)) {
        Scaffold(
            containerColor = Bg,
            bottomBar = {
                NavigationBar(containerColor = Color(0xFF101117)) {
                    listOf("Plan", "Water", "Money", "Food").forEachIndexed { i, label ->
                        NavigationBarItem(selected = tab == i, onClick = { tab = i },
                            icon = { Text(listOf("◫", "◌", "₼", "◒")[i]) },
                            label = { Text(label) })
                    }
                }
            }
        ) { pad ->
            when (tab) {
                0 -> Planner(Modifier.padding(pad))
                1 -> Water(Modifier.padding(pad))
                2 -> Money(Modifier.padding(pad))
                else -> Food(Modifier.padding(pad))
            }
        }
    }
}

@Composable
fun Header(title: String, subtitle: String) {
    Column(Modifier.padding(horizontal = 20.dp, vertical = 18.dp)) {
        Text(title, style = MaterialTheme.typography.headlineMedium, color = TextMain)
        Spacer(Modifier.height(4.dp))
        Text(subtitle, color = Muted)
    }
}

@Composable
fun GlassCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier
            .background(
                Brush.linearGradient(listOf(Color(0xFF191B26), Color(0xFF111219))),
                RoundedCornerShape(24.dp)
            )
            .padding(18.dp),
        content = content
    )
}

@Composable
fun Planner(modifier: Modifier) {
    val lessons = remember { mutableStateListOf(
        Lesson("Riyaziyyat", "15:00", "B.e."),
        Lesson("İngilis dili", "17:00", "B.e."),
        Lesson("Fizika", "19:00", "B.e.")
    ) }
    Column(modifier.fillMaxSize()) {
        Header("My Day", "Həftəlik plan")
        LazyColumn(contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item {
                GlassCard(Modifier.fillMaxWidth()) {
                    Text("BU GÜN", color = Muted)
                    Spacer(Modifier.height(12.dp))
                    Text("Tuesday · 22 September", color = TextMain, style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(16.dp))
                    LinearProgressIndicator(progress = { 0.42f }, modifier = Modifier.fillMaxWidth())
                }
            }
            items(lessons) { lesson ->
                GlassCard(Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text(lesson.title, color = TextMain, style = MaterialTheme.typography.titleMedium)
                            Text("${lesson.day} · ${lesson.time}", color = Muted)
                        }
                        Checkbox(checked = lesson.done, onCheckedChange = { checked ->
                            val i = lessons.indexOf(lesson)
                            lessons[i] = lesson.copy(done = checked)
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun Water(modifier: Modifier) {
    var ml by remember { mutableStateOf(1250) }
    Column(modifier.fillMaxSize()) {
        Header("Water", "Bugünkü qeyd")
        GlassCard(Modifier.padding(horizontal = 20.dp).fillMaxWidth()) {
            Text("$ml ml", color = TextMain, style = MaterialTheme.typography.displaySmall)
            Spacer(Modifier.height(12.dp))
            LinearProgressIndicator(progress = { (ml / 2000f).coerceIn(0f, 1f) }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(18.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(onClick = { ml += 250 }) { Text("+250 ml") }
                OutlinedButton(onClick = { ml = (ml - 250).coerceAtLeast(0) }) { Text("−250") }
            }
        }
    }
}

@Composable
fun Money(modifier: Modifier) {
    Column(modifier.fillMaxSize()) {
        Header("Money", "AZN · Xərclər")
        GlassCard(Modifier.padding(horizontal = 20.dp).fillMaxWidth()) {
            Text("₼ 8.50", color = TextMain, style = MaterialTheme.typography.displaySmall)
            Spacer(Modifier.height(8.dp))
            Text("Bu gün", color = Muted)
            Spacer(Modifier.height(18.dp))
            Text("Yemək        ₼ 5.00", color = TextMain)
            Text("Nəqliyyat     ₼ 3.50", color = TextMain)
        }
    }
}

@Composable
fun Food(modifier: Modifier) {
    Column(modifier.fillMaxSize()) {
        Header("Food", "Qida qeydləri")
        GlassCard(Modifier.padding(horizontal = 20.dp).fillMaxWidth()) {
            Text("Bugünkü qida qeydləri", color = TextMain, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(10.dp))
            Text("Yeməkləri və qida məlumatlarını qeyd edə bilərsən.", color = Muted)
            Spacer(Modifier.height(16.dp))
            Text("Qeyd: kalori rəqəmləri təxmini ola bilər; tətbiq onları sağlamlıq hədəfi kimi təqdim etmir.", color = Muted)
        }
    }
}
