package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    String postalCode;
    String street;
    String neighborhood;
    CityDto city;
    String number;
    String complement;
}
