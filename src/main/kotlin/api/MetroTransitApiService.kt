package api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import model.Direction
import model.NextTrips
import model.Route
import model.Stop
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MetroTransitApiService {

    @GET("routes")
    fun getRoutes(): Call<List<Route>>

    @GET("directions/{routeId}")
    fun getDirections(@Path("routeId") routeId: String): Call<List<Direction>>

    @GET("stops/{routeId}/{directionId}")
    fun getStops(
        @Path("routeId") routeId: String,
        @Path("directionId") directionId: String
    ): Call<List<Stop>>

    @GET("{routeId}/{directionId}/{stopId}")
    fun getDepartureTimes(
        @Path("routeId") routeId: String,
        @Path("directionId") directionId: String,
        @Path("stopId") stopId: String
    ): Call<NextTrips>

    companion object{
        private const val BASE_URL = "https://svc.metrotransit.org/nextripv2/"

        fun create(): MetroTransitApiService {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val retrofit =  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            return retrofit.create(MetroTransitApiService::class.java)
        }
    }
}
