package br.com.jhonny.starlord.feature.home.exception

import junit.framework.TestCase.assertEquals
import org.junit.Test

class FailGitHubRequestExceptionTest {

    @Test
    fun `should successful build message without backend message`() {
        val exception = FailGitHubRequestException(httpCode = 404)

        assertEquals(EXPECTED_MSG_WITHOUT_BACKEND_MESSAGE, exception.message)
    }

    @Test
    fun `should successful build message with backend message`() {
        val exception = FailGitHubRequestException(
            httpCode = 404,
            backendMessage = "backendMessage",
        )

        assertEquals(EXPECTED_MSG_WITH_BACKEND_MESSAGE, exception.message)
    }

    private companion object {
        const val EXPECTED_MSG_WITHOUT_BACKEND_MESSAGE = "Fail to get repositories with this http code 404."
        const val EXPECTED_MSG_WITH_BACKEND_MESSAGE =
            "Fail to get repositories with this http code 404.\nThe backend retrieve this http code 404 and message: backendMessage"
    }
}
