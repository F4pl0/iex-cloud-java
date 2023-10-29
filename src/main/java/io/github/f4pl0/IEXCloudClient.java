package io.github.f4pl0;

import io.github.f4pl0.equitiesmarketdata.EquitiesMarketData;
import io.github.f4pl0.reference.Reference;

public class IEXCloudClient {
    public final EquitiesMarketData equitiesMarketData;
    public final Reference reference;

    private IEXCloudClient(IEXCloudConfig config) {

        equitiesMarketData = new EquitiesMarketData(config);
        reference = new Reference(config);
    }

    /**
     * Builder for the IEXCloudClient.
     * You will need to set the publishable and secret tokens before building the client.
     */
    public static class IEXCloudClientBuilder {
        private String publishableToken;
        private String secretToken;
        private String baseEndpoint = "https://api.iex.cloud/v1";

        /**
         * Set the publishable token for the IEXCloudClient.
         * @param publishableToken The IEX Cloud publishable token.
         * @return The IEXCloudClientBuilder object.
         */
        public IEXCloudClientBuilder setPublishableToken(String publishableToken) {
            this.publishableToken = publishableToken;
            return this;
        }

        /**
         * Set the secret token for the IEXCloudClient.
         * @param secretToken The IEX Cloud secret token.
         * @return The IEXCloudClientBuilder object.
         */
        public IEXCloudClientBuilder setSecretToken(String secretToken) {
            this.secretToken = secretToken;
            return this;
        }

        /**
         * Set the base endpoint for the IEXCloudClient.
         * @param baseEndpoint The IEX Cloud base endpoint.
         * @return The IEXCloudClientBuilder object.
         */
        public IEXCloudClientBuilder setBaseEndpoint(String baseEndpoint) {
            this.baseEndpoint = baseEndpoint;
            return this;
        }

        /**
         * Build the IEXCloudClient object.
         * @return The IEXCloudClient object.
         * @throws IllegalArgumentException If the publishable token is not set.
         */
        public IEXCloudClient build() {
            // Validate the builder
            if (publishableToken == null || publishableToken.isEmpty()) {
                throw new IllegalArgumentException("Publishable token must be set");
            }

            IEXCloudConfig config = new IEXCloudConfig();
            config.setPublishableToken(publishableToken);
            config.setSecretToken(secretToken);
            config.setBaseEndpoint(baseEndpoint);
            return new IEXCloudClient(config);
        }
    }
}
