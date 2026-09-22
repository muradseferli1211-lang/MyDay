package com.myday.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyDayApp()
        }
    }
}

@Composable
fun MyDayApp() {
    var selectedTab by remember { mutableStateOf(0) }

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Color(0xFF0D0D0F),
            surface = Color(0xFF17171A),
            primary = Color(0xFF9B8CFF)
        )
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        icon = { Text("📚") },
                        label = { Text("Plan") }
                    )

                    NavigationBarItem(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        icon = { Text("💧") },
                        label = { Text("Water") }
                    )

                    NavigationBarItem(
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 },
                        icon = { Text("💰") },
                        label = { Text("Money") }
                    )

                    NavigationBarItem(
                        selected = selectedTab == 3,
                        onClick = { selectedTab = 3 },
                        icon = { Text("🍎") },
                        label = { Text("Food") }
                    )
                }
            }
        ) { padding ->

            when (selectedTab) {
                0 -> PlanScreen(Modifier.padding(padding))
                1 -> WaterScreen(Modifier.padding(padding))
                2 -> MoneyScreen(Modifier.padding(padding))
                3 -> FoodScreen(Modifier.padding(padding))
            }
        }
    }
}

@Composable
fun PlanScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0D0D0F))
            .padding(20.dp)
    ) {
        Text(
            text = "My Day",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Today's plan",
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        val lessons = listOf(
            "Math",
            "English",
            "Physics",
            "Computer Science"
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(lessons.size) { index ->
                var checked by remember { mutableStateOf(false) }

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { checked = it }
                        )

                        Text(
                            text = lessons[index],
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WaterScreen(modifier: Modifier = Modifier) {
    var water by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0D0D0F))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Water",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            "$water ml",
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { water += 250 }
        ) {
            Text("+250 ml")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = {
                if (water >= 250) water -= 250
            }
        ) {
            Text("-250 ml")
        }
    }
}

@Composable
fun MoneyScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0D0D0F))
            .padding(20.dp)
    ) {
        Text(
            "Money",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text("Today's expenses")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "0.00 AZN",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

@Composable
fun FoodScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0D0D0F))
            .padding(20.dp)
    ) {
        Text(
            "Food",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Add food and keep notes about what you ate.",
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Food") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
