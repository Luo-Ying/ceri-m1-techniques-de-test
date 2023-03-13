package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IPokedexFactoryTest {

    IPokedexFactory pokedexFactory;

    IPokedex pokedex;

    IPokemonMetadataProvider pokemonMetadataProvider;

    IPokemonFactory pokemonFactory;

    List<Pokemon> pokemonList;

    Pokemon pokemon1, pokemon2, pokemon3;

    @BeforeEach
    void initialize(){

        pokemonFactory = new IPokemonFactory() {
            @Override
            public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
                final String name = "pokemon" + index;
                final int attack = cp * hp;
                final int defense = (int) Math.floor(hp * 0.3);
                final int stamina = (int) Math.floor(hp * 0.25);
                final double iv = (cp + hp) * 0.25;
                return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
            }
        };

        pokemon1 = pokemonFactory.createPokemon(1, 1, 1, 11, 1);
        pokemon2 = pokemonFactory.createPokemon(2, 2, 2, 12, 2);
        pokemon3 = pokemonFactory.createPokemon(3, 3, 3, 13, 3);

        pokemonList = new ArrayList<Pokemon>();

        pokemonList.add(pokemon1);
        pokemonList.add(pokemon2);
        pokemonList.add(pokemon3);

        PokemonMetadata pokemonMetadata = new PokemonMetadata(0, "pokemon meta data", 999, 999, 999);

        pokemonMetadataProvider = new IPokemonMetadataProvider() {
            @Override
            public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
                return pokemonMetadata;
            }
        };

        pokedex = new IPokedex() {
            @Override
            public int size() {
                return pokemonList.size();
            }

            @Override
            public int addPokemon(Pokemon pokemon) {
                return 1;
            }

            @Override
            public Pokemon getPokemon(int id) throws PokedexException {
//                return pokemon;
                if (id == 1){
                    return pokemon1;
                } else if (id == 2){
                    return pokemon2;
                } else if (id == 3){
                    return pokemon3;
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
                return new Pokemon(index, "new pokemon", 999, 999, 999, cp, hp, dust, candy, 999.99);
            }

            @Override
            public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
                return pokemonMetadata;
            }
        };

        pokedexFactory = new IPokedexFactory() {
            @Override
            public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
                return pokedex;
            }
        };

    }

    @Test
    void createPokedexTest(){
        assertEquals(pokedex, pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory));
    }
}
