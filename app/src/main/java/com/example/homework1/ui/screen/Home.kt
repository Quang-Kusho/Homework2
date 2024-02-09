package com.example.homework1.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.homework1.R
import com.example.homework1.data.Account
import com.example.homework1.data.getBitmapFromImage
import com.example.homework1.ui.AccountTopAppBar
import com.example.homework1.ui.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    navigateToAccountEntry: () -> Unit,
    navigateToAccountUpdate: (Account) -> Unit,
    viewModel: AppViewModel
){
    val homeUiState by viewModel.accountUiStates.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AccountTopAppBar(
                title = "Home",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAccountEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Character"
                )
            }
        },
        containerColor = Color.Cyan,
        contentColor = Color.Black,
    ) {innerPadding ->
        HomeBody(
            accountList = homeUiState.accountList,
            onAccountClick = navigateToAccountUpdate,
            modifier = modifier
                .padding(innerPadding)
        )
    }
}

@Composable
private fun HomeBody(
    modifier: Modifier = Modifier,
    accountList: List<Account>,
    onAccountClick: (Account) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (accountList.isEmpty()) {
            Text(
                text = "Add new account with +.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            Text(
                text = "List of accounts: ",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            AccountListScreen(
                accounts = accountList,
                onClick = { onAccountClick(it) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}
@Composable
fun AccountListScreen(
    accounts: List<Account>,
    onClick: (Account) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(accounts){
            account -> AccountCard(account = account, onClick = {onClick(account)})
        }
    }
}

@Composable
fun AccountCard(
    modifier: Modifier = Modifier,
    account: Account,
    onClick: (Account) -> Unit,
) {
    Row(
        modifier = modifier.padding(all = 8.dp),
    ) {

        var isImageExpanded by remember { mutableStateOf(false) }
        val imageBitmap = when(account.imageData) {
            null -> getBitmapFromImage(LocalContext.current, R.drawable.avatarholder).asImageBitmap()
            else -> account.imageData.asImageBitmap()
        }
        Image(
            bitmap = imageBitmap,
            contentDescription = account.imageDescription,
            modifier = Modifier
                .clickable { isImageExpanded = !isImageExpanded }
                .size(if (isImageExpanded) 160.dp else 80.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = ""
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = account.name,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.clickable { onClick( account ) }
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = account.info,
                    modifier = Modifier
                        .padding(all = 4.dp)
                        .clickable { isExpanded = !isExpanded },
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}