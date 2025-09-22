package br.com.jhonny.startlord.feature.home.exception

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
