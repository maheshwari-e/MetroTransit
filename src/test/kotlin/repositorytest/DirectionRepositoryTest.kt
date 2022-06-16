package repositorytest

import org.junit.jupiter.api.Test
import repository.DirectionRepository
import repository.getDirection
import kotlin.test.assertEquals

class DirectionRepositoryTest {
    private val fakeDirectionRepository = object : DirectionRepository {
        override fun getAllDirection(routeId: String): Map<String, Int> {
            return mapOf(
                "Eastbound" to 0,
                "Westbound" to 1
            )
        }
    }

    @Test
    fun `Verify Direction Id based on provide direction`() {
        assertEquals(
            expected = 0,
            actual = getDirection(
                routeId = "612",
                name = "Eastbound" ,
                repository = fakeDirectionRepository
            )
        )
    }

    private val list = listOf("Southbound", "Northbound", "Westbound")

    @Test
    fun `Verify Direction Id based on provide invalid direction`() {
        var index = 0

        assertEquals(
            expected = 1,
            actual = getDirection(
                routeId = "612",
                name = "NorthWest" ,
                repository = fakeDirectionRepository,
                readLine = { list[index++] }
            )
        )
    }

    @Test
    fun `Verify Direction Id based without providing direction`() {
        var index = 0

        assertEquals(
            expected = 1,
            actual = getDirection(routeId = "612",
                name = "" ,
                repository = fakeDirectionRepository,
                readLine = { list[index++] })
        )
    }

    @Test
    fun `Verify Direction Id based provide direction as null`() {
        var index = 0

        assertEquals(expected = 1,
            actual = getDirection(
                routeId = "612",
                name = null ,
                repository = fakeDirectionRepository,
                readLine = { list[index++] })
        )
    }
}

