package br.com.jhonny.startlord.feature.home.exception

internal class FailGitHubRequestException(
    backendMessage: String?,
) : Throwable(buildMessage(backendMessage)) {

    private companion object {
         fun buildMessage(backendMessage: String?): String {
            val baseMessage = "Fail to get repositories."
            return if (backendMessage.isNullOrBlank()) {
                baseMessage
            } else {
                "$baseMessage\nThe backend retrieve this message: $backendMessage"
            }
        }
    }
}
