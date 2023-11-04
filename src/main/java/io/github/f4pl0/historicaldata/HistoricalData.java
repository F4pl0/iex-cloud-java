package io.github.f4pl0.historicaldata;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.f4pl0.IEXHttpClient;
import io.github.f4pl0.historicaldata.data.IEXHistoricalEquityPrice;
import io.github.f4pl0.historicaldata.enums.DateRange;
import lombok.NonNull;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HistoricalData {
    private final IEXHttpClient httpClient = IEXHttpClient.getInstance();
    private final ObjectMapper mapper = new ObjectMapper();
    
    /**
     * Historical Equity Prices
     *
     * <p>
     *     Returns daily, end of day split adjusted, dividend + split adjusted, and unadjusted equity prices since 2005
     *     for the U.S. and over 100 international exchanges.
     * </p>
     *
     * @see <a href="https://iexcloud.io/docs/core/HISTORICAL_PRICES#historical-equity-prices">IEX Cloud API</a>
     * @param symbol The stock symbol.
     * @param range The date range.
     * @param limit The number of results to return. Defaults to 1. Ignored if range is {@link DateRange#YEAR_TO_DATE}.
     * @throws IOException If the request fails.
     * @return A list of {@link IEXHistoricalEquityPrice} objects.
     */
    public List<IEXHistoricalEquityPrice> historicalEquityPrices(
            @NonNull String symbol,
            @NonNull DateRange range,
            int limit
            ) throws IOException {
        String encodedSymbol = URLEncoder.encode(symbol, StandardCharsets.UTF_8);
        String requestUri = "/data/core/historical_prices/" + encodedSymbol;

        if (range == DateRange.YEAR_TO_DATE) {
            requestUri += "?range=" + range.toString();
        } else if (limit < 1) {
            requestUri += "?range=1" + range.toString();
        } else {
            requestUri += "?range=" + limit + range.toString();
        }

        CloseableHttpResponse response = httpClient.execute(requestUri);

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXHistoricalEquityPrice.class));
    }

    // TODO: Add technical indicators endpoint
}
