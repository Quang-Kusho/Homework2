package com.example.homework1.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework1.R
import com.example.homework1.ui.theme.Homework1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Homework1Theme {
                Surface {
                    App()
                }
            }
        }
    }
}
@Composable
fun Greeting(name: String, text: String) {
    val context = LocalContext.current
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)){
        LazyColumn(){
            item{
                Text(
                    text = "Hello $name!",
                    color = Color.LightGray,
                    fontSize = 30.sp,
                )
                Text(
                    text = text,
                    color = Color.DarkGray,
                    fontSize = 20.sp,
                )
            }
            item{
                Text(
                    text = "Finance is a term for matters regarding the management, creation, and study of money and investments. It involves the use of credit and debt, securities, and investment to finance current projects using future income flows. Because of this temporal aspect, finance is closely linked to the time value of money, interest rates, and other related topics.",
                    color = Color.Black,
                    fontSize = 20.sp,
                )
                Text(
                    text = "Public finance includes tax systems, government expenditures, budget procedures, stabilization policy and instruments, debt issues, and other government concerns. Corporate finance involves managing assets, liabilities, revenues, and debts for a business. Personal finance defines all financial decisions and activities of an individual or household, including budgeting, insurance, mortgage planning, savings, and retirement planning.",
                    color = Color.Black,
                    fontSize = 20.sp,
                )
            }
            item{
                Button(onClick = { showToast(context, "clicked") }) {
                    Text("Click here")
                }
                Row (modifier = Modifier.padding(all = 30.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.ava),
                        contentDescription = "my avatar")
                }
            }

        }
    }
}

fun showToast(context: Context, msg: String){
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

