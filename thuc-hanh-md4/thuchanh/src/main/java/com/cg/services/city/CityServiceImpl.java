package com.cg.services.city;

import com.cg.model.City;
import com.cg.model.dto.CityDTO;
import com.cg.repository.CityRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService{
    @Autowired
    CityRepository cityRepository;
    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public City getById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

//    @Override
//    public List<CityDTO> getAllCityDTO() {
//        return cityRepository.getAllCityDTO();
//    }
}
