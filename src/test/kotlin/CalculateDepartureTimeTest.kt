import org.junit.jupiter.api.Test
import repository.calculateDepartureTime
import kotlin.test.assertEquals

class CalculateDepartureTimeTest {

    private val currentTimeInMilliSec = 1655312646970L // Wed Jun 15 2022, 12:04:06

    @Test
    fun `Verify departure time calculation for diff less than or equal to zero`() {
        assertEquals(
            expected = "Arriving soon!",
            actual = calculateDepartureTime(
                departureTime = 1655312300, // Wed Jun 15 2022, 11:58:20
                currentTime = currentTimeInMilliSec
            )
        )
    }

    @Test
    fun `Verify departure time calculation for diff equal to one`() {
        assertEquals(
            expected = "Due in a minute",
            actual = calculateDepartureTime(
                departureTime = 1655312746, // Wed Jun 15 2022, 12:05:46
                currentTime = currentTimeInMilliSec
            )
        )
    }

    @Test
    fun `Verify departure time calculation for diff greater than zero`() {
        assertEquals(
            expected = "3 Minutes",
            actual = calculateDepartureTime(
                departureTime = 1655312882,  // Wed Jun 15 2022, 12:08:02
                currentTime = currentTimeInMilliSec
            )
        )
    }
}
