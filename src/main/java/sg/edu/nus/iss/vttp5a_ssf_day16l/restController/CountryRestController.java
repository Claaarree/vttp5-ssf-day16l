package sg.edu.nus.iss.vttp5a_ssf_day16l.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.vttp5a_ssf_day16l.model.Country;
import sg.edu.nus.iss.vttp5a_ssf_day16l.service.CountryRestService;

@RestController
public class CountryRestController {
    
    @Autowired
    CountryRestService countryRestService;

    @GetMapping()
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = new ArrayList<>();
        countries = countryRestService.getCountries();

        return ResponseEntity.ok().body(countries);
    }
}
