package br.com.jhonny.starlord.extension

import android.util.Log
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale

private const val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"

/**
 * Converts a string representation of a date to a [Date] object.
 *
 * This function first attempts to parse the string as an ISO 8601 date-time string
 * (e.g., "2023-10-27T10:15:30.00Z") using `Instant.parse()`.
 *
 * If parsing with `Instant.parse()` fails, it falls back to parsing the string
 * using a specific date pattern: "yyyy-MM-dd'T'HH:mm:ss'Z'" (defined by `DATE_PATTERN`).
 *
 * If both parsing attempts fail, it returns the current [Date].
 *
 * Logs are generated using `Log.d` with the tag "RetrieveGitHubRepositoryUseCase"
 * to indicate parsing failures and fallback attempts.
 *
 * @return The parsed [Date] object, or the current [Date] if parsing fails.
 */
public fun String.toDate(): Date = runCatching {
    Date.from(Instant.parse(this))
}.getOrElse {
    Log.d("RetrieveGitHubRepositoryUseCase", "Fail to parse date: $this")
    val dateFormatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
    dateFormatter.parse(this) ?: Date().also {
        Log.d("RetrieveGitHubRepositoryUseCase", "Fallback is failed to parse date: $this")
    }
}
