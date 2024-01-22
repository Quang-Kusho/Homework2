package com.example.homework1.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun FirstScreen(
    onBackPress: () -> Unit
){
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)){
            LazyColumn(){
                item{
                    Text(
                        text = "OULU POLICE STATION",
                        color = Color.DarkGray,
                        fontSize = 30.sp,
                    )
                }
                item{
                    Text(
                        text = "Apart from being the place where you can find help for safety problem, Oulu Police Station is also the place where you can create the Finnish Personal Identification card.\n" +
                                "\n" +
                                "In certain situations in Finland, such as when you're opening a bank account, having just your resident permit might not enough. In addition to your permit, your passport is also typically required. \n" +
                                "\n" +
                                "However, possessing a Finnish Personal Identity Card simplifies things significantly – a single card suffices, and you won't need to present both your resident permit and passport.\u200B\n" +
                                "\n" +
                                "With this card, you can open the electronic identification. Apart from being a safe way to transfer money in Finland, this is a convenient way to identify yourself electronically. This means that many processes that currently demanded in-person interaction can now be completed online.\u200B\n" +
                                "\n" +
                                "The process of opening strong-identification is very simple. When you get the Finnish Personal Identity Card from police station, just go directly to your bank and ask them to open e-identification for you. \u200B\n" +
                                "\n" +
                                "Once the e-identification is opened, it will keep open even when your Finnish Personal Identity card is overdue. So, I would recommend you create the Finnish Personal Identity Card at least once.\u200B\n" +
                                "\n" +
                                "And also dont forget to bring the card to Academic Affairs to gain access to FSHS services.\u200B",
                        color = Color.DarkGray,
                        fontSize = 20.sp,
                    )
                }
                item{
                    Button(onClick = onBackPress,
                        enabled = true,
                    ) {
                        Text("Back to home page")
                    }
                }
            }
    }
}