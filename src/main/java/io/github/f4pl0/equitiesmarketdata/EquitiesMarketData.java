package io.github.f4pl0.equitiesmarketdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.f4pl0.IEXHttpClient;
import io.github.f4pl0.config.IEXCloudConfig;
import io.github.f4pl0.equitiesmarketdata.data.IEXDEEP;
import io.github.f4pl0.equitiesmarketdata.data.IEXIntradayEquityPrice;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class EquitiesMarketData {
    private final IEXHttpClient httpClient = IEXHttpClient.getInstance();
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * IEX Depth of Book (DEEP)
     *
     * <p>
     *     DEEP is used to receive real-time depth of book quotations direct from IEX. The depth of book quotations
     *     received via DEEP provide an aggregated size of resting displayed orders at a price and side, and do not
     *     indicate the size or number of individual orders at any price level. Non-displayed orders and non-displayed
     *     portions of reserve orders are not represented in DEEP.
     * </p>
     *
     * <p>
     *     DEEP also provides last trade price and size information. Trades resulting from either displayed or
     *     non-displayed orders matching on IEX will be reported. Routed executions will not be reported.
     * </p>
     *
     * @see <a href="https://iexcloud.io/docs/core/IEX_DEEP#iex-depth-of-book-deep">IEX Cloud API</a>
     * @param symbol The stock symbol to get the DEEP data for.
     * @return The DEEP data for the given stock symbol.
     * @throws IOException If the request fails.
     */
    public IEXDEEP getDEEP(@NonNull String symbol) throws IOException {
        String encodedSymbol = URLEncoder.encode(symbol, StandardCharsets.UTF_8);
        CloseableHttpResponse response = httpClient.execute("/data/core/iex_deep/" + encodedSymbol);

        List<IEXDEEP> data = mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXDEEP.class));

        return data.get(0);
    }

    /**
     * IEX Depth of Book (DEEP)
     *
     * <p>
     *     DEEP is used to receive real-time depth of book quotations direct from IEX. The depth of book quotations
     *     received via DEEP provide an aggregated size of resting displayed orders at a price and side, and do not
     *     indicate the size or number of individual orders at any price level. Non-displayed orders and non-displayed
     *     portions of reserve orders are not represented in DEEP.
     * </p>
     *
     * <p>
     *     DEEP also provides last trade price and size information. Trades resulting from either displayed or
     *     non-displayed orders matching on IEX will be reported. Routed executions will not be reported.
     * </p>
     *
     * @see <a href="https://iexcloud.io/docs/core/IEX_DEEP#iex-depth-of-book-deep">IEX Cloud API</a>
     * @param symbols The stock symbols to get the DEEP data for.
     * @return The DEEP data for the given stock symbols.
     * @throws IOException If the request fails.
     */
    public List<IEXDEEP> getDEEP(@NonNull String[] symbols) throws IOException {
        List<String> encodedSymbols = new ArrayList<>(symbols.length);
        for (String symbol : symbols) {
            encodedSymbols.add(URLEncoder.encode(symbol, StandardCharsets.UTF_8));
        }
        String joinedSymbols = String.join(",", encodedSymbols);
        CloseableHttpResponse response = httpClient.execute("/data/core/iex_deep/" + joinedSymbols);

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXDEEP.class));
    }

    /**
     * Intraday Equity Prices
     *
     * <p>
     *     Aggregated intraday prices in n-minute buckets for the current day, where n is 10 minutes or 30 minutes,
     *     depending on the range you specify.
     * </p>
     *
     * @see <a href="https://iexcloud.io/docs/core/INTRADAY_PRICES#intraday-equity-prices">IEX Cloud API</a>
     * @param symbol The stock symbol to get the intraday prices for.
     * @return The intraday prices for the given stock symbol.
     * @throws IOException If the request fails.
     */
    public List<IEXIntradayEquityPrice> getIntradayPrices(@NonNull String symbol) throws IOException {
        String encodedSymbol = URLEncoder.encode(symbol, StandardCharsets.UTF_8);
        CloseableHttpResponse response = httpClient.execute("/data/core/intraday-prices/" + encodedSymbol);

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXIntradayEquityPrice.class));
    }
    // TODO: Add intraday prices for multiple symbols. and support for the range parameter.
    // TODO: Finish the rest of the endpoints.


}
