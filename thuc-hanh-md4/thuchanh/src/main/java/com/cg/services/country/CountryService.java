package com.cg.services.country;

import com.cg.model.Country;
import com.cg.model.dto.CountryDTO;
import com.cg.services.IGeneralService;

import java.util.List;

public interface CountryService extends IGeneralService<Country> {

    List<CountryDTO> findAllCountryDTO();
}
