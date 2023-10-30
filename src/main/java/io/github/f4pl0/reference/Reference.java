package io.github.f4pl0.reference;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.f4pl0.IEXHttpClient;
import io.github.f4pl0.config.IEXCloudConfig;
import io.github.f4pl0.config.IEXConfiguration;
import io.github.f4pl0.reference.data.*;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;


public class Reference {
    private final IEXHttpClient httpClient = IEXHttpClient.getInstance();
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * <h2>Daily IEX Trading Symbols</h2>
     * Returns an array of symbols the Investors Exchange supports for trading. Symbols may be added or removed by the
     * Investors Exchange at any time.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_IEX_SYMBOLS#daily-iex-trading-symbols">IEX Cloud API</a>
     * @throws IOException If the request fails.
     * @return A list of IEXTradingSymbol objects.
     */
    public List<IEXTradingSymbol> dailyIEXTradingSymbols() throws IOException {
        CloseableHttpResponse response = httpClient.execute("/data/core/ref_data_iex_symbols");

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXTradingSymbol.class));
    }

    /**
     * <h2>Forex Reference data</h2>
     * Returns an array of the supported currencies and currency pairs.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_FX#forex-reference-data">IEX Cloud API</a>
     * @throws IOException If the request fails.
     * @return An IEXForexReferenceData object.
     */
    public IEXForexReferenceData forexReferenceData() throws IOException {
        CloseableHttpResponse response = httpClient.execute("/data/core/ref_data_fx");

        TypeReference<List<IEXForexReferenceData>> typeReference = new TypeReference<List<IEXForexReferenceData>>() {};
        List<IEXForexReferenceData> forexData =
                mapper.readValue(EntityUtils.toString(response.getEntity()), typeReference);

        return forexData.get(0);
    }

    /**
     * <h2>Futures Reference Data</h2>
     * Returns an array of the supported futures contract symbols.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_FUTURES#futures-reference-data">IEX Cloud API</a>
     * @return A list of IEXFuturesContractSymbol objects.
     * @throws IOException If the request fails.
     */
    public List<IEXFuturesContractSymbol> futuresReferenceData() throws IOException {
        CloseableHttpResponse response = httpClient.execute("/data/core/ref_data_futures");

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXFuturesContractSymbol.class));
    }

    /**
     * <h2>Futures Reference Data</h2>
     * Returns an array of the supported futures contract symbols.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_FUTURES#futures-reference-data">IEX Cloud API</a>
     * @param underlying The underlying symbol of the futures contract.
     * @return A list of IEXFuturesContractSymbol objects.
     * @throws IOException If the request fails.
     */
    public List<IEXFuturesContractSymbol> futuresReferenceData(@NonNull String underlying) throws IOException {
        String encodedUnderlying = URLEncoder.encode(underlying, StandardCharsets.UTF_8);
        CloseableHttpResponse response =
                httpClient.execute("/data/core/ref_data_futures/" + encodedUnderlying);

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXFuturesContractSymbol.class));
    }

    /**
     * <h2>Mutual Fund Reference Data</h2>
     * Returns an array of the supported mutual fund symbols.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_MUTUAL_FUNDS#mutual-fund-reference-data">IEX Cloud API</a>
     * @return A list of IEXMutualFundSymbol objects.
     * @throws IOException If the request fails.
     */
    public List<IEXMutualFundSymbol> mutualFundReferenceData() throws IOException {
        CloseableHttpResponse response = httpClient.execute("/data/core/ref_data_mutual_funds");

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXMutualFundSymbol.class));
    }

    /**
     * <h2>OTC Reference Data</h2>
     * Returns an array of the supported over-the-counter (OTC) symbols.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_OTC#otc-reference-data">IEX Cloud API</a>
     * @return A list of IEXOTCSymbol objects.
     * @throws IOException If the request fails.
     */
    public List<IEXOTCSymbol> otcReferenceData() throws IOException {
        CloseableHttpResponse response = httpClient.execute("/data/core/ref_data_otc");

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXOTCSymbol.class));
    }

    /**
     * <h2>Options Reference Data</h2>
     * Returns an object keyed by symbol with the value of each symbol being an array of available contract dates.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_OPTIONS#options-reference-data">IEX Cloud API</a>
     * @return A Map of String keys and List of Date values.
     * @throws IOException
     */
    public Map<String, List<Date>> optionsReferenceData() throws IOException {
        CloseableHttpResponse response = httpClient.execute("/data/core/ref_data_options");

        // The response is a list of maps (for some apparent reason there is only 1 entry in the list)
        // so we will get and return only the first entry.
        List<Map<String, List<String>>> data =
                mapper.readValue(
                        EntityUtils.toString(response.getEntity()),
                        mapper.getTypeFactory().constructCollectionType(List.class, Map.class));

        Map<String, List<Date>> parsedData = new HashMap<>();

        // This here is basically just reformatting the string dates into Date objects.
        for (Map.Entry<String, List<String>> entry : data.get(0).entrySet()) {
            List<String> dates = entry.getValue();
            List<Date> parsedDates = new java.util.ArrayList<>(dates.size());

            for (String date : dates) {
                try {
                    parsedDates.add(new java.text.SimpleDateFormat("yyyyMMdd").parse(date));
                } catch (ParseException e) {
                    e.printStackTrace(); // TODO: Implement more robust error logging.
                }
            }

            parsedData.put(entry.getKey(), parsedDates);
        }

        return parsedData;
    }

    /**
     * <h2>Options Reference Data</h2>
     * Returns an object keyed by symbol with the value of each symbol being an array of available contract dates.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_OPTIONS#options-reference-data">IEX Cloud API</a>
     * @param underlying The underlying symbol of the options.
     * @return A List of Options for the given underlying symbols.
     * @throws IOException
     */
    public List<IEXOption> optionsReferenceData(@NonNull String underlying) throws IOException {
        String encodedUnderlying = URLEncoder.encode(underlying, StandardCharsets.UTF_8);
        CloseableHttpResponse response =
                httpClient.execute("/data/core/ref_data_options/" + encodedUnderlying);

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXOption.class));
    }

    /**
     * <h2>Options Reference Data</h2>
     * Returns an object keyed by symbol with the value of each symbol being an array of available contract dates.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_OPTIONS#options-reference-data">IEX Cloud API</a>
     * @param underlying The underlying symbol of the options.
     * @param expirationDate The expiration date of the options.
     * @return A List of Options for the given underlying symbol with the given expiration date.
     * @throws IOException
     */
    public List<IEXOption> optionsReferenceData(
            @NonNull String underlying,
            @NonNull Date expirationDate
    ) throws IOException {
        String encodedUnderlying = URLEncoder.encode(underlying, StandardCharsets.UTF_8);
        String formattedExpirationDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(expirationDate);
        CloseableHttpResponse response = httpClient.execute(
                "/data/core/ref_data_options/" + encodedUnderlying + "/" + formattedExpirationDate);

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXOption.class));
    }

    /**
     * <h2>Options Reference Data</h2>
     * Returns an object keyed by symbol with the value of each symbol being an array of available contract dates.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_OPTIONS#options-reference-data">IEX Cloud API</a>
     * @param underlying List of underlying symbols of the options.
     * @return A List of Options for the given array of underlying symbols.
     * @throws IOException
     */
    public List<IEXOption> optionsReferenceData(@NonNull String[] underlying) throws IOException {
        List<String> encodedUnderlying = new ArrayList<>(underlying.length);
        for (String underlyingSymbol : underlying) {
            encodedUnderlying.add(URLEncoder.encode(underlyingSymbol, StandardCharsets.UTF_8));
        }
        String joinedUnderlying = String.join(",", encodedUnderlying);
        CloseableHttpResponse response =
                httpClient.execute("/data/core/ref_data_options/" + joinedUnderlying);

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXOption.class));
    }

    /**
     * <h2>Options Reference Data</h2>
     * Returns an object keyed by symbol with the value of each symbol being an array of available contract dates.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_OPTIONS#options-reference-data">IEX Cloud API</a>
     * @param underlying List of underlying symbols of the options.
     * @param expirationDate The expiration date of the options.
     * @return A List of Options for the given underlying symbols with the given expiration date.
     * @throws IOException
     */
    public List<IEXOption> optionsReferenceData(
            @NonNull String[] underlying,
            @NonNull Date expirationDate
    ) throws IOException {
        List<String> encodedUnderlying = new ArrayList<>(underlying.length);
        for (String underlyingSymbol : underlying) {
            encodedUnderlying.add(URLEncoder.encode(underlyingSymbol, StandardCharsets.UTF_8));
        }
        String joinedUnderlying = String.join(",", encodedUnderlying);
        String formattedExpirationDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(expirationDate);
        CloseableHttpResponse response = httpClient.execute(
                "/data/core/ref_data_options/" + joinedUnderlying + "/" + formattedExpirationDate);

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXOption.class));
    }

    /**
     * <h2>Sectors Reference Data</h2>
     * Returns an array of the sectors.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_SECTORS#sectors-reference-data">IEX Cloud API</a>
     * @return A list of sectors.
     * @throws IOException If the request fails.
     */
    public List<IEXSector> sectorsReferenceData() throws IOException {
        CloseableHttpResponse response = httpClient.execute("/data/core/ref_data_sectors");

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXSector.class));
    }

    /**
     * <h2>Security Symbol Reference Data</h2>
     * Returns an array of reference data for securities by symbol. Intraday price updates are tracked for these securities.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA#security-symbols-reference-data">IEX Cloud API</a>
     * @return A list of security symbols.
     * @throws IOException If the request fails.
     */
    public List<IEXSecuritySymbol> securitySymbolsReferenceData() throws IOException {
        CloseableHttpResponse response = httpClient.execute("/data/core/ref_data");

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXSecuritySymbol.class));
    }

    /**
     * <h2>Security Tags/Categories</h2>
     * Returns an array of tags. Tags can be found on companies.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_TAGS#security-tags-categories">IEX Cloud API</a>
     * @return A list of tags.
     * @throws IOException If the request fails.
     */
    public List<IEXSecurityTag> securityTags() throws IOException {
        CloseableHttpResponse response = httpClient.execute("/data/core/ref_data_tags");

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXSecurityTag.class));
    }

    /**
     * <h2>U.S. Exchanges</h2>
     * Returns an array of U.S. exchanges.
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_EXCHANGES#u-s-exchanges">IEX Cloud API</a>
     * @return A list of U.S. exchanges.
     * @throws IOException If the request fails.
     */
    public List<IEXUSExchange> usExchanges() throws IOException {
        CloseableHttpResponse response = httpClient.execute("/data/core/ref_data_exchanges");

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXUSExchange.class));
    }

    /**
     * U.S. Holidays & Trading Dates
     * Returns the next trade date
     * @see <a href="https://iexcloud.io/docs/core/REF_DATA_DATES#u-s-holidays-trading-dates">IEX Cloud API</a>
     * @return The next trade date.
     * @throws IOException If the request fails.
     */
    public IEXTradeDate usNextTradeDate() throws IOException {
        CloseableHttpResponse response = httpClient.execute("/data/core/ref_data_dates/trade/next");

        List<IEXTradeDate> dates = mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXTradeDate.class));

        return dates.get(0);
    }

    /**
     * <h2>Autocomplete Search</h2>
     * Returns an array of symbols up to the top 10 matches. Results will be sorted for relevancy. Search currently
     * defaults to equities only, where the symbol returned is supported by endpoints listed under the Stocks category.
     *
     * This endpoint is useful for creating an autocomplete search box.
     * @see <a href="https://iexcloud.io/docs/core/autocomplete-search#autocomplete-search">IEX Cloud API</a>
     * @param fragment The fragment to search for.
     * @return A list of results.
     * @throws IOException If the request fails.
     */
    public List<IEXSecuritySearchResult> search(@NonNull String fragment) throws IOException {
        String encodedFragment = URLEncoder.encode(fragment, StandardCharsets.UTF_8);
        CloseableHttpResponse response = httpClient.execute("/search/" + encodedFragment);

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXSecuritySearchResult.class));
    }

    /**
     * <h2>International Exchanges</h2>
     * Returns an array of reference information about securities exchanges throughout the world.
     * @see <a href="https://iexcloud.io/docs/core/international-exchanges#international-exchanges">IEX Cloud API</a>
     * @return A list of international exchanges.
     * @throws IOException If the request fails.
     */
    public List<IEXInternationalExchange> internationalExchanges() throws IOException {
        CloseableHttpResponse response = httpClient.execute("/ref-data/exchanges");

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXInternationalExchange.class));
    }
}
