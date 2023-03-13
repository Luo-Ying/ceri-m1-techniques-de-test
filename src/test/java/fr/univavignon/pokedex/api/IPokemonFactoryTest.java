package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class IPokemonFactoryTest {

    IPokemonFactory pokemonFactory;

    Pokemon pokemon;

    int index;

    int cp;

    int hp;

    int dust;

    int candy;

    @BeforeEach
    void initialize(){

        pokemon = new Pokemon(0, "pokemonx", 999, 999, 999, 999, 999, 99, 99, 99);

        index = 0;

        cp = 999;

        hp = 999;

        dust = 99;

        candy = 99;

        pokemonFactory = new IPokemonFactory() {
            @Override
            public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
                return pokemon;
            }
        };

    }

    @Test
    void createPokemonTest(){
        assertEquals(pokemon, pokemonFactory.createPokemon(index, cp, hp, dust, candy));
    }

}
