package io.github.f4pl0.companydata;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.f4pl0.IEXHttpClient;
import io.github.f4pl0.companydata.data.IEXCompanyCeoCompensation;
import io.github.f4pl0.companydata.data.IEXCompanyData;
import io.github.f4pl0.companydata.data.IEXCompanyLogo;
import io.github.f4pl0.reference.data.IEXTradeDate;
import lombok.NonNull;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CompanyData {
    private final IEXHttpClient httpClient = IEXHttpClient.getInstance();
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Company Information
     *
     * <p>Latest snapshot of public company information such as address, number of employees, CEO, and description.</p>
     *
     * @see <a href="https://iexcloud.io/docs/core/COMPANY#company-information">IEX Cloud API</a>
     * @param symbol Company symbol.
     * @throws IOException If the request fails.
     * @return A {@link io.github.f4pl0.companydata.data.IEXCompanyData} object.
     */
    public IEXCompanyData companyData(@NonNull String symbol) throws IOException {
        String encodedSymbol = URLEncoder.encode(symbol, StandardCharsets.UTF_8);
        CloseableHttpResponse response = httpClient.execute("/data/core/company/" + encodedSymbol);

        List<IEXCompanyData> data = mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXCompanyData.class));

        return data.get(0);
    }

    /**
     * Company Information
     *
     * <p>Latest snapshot of public company information such as address, number of employees, CEO, and description.</p>
     *
     * @see <a href="https://iexcloud.io/docs/core/COMPANY#company-information">IEX Cloud API</a>
     * @param symbols Company symbols.
     * @throws IOException If the request fails.
     * @return A List of {@link io.github.f4pl0.companydata.data.IEXCompanyData} objects.
     */
    public List<IEXCompanyData> companyData(@NonNull String[] symbols) throws IOException {
        List<String> encodedSymbols = new ArrayList<>(symbols.length);
        for (String symbol : symbols) {
            encodedSymbols.add(URLEncoder.encode(symbol, StandardCharsets.UTF_8));
        }
        String joinedSymbols = String.join(",", encodedSymbols);
        CloseableHttpResponse response = httpClient.execute("/data/core/company/" + joinedSymbols);

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXCompanyData.class));
    }

    /**
     * Historical Company Snapshots
     *
     * <p>Historical daily snapshot of public company information such as address, number of employees, CEO, and description.</p>
     *
     * @see <a href="https://iexcloud.io/docs/core/COMPANY_HISTORICAL#historical-company-snapshots">IEX Cloud API</a>
     * @param symbol Company symbol.
     * @throws IOException If the request fails.
     * @return A List of {@link io.github.f4pl0.companydata.data.IEXCompanyData} objects.
     */
    public List<IEXCompanyData> companyDataSnapshots(@NonNull String symbol) throws IOException {
        String encodedSymbol = URLEncoder.encode(symbol, StandardCharsets.UTF_8);
        CloseableHttpResponse response = httpClient.execute("/data/core/company_historical/" + encodedSymbol);

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXCompanyData.class));
    }

    /**
     * Historical Company Snapshots
     *
     * <p>Historical daily snapshot of public company information such as address, number of employees, CEO, and description.</p>
     *
     * @see <a href="https://iexcloud.io/docs/core/COMPANY_HISTORICAL#historical-company-snapshots">IEX Cloud API</a>
     * @param symbol Company symbol.
     * @param last Number of records to return.
     * @throws IOException If the request fails.
     * @return A List of {@link io.github.f4pl0.companydata.data.IEXCompanyData} objects.
     */
    public List<IEXCompanyData> companyDataSnapshots(@NonNull String symbol, int last) throws IOException {
        String encodedSymbol = URLEncoder.encode(symbol, StandardCharsets.UTF_8);
        CloseableHttpResponse response = httpClient.execute(
                "/data/core/company_historical/" + encodedSymbol + "?last=" + last
        );

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXCompanyData.class));
    }

    /**
     * Historical Company Snapshots
     *
     * <p>Historical daily snapshot of public company information such as address, number of employees, CEO, and description.</p>
     *
     * @see <a href="https://iexcloud.io/docs/core/COMPANY_HISTORICAL#historical-company-snapshots">IEX Cloud API</a>
     * @param symbols Company symbols.
     * @throws IOException If the request fails.
     * @return A List of {@link io.github.f4pl0.companydata.data.IEXCompanyData} objects.
     */
    public List<IEXCompanyData> companyDataSnapshots(@NonNull String[] symbols) throws IOException {
        List<String> encodedSymbols = new ArrayList<>(symbols.length);
        for (String symbol : symbols) {
            encodedSymbols.add(URLEncoder.encode(symbol, StandardCharsets.UTF_8));
        }
        String joinedSymbols = String.join(",", encodedSymbols);
        CloseableHttpResponse response = httpClient.execute("/data/core/company_historical/" + joinedSymbols);

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXCompanyData.class));
    }

    /**
     * Historical Company Snapshots
     *
     * <p>Historical daily snapshot of public company information such as address, number of employees, CEO, and description.</p>
     *
     * @see <a href="https://iexcloud.io/docs/core/COMPANY_HISTORICAL#historical-company-snapshots">IEX Cloud API</a>
     * @param symbols Company symbols.
     * @param last Number of records to return.
     * @throws IOException If the request fails.
     * @return A List of {@link io.github.f4pl0.companydata.data.IEXCompanyData} objects.
     */
    public List<IEXCompanyData> companyDataSnapshots(@NonNull String[] symbols, int last) throws IOException {
        List<String> encodedSymbols = new ArrayList<>(symbols.length);
        for (String symbol : symbols) {
            encodedSymbols.add(URLEncoder.encode(symbol, StandardCharsets.UTF_8));
        }
        String joinedSymbols = String.join(",", encodedSymbols);
        CloseableHttpResponse response = httpClient.execute(
                "/data/core/company_historical/" + joinedSymbols + "?last=" + last
        );

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXCompanyData.class));
    }

    /**
     * CEO Compensation
     *
     * <p>Returns the latest compensation information for the CEO of the company matching the symbol.</p>
     *
     * @see <a href="https://iexcloud.io/docs/core/ceo-compensation#ceo-compensation">IEX Cloud API</a>
     * @param symbol Company symbol.
     * @throws IOException If the request fails.
     * @return A {@link io.github.f4pl0.companydata.data.IEXCompanyCeoCompensation} object.
     */
    public IEXCompanyCeoCompensation companyCeoCompensation(@NonNull String symbol) throws IOException {
        String encodedSymbol = URLEncoder.encode(symbol, StandardCharsets.UTF_8);
        CloseableHttpResponse response = httpClient.execute("/stock/" + encodedSymbol + "/ceo-compensation");

        return mapper.readValue(EntityUtils.toString(response.getEntity()), IEXCompanyCeoCompensation.class);
    }

    /**
     * Company Logo
     *
     * <p>Returns a logo (if available) for the company.</p>
     *
     * @see <a href="https://iexcloud.io/docs/core/company-logo#company-logo">IEX Cloud API</a>
     * @param symbol Company symbol.
     * @throws IOException If the request fails.
     * @return A {@link io.github.f4pl0.companydata.data.IEXCompanyLogo} object.
     */
    public IEXCompanyLogo companyLogo(@NonNull String symbol) throws IOException {
        String encodedSymbol = URLEncoder.encode(symbol, StandardCharsets.UTF_8);
        CloseableHttpResponse response = httpClient.execute("/stock/" + encodedSymbol + "/logo");

        return mapper.readValue(EntityUtils.toString(response.getEntity()), IEXCompanyLogo.class);
    }
}
