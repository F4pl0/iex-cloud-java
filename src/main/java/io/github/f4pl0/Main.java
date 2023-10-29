package io.github.f4pl0;

import io.github.f4pl0.reference.data.IEXTradingSymbol;
import lombok.SneakyThrows;

import java.util.List;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        IEXCloudClient client = new IEXCloudClient.IEXCloudClientBuilder()
                .setPublishableToken("pk_5f9b26d6d90e485a87729ac70e5978a6")
                .build();

        List<IEXTradingSymbol> symbols = client.reference.dailyIEXTradingSymbols();
        System.out.println(symbols);
    }
}
