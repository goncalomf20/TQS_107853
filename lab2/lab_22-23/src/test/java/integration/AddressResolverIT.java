package integration;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolverService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.swing.text.html.Option;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressResolverIT {

    private AddressResolverService resolver;

    @BeforeEach
    public void init(){
        //todo
        TqsBasicHttpClient httpClient = new TqsBasicHttpClient();
        resolver = new AddressResolverService(httpClient);        
    }


    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        //todo

        // repeat the same tests conditions from AddressResolverTest, without mocks

        Optional<Address> result = resolver.findAddressForLocation( 40.6349 , -8.6469);
        Address expected = new Address("17 Rua da Banda Amizade", "Aveiro", "3810-060", "");

        assertEquals(expected, result.get());
        

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        //todo
        // repeat the same tests conditions from AddressResolverTest, without mocks
        Optional<Address> result = resolver.findAddressForLocation( -361,-361);
        assertThrows(NoSuchElementException.class, () -> {
            result.get();
        });
        
    }

}
