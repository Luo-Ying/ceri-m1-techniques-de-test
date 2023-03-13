package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class IPokemonMetadataProviderTest {

    IPokemonMetadataProvider pokemonMetadataProvider;

    PokemonMetadata pokemonMetadata;

    @BeforeEach
    void Initialize(){
        pokemonMetadata = new PokemonMetadata(0, "bizarre", 126, 126, 90);
        pokemonMetadataProvider = new IPokemonMetadataProvider() {
            @Override
            public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
                return pokemonMetadata;
            }
        };
    }

    @Test
    void PokemonMetadataTest() throws PokedexException {
        assertEquals(pokemonMetadata, pokemonMetadataProvider.getPokemonMetadata(0));
    }

}
