package com.cg.controller;

import com.cg.model.City;
import com.cg.model.Country;
import com.cg.model.dto.CityDTO;
import com.cg.model.dto.CountryDTO;
import com.cg.services.city.CityService;
import com.cg.services.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    CityService cityService;

    @Autowired
    CountryService countryService;

    @GetMapping
    public ModelAndView showListCities() {
        ModelAndView modelAndView = new ModelAndView();
        List<City> cities = cityService.findAll();
        modelAndView.addObject("cities", cities);
        modelAndView.setViewName("/customers/list");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView();
//        List<Country> countries = countryService.findAll();
        List<CountryDTO> countryDTOS = countryService.findAllCountryDTO();
        modelAndView.addObject("countries", countryDTOS);
        City city = new City();
        modelAndView.addObject("city", city);
        modelAndView.setViewName("/customers/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createNewCity(@Validated @ModelAttribute City city, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        List<City> cities = (List<City>) cityService.findAll();
        modelAndView.addObject("cities", cities);
        if (result.hasFieldErrors()) {
            List<String> errors = new ArrayList<>();
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError o : list) {
                errors.add(o.getDefaultMessage());
            }
            List<Country> countries = countryService.findAll();
            modelAndView.addObject("countries", countries);
            modelAndView.addObject("city", city);
            modelAndView.addObject("error", errors);
            modelAndView.setViewName("/customers/create");
            return modelAndView;
        }
        cityService.save(city);
        modelAndView.setViewName("redirect:/city");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEditCity(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/customers/edit");
//        List<Country> countries =  countryService.findAll();
        List<CountryDTO> countryDTOS = countryService.findAllCountryDTO();
        modelAndView.addObject("countries", countryDTOS);
//        List<Country> countries = countryService.findAll();
//        modelAndView.addObject("countries", countries);
        Optional<City> city = cityService.findById(id);
        if (!city.isPresent()) {
            modelAndView.addObject("error", "the city doesn't exist");
            return modelAndView;
        }
        City newCity = city.get();
        modelAndView.addObject("city", newCity);
        modelAndView.setViewName("/customers/edit");
        return modelAndView;
    }


    @PostMapping("/edit/{id}")
    public ModelAndView editCity(@PathVariable Long id, @Validated @ModelAttribute City city, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        List<City> cities = (List<City>) cityService.findAll();
        if (result.hasFieldErrors()) {
            List<String> errors = new ArrayList<>();
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError o : list) {
                errors.add(o.getDefaultMessage());
            }
            modelAndView.addObject("city", city);
            modelAndView.addObject("error", errors);
            modelAndView.setViewName("/customers/edit");
            return modelAndView;
        }
        cityService.save(city);
        modelAndView.setViewName("redirect:/city");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/deleted/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        cityService.remove(id);
        List<City> cities = (List<City>) cityService.findAll();
        modelAndView.addObject("cities", cities);
        modelAndView.setViewName("/customers/list");
        return modelAndView;
    }

    @PostMapping("/deleted/{id}")
    public ModelAndView deletedCity(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        cityService.remove(id);
        List<City> cities = (List<City>) cityService.findAll();
        modelAndView.addObject("cities", cities);
        modelAndView.setViewName("redirect:/city");
        return modelAndView;
    }
    @GetMapping("/{id}")
    public ModelAndView showInfoCity(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/customers/information");
        Optional<City> city = cityService.findById(id);
        if (!city.isPresent()) {
            modelAndView.addObject("error", "the city doesn't exist");
            return modelAndView;
        }
        City newCity = city.get();
        modelAndView.addObject("city", newCity);
        return modelAndView;
    }

}
