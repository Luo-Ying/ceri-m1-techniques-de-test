package fr.univavignon.pokedex.api;

import static fr.univavignon.pokedex.api.Team.MYSTIC;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IPokemonTrainerFactoryTest {

    IPokemonTrainerFactory pokemonTrainerFactory;

    PokemonTrainer pokemonTrainer;

    String name;

    Team team;

    IPokedexFactory pokedexFactory;

    @BeforeEach
    void initialize(){

        name = "pokemonx";

        team = MYSTIC;

        Pokemon pokemonx = new Pokemon(1, "pokemonx", 999, 999, 999, 999, 999, 999, 999, 999);
        Pokemon pokemony = new Pokemon(2, "pokemony", 888, 888, 888, 888, 888, 888, 888, 888);

        List<Pokemon> pokemonList = new ArrayList<>();

        pokemonList.add(pokemonx);
        pokemonList.add(pokemony);

        PokemonMetadata pokemonMetadata = new PokemonMetadata(99, "pokemon meta data", 9999, 9999, 9999);

        IPokedex pokedex = new IPokedex() {
            @Override
            public int size() {
                return 1;
            }

            @Override
            public int addPokemon(Pokemon pokemon) {
                return 1;
            }

            @Override
            public Pokemon getPokemon(int id) throws PokedexException {
                if (id == 1){
                    return pokemonx;
                } else if (id == 2){
                    return pokemony;
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
                return new Pokemon(3, "pokemonz", 666, 666, 666, 666, 666, 666, 666, 666);
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

        pokemonTrainer = new PokemonTrainer(name, team, pokedex);

        pokemonTrainerFactory = new IPokemonTrainerFactory() {
            @Override
            public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
                return pokemonTrainer;
            }
        };

    }

    @Test
    void createTrainerTest(){
        assertEquals(pokemonTrainer, pokemonTrainerFactory.createTrainer(name, team, pokedexFactory));
    }

}
