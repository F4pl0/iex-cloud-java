package io.github.f4pl0;

import lombok.Data;

@Data
public class IEXCloudConfig {
    private String publishableToken;
    private String secretToken;
    private String baseEndpoint;
}