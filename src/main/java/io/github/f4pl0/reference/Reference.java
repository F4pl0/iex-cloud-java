package io.github.f4pl0.reference;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.f4pl0.IEXCloudConfig;
import io.github.f4pl0.reference.data.IEXTradingSymbol;
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
import java.util.List;


public class Reference {
    private final CloseableHttpClient httpClient;
    private final IEXCloudConfig config;

    public Reference(IEXCloudConfig config) {
        this.httpClient = HttpClients.createDefault();
        this.config = config;
    }

    @SneakyThrows(URISyntaxException.class)
    public List<IEXTradingSymbol> dailyIEXTradingSymbols() throws IOException {
        URI uri = new URIBuilder(config.getBaseEndpoint() + "/data/core/ref_data_iex_symbols")
                .addParameter("token", config.getPublishableToken())
                .build();
        HttpGet request = new HttpGet(uri);

        CloseableHttpResponse response = httpClient.execute(request);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IOException("Request failed: " + response.getStatusLine());
        }

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(
                EntityUtils.toString(response.getEntity()),
                mapper.getTypeFactory().constructCollectionType(List.class, IEXTradingSymbol.class));
    }
}
