package com.example.food_2_fork_kmm_my.android.presentation.recipe_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    query: String,
    // how we change
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
) {
    // с помощью этого контроллера можно прятать клаву
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.secondary,
        elevation = 8.dp,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp),
                    value = query,
                    onValueChange = { onQueryChanged(it) },
                    label = { Text(text = "Search") }, // if nothing input show Search

                    // что-то вроде настроек клавиатуры
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        // что-то вроде нажатия завершающего интер
                        imeAction = ImeAction.Done,
                    ),
                    // что-то вроде отбивки от клавы при действиях пользака
                    keyboardActions = KeyboardActions(
                        // что происходит при нажатии кнопки Done (Enter)
                        onDone = {
                            onExecuteSearch()
                            keyboardController?.hide() // прячем клаву
                        },
                    ),
                    // третий атрибут это иконка в начале текстового поля поиска
                    leadingIcon = {
                        // в Compose мы обращаемся к банку иконок и берем оттуда: поиск иконка
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search Icon" // для слабовидящих озвучка
                        )
                    },
                    // цвет
                    textStyle = TextStyle(
                        // цвет текста черный (что на поверхности)
                        color = MaterialTheme.colors.onSurface
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        // цвет поля - белый (поверхность)
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                )
            }
            LazyRow(
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 8.dp),
            ) {
            }
        }
    }
}

