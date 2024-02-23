package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Unit test for simple App.
 */


public class AppTest {



    //1. Prepare a mock to substitute the remote stockmarket service (@Mock annotation)
    private IStockMarketService stockMarket;

    @Test
    public void testTotalValue() {

        stockMarket = mock(IStockMarketService.class);

        //2. Create an instance of the subject under test (SuT) and use the mock to set the (remote) service instance.
        StocksPortefolio sut = new StocksPortefolio(stockMarket);

        //3. Load the mock with the proper expectations (when...thenReturn)
        when(stockMarket.lookUpPrice(anyString())).thenReturn(100.0);

        //4. Execute the test (use the service in the SuT)
        Stock s = new Stock("Google", 1);
        sut.addStock(s);

        //5. Verify the result (assert) and the use of the mock (verify)
        Assertions.assertEquals(sut.totalValue(), 100.0);
        //TEST ANY STRING
        verify(stockMarket).lookUpPrice("Google");
        //B -> hamcrest tests
        assertThat(100.0, equalTo(stockMarket.lookUpPrice("Google")));
        


    }
    
}
