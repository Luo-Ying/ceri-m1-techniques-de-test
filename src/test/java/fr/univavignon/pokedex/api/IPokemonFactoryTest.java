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
                Pokemon newPokemon = new Pokemon(index, "pokemonx", 999, 999, 999, cp, hp, dust, candy, 99);
                return newPokemon ;
            }
        };

    }

    @Test
    void createPokemonTest(){
        Pokemon createPokemonTest = pokemonFactory.createPokemon(0, cp, hp, dust, candy);
        assertEquals(pokemon.getIndex(), createPokemonTest.getIndex());
        assertEquals(pokemon.getName(), createPokemonTest.getName());
        assertEquals(pokemon.getAttack(), createPokemonTest.getAttack());
        assertEquals(pokemon.getDefense(), createPokemonTest.getDefense());
        assertEquals(pokemon.getStamina(), createPokemonTest.getStamina());
        assertEquals(pokemon.getCp(), createPokemonTest.getCp());
        assertEquals(pokemon.getHp(), createPokemonTest.getHp());
        assertEquals(pokemon.getDust(), createPokemonTest.getDust());
        assertEquals(pokemon.getCandy(), createPokemonTest.getCandy());
        assertEquals(pokemon.getIv(), createPokemonTest.getIv());
    }

}
