package io.polygon.kotlin.sdk.rest.forex

import io.ktor.http.*
import io.polygon.kotlin.sdk.rest.stocks.GainersOrLosersDirection
import io.polygon.kotlin.sdk.rest.stocks.SnapshotAggregateDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** See [PolygonForexClient.getSnapshotAllTickersBlocking] */
suspend fun PolygonForexClient.getSnapshotAllTickers(): SnapshotForexTickersDTO =
    polygonClient.fetchResult { path("v2", "snapshot", "locale", "global", "markets", "forex", "tickers") }

/** See [PolygonForexClient.getSnapshotGainersOrLosersBlocking] */
suspend fun PolygonForexClient.getSnapshotGainersOrLosers(direction: GainersOrLosersDirection): SnapshotForexTickersDTO =
    polygonClient.fetchResult { path("v2", "snapshot", "locale", "global", "markets", "forex", direction.queryParamValue) }

@Serializable
data class SnapshotForexTickersDTO(
    val status: String? = null,
    val tickers: List<SnapshotForexTickerDTO> = emptyList()
)

@Serializable
data class SnapshotForexTickerDTO(
    val ticker: String? = null,
    val todaysChange: Double? = null,
    val todaysChangePerc: Double? = null,
    val updated: Long? = null,
    val day: SnapshotAggregateDTO = SnapshotAggregateDTO(),
    val min: SnapshotAggregateDTO = SnapshotAggregateDTO(),
    val prevDay: SnapshotAggregateDTO = SnapshotAggregateDTO(),
    val lastQuote: SnapshotForexQuoteDTO = SnapshotForexQuoteDTO()
)

@Serializable
data class SnapshotForexQuoteDTO(
    @SerialName("a") val ask: Double? = null,
    @SerialName("b") val bid: Double? = null,
    @SerialName("x") val exchange: Long? = null,
    @SerialName("t") val timestamp: Long? = null
)