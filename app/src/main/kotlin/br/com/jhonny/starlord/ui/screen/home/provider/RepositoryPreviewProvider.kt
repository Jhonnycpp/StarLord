package br.com.jhonny.starlord.ui.screen.home.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import java.util.Date
import kotlin.random.Random

/**
 * Provides sample data for [RepositoryVO] lists to be used in Composable previews.
 *
 * This class implements [PreviewParameterProvider] to supply different scenarios
 * for previewing lists of repositories, such as lists with varying numbers of items.
 *
 * The [values] property generates a sequence of lists, each created by the
 * [createRandomSample] function. Each list in the sequence contains a different
 * number of [RepositoryVO] items (1, 2, 3, 4, 5, and 50).
 *
 * The [createRandomSample] function generates a list of [RepositoryVO] objects
 * based on a predefined [sample] list. It randomly selects items from the sample
 * and modifies them with unique IDs, names, avatars, descriptions, and random
 * counts for stars, forks, watchers, and issues. This ensures that each preview
 * displays varied and realistic-looking data.
 *
 * The [sample] companion object provides a static list of base [RepositoryVO]
 * objects that are used as templates for generating the random samples.
 */
internal class RepositoryPreviewProvider : PreviewParameterProvider<List<RepositoryVO>> {
    override val values: Sequence<List<RepositoryVO>>
        get() = sequenceOf(
            createRandomSample(1),
            createRandomSample(2),
            createRandomSample(3),
            createRandomSample(4),
            createRandomSample(5),
            createRandomSample(50),
        )

    fun createRandomSample(count: Int): List<RepositoryVO> {
        if (sample.isEmpty()) return emptyList()

        return (1..count).map { index ->
            val baseRepo = sample.random()
            val newId = (100_000L + index) + Random.nextLong(100_000)

            baseRepo.copy(
                id = newId.toInt(),
                name = "${baseRepo.name} #${index + 1}",
                userAvatar = "https://picsum.photos/200?random=$newId",
                description = "Descrição aleatória para ${baseRepo.name}. Item número $index.",
                starCount = Random.nextInt(50, 3000),
                forkCount = Random.nextInt(10, baseRepo.starCount.coerceAtLeast(11)),
                watcherCount = Random.nextInt(20, 500),
                issueCount = Random.nextInt(5, 100)
            )
        }
    }

    private companion object {
        val sample = listOf(
            RepositoryVO(
                id = 1,
                name = "Lumos",
                author = "Jhonatan",
                userAvatar = "https://picsum.photos/200?random=1",
                description = "A sample description for Lumos repository.",
                language = "Kotlin",
                licenseName = "MIT",
                starCount = 124,
                forkCount = 37,
                watcherCount = 60,
                issueCount = 12,
                createdAt = Date(),
                updatedAt = Date(),
                pushedAt = Date()
            ),
            RepositoryVO(
                id = 2,
                name = "Nebula",
                author = "Alice",
                userAvatar = "https://picsum.photos/200?random=2",
                description = "Nebula: exploring the cosmos of code.",
                language = "Java",
                licenseName = "Apache 2.0",
                starCount = 982,
                forkCount = 210,
                watcherCount = 450,
                issueCount = 55,
                createdAt = Date(),
                updatedAt = Date(),
                pushedAt = Date()
            ),
            RepositoryVO(
                id = 3,
                name = "Orion",
                author = "Carlos",
                userAvatar = "https://picsum.photos/200?random=3",
                description = "Orion is a constellation of features.",
                language = "Python",
                licenseName = "GNU GPLv3",
                starCount = 321,
                forkCount = 58,
                watcherCount = 120,
                issueCount = 22,
                createdAt = Date(),
                updatedAt = Date(),
                pushedAt = Date()
            ),
            RepositoryVO(
                id = 4,
                name = "Andromeda",
                author = "Marina",
                userAvatar = "https://picsum.photos/200?random=4",
                description = "Andromeda project, vast and expansive.",
                language = "Swift",
                licenseName = "BSD 3-Clause",
                starCount = 1456,
                forkCount = 330,
                watcherCount = 700,
                issueCount = 90,
                createdAt = Date(),
                updatedAt = Date(),
                pushedAt = Date()
            ),
            RepositoryVO(
                id = 5,
                name = "Cosmos",
                author = "Rafael",
                userAvatar = "https://picsum.photos/200?random=5",
                description = "The entire Cosmos in a single app.",
                language = "JavaScript",
                licenseName = "MIT",
                starCount = 765,
                forkCount = 120,
                watcherCount = 300,
                issueCount = 40,
                createdAt = Date(),
                updatedAt = Date(),
                pushedAt = Date()
            ),
            RepositoryVO(
                id = 6,
                name = "Eclipse",
                author = "Fernanda",
                userAvatar = "https://picsum.photos/200?random=6",
                description = "Eclipse: overshadowing the competition.",
                language = "Kotlin",
                licenseName = "Apache 2.0",
                starCount = 543,
                forkCount = 87,
                watcherCount = 250,
                issueCount = 30,
                createdAt = Date(),
                updatedAt = Date(),
                pushedAt = Date()
            ),
            RepositoryVO(
                id = 7,
                name = "Nova",
                author = "Thiago",
                userAvatar = "https://picsum.photos/200?random=7",
                description = "A supernova of innovation.",
                language = "Java",
                licenseName = "MIT",
                starCount = 2389,
                forkCount = 456,
                watcherCount = 1000,
                issueCount = 150,
                createdAt = Date(),
                updatedAt = Date(),
                pushedAt = Date()
            ),
            RepositoryVO(
                id = 50,
                name = "Hades",
                author = "Fernando",
                userAvatar = "https://picsum.photos/200?random=50",
                description = "Exploring the underworld of low-level programming.",
                language = "C++",
                licenseName = "Unlicense",
                starCount = 1594,
                forkCount = 327,
                watcherCount = 800,
                issueCount = 120,
                createdAt = Date(),
                updatedAt = Date(),
                pushedAt = Date()
            )
        )
    }
}
