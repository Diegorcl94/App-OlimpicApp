package cl.olimpictraining.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun WeekdayDateSelector(
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit,
    daysAhead: Long = 30
) {
    val today = LocalDate.now()

    val availableDates = (0..daysAhead)
        .map { today.plusDays(it) }
        .filter {
            it.dayOfWeek != DayOfWeek.SATURDAY &&
            it.dayOfWeek != DayOfWeek.SUNDAY
        }

    val dayFormatter = DateTimeFormatter.ofPattern(
        "EEE",
        Locale("es", "CL")
    )

    val monthFormatter = DateTimeFormatter.ofPattern(
        "MMM",
        Locale("es", "CL")
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(availableDates) { date ->

            val selected = selectedDate == date

            Card(
                modifier = Modifier
                    .width(74.dp)
                    .clickable {
                        onDateSelected(date)
                    },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor =
                        if (selected) OlimpicBlue
                        else Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation =
                        if (selected) 5.dp else 2.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = date.format(dayFormatter)
                            .replace(".", "")
                            .uppercase(),
                        color =
                            if (selected) Color.White
                            else OlimpicTextSecondary,
                        style = MaterialTheme.typography.labelLarge
                    )

                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )

                    Text(
                        text = date.dayOfMonth.toString(),
                        color =
                            if (selected) Color.White
                            else OlimpicTextPrimary,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = date.format(monthFormatter)
                            .replace(".", ""),
                        color =
                            if (selected) {
                                Color.White.copy(alpha = 0.8f)
                            } else {
                                OlimpicTextSecondary
                            },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
