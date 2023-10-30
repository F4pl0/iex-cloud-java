package io.github.f4pl0.config;

import lombok.Data;

/**
 * The IEXCloudConfig is used to configure the IEXCloudClient.
 * @hidden This class is not part of the public API. <b>DO NOT USE</b>.
 */
@Data
public class IEXCloudConfig {
    private String publishableToken;
    private String secretToken;
    private String baseEndpoint;
}