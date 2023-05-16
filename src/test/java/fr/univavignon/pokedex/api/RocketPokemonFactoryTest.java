package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RocketPokemonFactoryTest {

    RocketPokemonFactory pokemonFactory;
    Map<Integer, String> index2name;

    Pokemon pokemon;

    int index;

    int cp;

    int hp;

    int dust;

    int candy;


    @BeforeEach
    void initialize(){

        pokemon = new Pokemon(-1, "pokemonx", 1000, 1000, 1000, 999, 999, 99, 99, 0);

        index = -1;

        cp = 999;

        hp = 999;

        dust = 99;

        candy = 99;

        pokemonFactory = new RocketPokemonFactory() {
            @Override
            public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
                Pokemon newPokemon = new Pokemon(index, "pokemonx", 1000, 1000, 1000, cp, hp, dust, candy, 0);
                return newPokemon ;
            }
        };

    }

    @Test
    void createRocketPokemonFactoryTest(){
        Pokemon createPokemonTest = pokemonFactory.createPokemon(-1, cp, hp, dust, candy);
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
