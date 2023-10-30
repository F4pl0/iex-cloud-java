# iex-cloud-java

![IEX Cloud Logo](https://www.giantmachines.com/wp-content/uploads/2023/03/5e7e3da8df522d95bd159c94_IEX-Cloud-e1680079186819.png)

This is a Java library for interfacing with the [IEX Cloud](https://iexcloud.io/) API. It provides a convenient and straightforward way to access a wide range of financial market data, such as stock quotes, historical data, and more.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Examples](#examples)
- [Documentation](#documentation)
- [Contributing](#contributing)
- [License](#license)

## Installation

You can add this library to your Java project using Maven:

```xml
<dependency>
    <groupId>io.github.f4pl0</groupId>
    <artifactId>iex-cloud</artifactId>
    <version>1.12.0</version>
</dependency>
```

Or, if you are using Gradle:

```gradle
dependencies {
    implementation 'io.github.f4pl0:iex-cloud:1.12.0'
}
```

## Usage

Before using this library, you will need to obtain an API key from IEX Cloud. You can sign up for an API key [here](https://iexcloud.io/cloud-login#/register/).

Once you have an API key, you can use it to initialize the IEX Cloud client:

```java
import io.github.f4pl0.IEXCloudClient;

public class Main {
    public static void main(String[] args) {
        IEXCloudClient client = new IEXCloudClient.IEXCloudClientBuilder()
                .setPublishableToken("pk_yourpublishabletoken")
                .build();
    }
}
```

You can then use the client to access various endpoints of the IEX Cloud API.

## Examples

Here are some examples of how to use this library:

### Get a Stock Quote

```java
StockQuote quote = client.getStockQuote("AAPL");
System.out.println("Apple Inc. (AAPL) current price: " + quote.getLatestPrice());
```

### Retrieve Historical Prices

```java
List<HistoricalPrice> historicalPrices = client.getHistoricalPrices("AAPL", 5, ChartRange.ONE_MONTH);
for (HistoricalPrice price : historicalPrices) {
    System.out.println(price.getDate() + ": " + price.getClose());
}
```

### Fetch Market News

```java
List<NewsArticle> news = client.getMarketNews();
for (NewsArticle article : news) {
    System.out.println(article.getHeadline());
}
```

## Documentation

For more details on how to use this library and the available API endpoints, refer to the [wiki](https://github.com/F4pl0/iex-cloud-java/wiki).

## Contributing

Contributions from the community are welcome. If you find a bug, have a feature request, or want to contribute in any way, please check out our [contribution guidelines](CONTRIBUTING.md).

## License

This library is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Enjoy using the IEX Cloud API Wrapper Library for Java! If you have any questions or need assistance, please feel free to [open an issue](https://github.com/F4pl0/iex-cloud-java/issues).