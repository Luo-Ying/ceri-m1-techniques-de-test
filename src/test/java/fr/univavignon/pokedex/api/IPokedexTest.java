package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IPokedexTest {

    int size;

    int reussit;

    IPokedex pokedex;

    Pokemon pokemon1, pokemon2;

    List<Pokemon> pokemonList;

    Comparator<Pokemon> pokemonListOrder;

    @BeforeEach
    void initialize(){

        size = 1;

        reussit = 1;

        pokemon1 = new Pokemon(0, "pokemon1", 1, 1, 1, 1, 1, 1, 1, 1);
        pokemon2 = new Pokemon(1, "pokemon2", 2, 2, 2, 2, 2, 2, 2, 2);


        pokemonList = new ArrayList<>();

        pokemonList.add(pokemon1);
        pokemonList.add(pokemon2);

        PokemonMetadata pokemonMetadata = new PokemonMetadata(0, "pokemon1", 999, 999, 999);

        pokemonListOrder = new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                return 0;
            }
        };

        pokedex = new IPokedex() {
            @Override
            public int size() {
                return size;
            }

            @Override
            public int addPokemon(Pokemon pokemon) {
                return reussit;
            }

            @Override
            public Pokemon getPokemon(int id) throws PokedexException {
                if(id == 1){
                    return pokemon1;
                } else if (id == 2){
                    return pokemon2;
                }
                return null;
            }

            @Override
            public List<Pokemon> getPokemons() {
                return pokemonList;
            }

            @Override
            public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
                Collections.sort(pokemonList, order);
                return pokemonList;
            }

            @Override
            public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
                return new Pokemon(1, "pokemon2", 888, 888, 888, 888, 888, 88, 8, 8);
            }

            @Override
            public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
                return pokemonMetadata;
            }
        };
    }

    @Test
    void sizeTest() throws PokedexException {
        assertEquals(size, pokedex.size());
    }

    @Test
    void addPokemonTest() throws PokedexException {
        assertEquals(1, pokedex.addPokemon(new Pokemon(3, "pokemonx", 999, 999, 999, 999, 999, 99, 9, 9)));
    }

    @Test
    void getPokemonTest() throws PokedexException {
        assertEquals(pokemon1, pokedex.getPokemon(1));
    }

    @Test
    void getPokemonsTest(){
        assertEquals(pokemonList, pokedex.getPokemons());
    }

    @Test
    void getPokemonsCompareTest(){
        assertEquals(pokemonList, pokedex.getPokemons(pokemonListOrder));
    }

}
