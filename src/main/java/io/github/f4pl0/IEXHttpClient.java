package io.github.f4pl0;

import io.github.f4pl0.config.IEXCloudConfig;
import io.github.f4pl0.config.IEXConfiguration;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;

/**
 * The IEXHttpClient is a wrapper around the Apache HttpClient.
 * It is used to make requests to the IEX Cloud API.
 * @hidden This class is not part of the public API. <b>DO NOT USE</b>.
 */
public class IEXHttpClient {
    private static IEXHttpClient instance;
    @IEXConfiguration
    private IEXCloudConfig config;

    private final CloseableHttpClient httpClient;

    private IEXHttpClient() {
        this.httpClient = HttpClients.createDefault();
    }

    @SneakyThrows
    public CloseableHttpResponse execute(String requestUri) {
        URI uri = new URIBuilder(config.getBaseEndpoint() + requestUri)
                .addParameter("token", config.getPublishableToken())
                .build();

        HttpGet request = new HttpGet(uri);

        CloseableHttpResponse response = httpClient.execute(request);

        if (response.getStatusLine().getStatusCode() >= 400) {
            throw new IOException("Request failed: " + response.getStatusLine());
        }

        return response;
    }

    public static IEXHttpClient getInstance() {
        if (instance == null) {
            instance = new IEXHttpClient();
        }
        return instance;
    }
}
