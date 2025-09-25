package br.com.jhonny.starlord.feature.home.exception

/**
 * Exception thrown when a GitHub API request fails.
 *
 * This exception is used to indicate that a request to the GitHub API did not complete successfully.
 * It provides information about the HTTP status code received and an optional backend message.
 *
 * @param httpCode The HTTP status code returned by the GitHub API. Can be null if the request failed before receiving a response.
 * @param backendMessage An optional message from the backend system related to the failure. Defaults to null.
 */
internal class FailGitHubRequestException(
    httpCode: Int?,
    backendMessage: String? = null,
) : Throwable(buildMessage(backendMessage, httpCode)) {

    private companion object {
        fun buildMessage(backendMessage: String?, httpCode: Int?): String {
            val baseMessage = "Fail to get repositories with this http code $httpCode."
            return if (backendMessage.isNullOrBlank()) {
                baseMessage
            } else {
                "$baseMessage\nThe backend retrieve this http code $httpCode and message: $backendMessage"
            }
        }
    }
}
