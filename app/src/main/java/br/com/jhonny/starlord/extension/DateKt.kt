package br.com.jhonny.starlord.extension

import android.util.Log
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale

private const val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"

public fun String.toDate(): Date = runCatching {
    Date.from(Instant.parse(this))
}.getOrElse {
    Log.d("RetrieveGitHubRepositoryUseCase", "Fail to parse date: $this")
    val dateFormatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
    dateFormatter.parse(this) ?: Date().also {
        Log.d("RetrieveGitHubRepositoryUseCase", "Fallback is failed to parse date: $this")
    }
}
