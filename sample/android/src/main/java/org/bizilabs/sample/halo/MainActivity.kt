package org.bizilabs.sample.halo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentSize
import org.bizilabs.halo.base.HaloShapes
import org.bizilabs.halo.components.HaloScaffold
import org.bizilabs.halo.components.toogle.HaloFilledSwitch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HaloTheme {
                HaloScaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        var toggled by remember { mutableStateOf(false) }
                        var toggleEnabled by remember { mutableStateOf(true) }
                        HaloFilledSwitch(
                            toggled = toggled,
                            onToggled = { toggled = !toggled },
                            indicator = {
                                Text(
                                    text = if (toggled) "On" else "Off",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (toggled) Color.White else Color.Black,
                                )
                            },
                            shape = HaloShapes.Rounded.small,
                            contentPadding = PaddingValues(4.dp),
                            size = ComponentSize.Medium,
                            enabled = toggleEnabled,
                        )

                        Spacer(Modifier.height(16.dp))
                        Text("Toggle Enabled")
                        Switch(
                            checked = toggleEnabled,
                            onCheckedChange = {
                                toggleEnabled = !toggleEnabled
                            },
                        )
                    }
                }
            }
        }
    }
}
