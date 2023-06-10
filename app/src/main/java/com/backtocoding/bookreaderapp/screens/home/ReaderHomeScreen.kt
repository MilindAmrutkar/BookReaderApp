package com.backtocoding.bookreaderapp.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.backtocoding.bookreaderapp.components.FABContent
import com.backtocoding.bookreaderapp.components.ListCard
import com.backtocoding.bookreaderapp.components.ReaderAppBar
import com.backtocoding.bookreaderapp.components.TitleSection
import com.backtocoding.bookreaderapp.model.ReaderBook
import com.backtocoding.bookreaderapp.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(topBar = {
        ReaderAppBar(title = "A.Reader", navController = navController)
    }, floatingActionButton = {
        FABContent {}
    }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeContent(it, navController)
        }
    }
}

@Composable
fun HomeContent(paddingValues: PaddingValues, navController: NavController) {

    val listOfBooks = listOf(
        ReaderBook(id = "dafjdljlsk", title = "Hello Again", authors = "All of us", notes = null),
        ReaderBook(id = "dafjdljlsk", title = "Hello there", authors = "All of us", notes = null),
        ReaderBook(
            id = "dafjdljlsk",
            title = "Hello jlaksdfjkl",
            authors = "All of us",
            notes = null
        ),
        ReaderBook(id = "dafjdljlsk", title = "Hello pLkasdf", authors = "All of us", notes = null),
        ReaderBook(
            id = "dafjdljlsk",
            title = "Hello pickasdfh",
            authors = "All of us",
            notes = null
        ),
        ReaderBook(id = "dafjdljlsk", title = "Hello Google", authors = "All of us", notes = null),
        ReaderBook(
            id = "dafjdljlsk",
            title = "Hello Microsoft",
            authors = "All of us",
            notes = null
        ),
    )

    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty()) {
        email.split("@")[0]
    } else {
        "N/A"
    }
    Column(
        Modifier.padding(
            top = paddingValues.calculateTopPadding(),
            bottom = 2.dp,
            start = 2.dp,
            end = 2.dp
        ),
        verticalArrangement = Arrangement.Top
    ) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Your reading \n " + " activity right now. ")

            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                        }
                        .size(45.dp),
                    tint = MaterialTheme.colorScheme.secondaryContainer
                )
                Text(
                    text = currentUserName,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                Divider()
            }
        }
        ReadingRightNowArea(books = listOf(), navController = navController)
        TitleSection(label = "Reading List")
        BookListArea(listOfBooks = listOfBooks, navController = navController)
    }
}

@Composable
fun BookListArea(listOfBooks: List<ReaderBook>, navController: NavController) {
    HorizontalScrollableComponent(listOfBooks) {
        Log.d("TAG", "BookListArea: $it")
    }
}

@Composable
fun HorizontalScrollableComponent(listOfBooks: List<ReaderBook>, onCardPressed: (String) -> Unit) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(280.dp)
            .horizontalScroll(scrollState)
    ) {
        for (book in listOfBooks) {
            ListCard(book) {
                onCardPressed(it)
            }
        }
    }
}

@Composable
fun ReadingRightNowArea(
    books: List<ReaderBook>,
    navController: NavController
) {
    ListCard()
}

