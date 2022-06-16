package transformer

import model.NextTrips

object DepartureMapTransformer {

    operator fun invoke(from: NextTrips?): List<Long> {
        return from?.let {
            it.departures?.let { departures ->
                departures.map { time ->  time.departureTime }
            } ?: emptyList()
        } ?: emptyList()
    }
}
