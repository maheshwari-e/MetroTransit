package transformertest

import model.Departure
import model.NextTrips
import org.junit.jupiter.api.Test
import transformer.DepartureMapTransformer
import kotlin.test.assertEquals

class DepartureMapTransformerTest {

    @Test
    fun `Verify the conversion from nextTrips to list Of (long) departure time`() {
        val nexTrips = NextTrips(
            departures = listOf(
                Departure(departureTime = 1655386541),
                Departure(departureTime = 1655388480),
                Departure(departureTime = 1655390160),
                Departure(departureTime = 1655391960)
            )
        )

        assertEquals(
            expected = listOf<Long>(1655386541, 1655388480, 1655390160, 1655391960),
            actual = DepartureMapTransformer.invoke(nexTrips)
        )
    }

    @Test
    fun `Verify the conversion from nextTrips as nullable to list Of (long) departure time`() {
        assertEquals(
            expected = emptyList(),
            actual = DepartureMapTransformer.invoke(null)
        )
    }

    @Test
    fun `Verify the conversion from no more available nextTrips to list Of (long) departure time`() {
        val nexTripsNotAvailable = NextTrips(departures = listOf())

        assertEquals(
            expected = emptyList(),
            actual = DepartureMapTransformer.invoke(nexTripsNotAvailable)
        )
    }

    @Test
    fun `Verify the conversion from departures as nullable to list Of (long) departure time`() {
        val nexTripsNotAvailable = NextTrips(departures = null)

        assertEquals(
            expected = emptyList(),
            actual = DepartureMapTransformer.invoke(nexTripsNotAvailable)
        )
    }
}