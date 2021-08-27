package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.State;

public class CityFactoryUtils {
    public static City sampleCity(State state){
        return new City("Test City", state);
    }
}
