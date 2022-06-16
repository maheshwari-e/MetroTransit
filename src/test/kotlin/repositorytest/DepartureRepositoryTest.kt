package repositorytest

import org.junit.jupiter.api.Test
import repository.*
import kotlin.test.assertEquals

class DepartureRepositoryTest {

    private val fakeDepartureRepository = object : DepartureRepository {

        override fun getAllTime(routeId: String, directionId: String, stopId: String): List<Long> {
            return listOf(1655386541, 1655388480, 1655390160, 1655391960)
        }
    }

    private val fakeDepartureRepositoryForEmptyList = object : DepartureRepository {

        override fun getAllTime(routeId: String, directionId: String, stopId: String): List<Long> {
            return emptyList()
        }
    }

    private val currentTime = 1655385762274

    @Test
    fun `Verify the final result of waiting time`() {
        assertEquals(
            expected = "12 Minutes",
            actual = getWaitingTime(
                routeId = "612",
                directionId = "0",
                stopId = "HEUP",
                repository = fakeDepartureRepository,
                currentTime = currentTime
            )
        )
    }

    @Test
    fun `Verify the waiting time for no more trips available (emptyList)`() {
        assertEquals(
            expected = "The Last bus for the day already left or No More bus available",
            actual = getWaitingTime(
                routeId = "612",
                directionId = "0",
                stopId = "HEUP",
                repository = fakeDepartureRepositoryForEmptyList,
                currentTime = currentTime
            )
        )
    }
}