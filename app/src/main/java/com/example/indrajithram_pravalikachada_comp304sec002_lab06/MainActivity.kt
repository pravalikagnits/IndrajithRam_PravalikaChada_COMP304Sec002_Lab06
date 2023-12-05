package com.example.indrajithram_pravalikachada_comp304sec002_lab06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.indrajithram_pravalikachada_comp304sec002_lab06.ui.theme.indrajithram_pravalikachada_comp304sec002_lab06Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val programNames = resources.getStringArray(R.array.program_names)
        val programDescriptions = resources.getStringArray(R.array.program_descriptions)

        val programs = mutableListOf<Program>()

        for (i in programNames.indices){
            val program = Program(programNames[i],programDescriptions[i])
            programs.add(program)
        }

        // Outside MainActivity class
        val customFontFamily = FontFamily(
            Font(R.font.bricolage_grotesque) // Use the name of your custom font resource
        )

        setContent {
            indrajithram_pravalikachada_comp304sec002_lab06Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF7F7F7)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    )  {
                        Text(text = "List Of Available Programs",
                            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFF018786),
                            fontFamily = customFontFamily
                        )
                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                        ProgramList(programs = programs)
                    }
                }
            }
        }
    }
}

@Composable
fun ProgramCard(program: Program) {

    // Outside MainActivity class
    val customFontFamily = FontFamily(
        Font(R.font.bricolage_grotesque) // Use the name of your custom font resource
    )

    Row(modifier = Modifier.padding(all = 8.dp)) {

        Image(
            painter = painterResource(R.drawable.profile_logo),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {

            Text(
                text = program.name,
                style = MaterialTheme.typography.h6.copy(color = Color(0xFF4A148C)),
                fontFamily = customFontFamily
            )

            Spacer(modifier = Modifier.width(5.dp))

            var isExpanded by remember { mutableStateOf(false) }

            val surfaceColor by animateColorAsState(
                if (isExpanded) Color(0xFFFFFFFF) else MaterialTheme.colors.surface,
            )

            Surface(shape = MaterialTheme.shapes.medium,
                elevation = 5.dp,
                color= surfaceColor,
                modifier = Modifier
                    .padding(top = 15.dp, end = 15.dp)
                    .clickable { isExpanded = !isExpanded }) {
                Text(
                    text = program.description,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                        .animateContentSize(),
                    style = MaterialTheme.typography.subtitle2,
                    color = Color(0xFFAB47BC),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = customFontFamily
                )
            }
        }
    }
}

@Composable
fun ProgramList(programs: List<Program>){
    LazyColumn{
        items(programs){
                program -> Column {
                    ProgramCard(program)
                    Spacer(modifier = Modifier.padding(bottom = 16.dp))
                }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProgramCard() {
    val programs = listOf(
        Program("Sample Program 1", "This is the first sample program."),
        Program("Sample Program 2", "This is the second sample program.")
    )
    ProgramList(programs = programs)
}