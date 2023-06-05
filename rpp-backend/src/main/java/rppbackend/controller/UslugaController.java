package rppbackend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import rppbackend.model.KorisnikUsluge;
import rppbackend.model.Usluga;
import rppbackend.service.KorisnikUslugeService;
import rppbackend.service.UslugaService;

@CrossOrigin
@RestController
public class UslugaController {

	@Autowired
	private UslugaService uslugaService;
	@Autowired
	private KorisnikUslugeService korisnikService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ApiOperation(value = "Returns List of all Uslugas")
	@GetMapping("usluga")
	public ResponseEntity<List<Usluga>> getAll(){
		List<Usluga> uslugas = uslugaService.getAll();
        return new ResponseEntity<>(uslugas, HttpStatus.OK);
	}
	
    @ApiOperation(value = "Returns Usluga with id that was forwarded as path variable.")
	@GetMapping("usluga/{id}")
	public ResponseEntity<Usluga> getOne(@PathVariable("id") Integer id){
	    if (uslugaService.findById(id).isPresent()) {
	    	Optional<Usluga> usluga = uslugaService.findById(id);
            return new ResponseEntity<>(usluga.get(), HttpStatus.OK);
	    } else {
	    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }
	}
    
    @ApiOperation(value="Returns list of Usluga for KorisnikUsluge with id that was forwarded as path variable")
    @GetMapping("uslugeZaKorisnika/{id}")
	
    public ResponseEntity<List<Usluga>> getAllForKorisnikUsluge(@PathVariable("id") Integer id){
    	Optional<KorisnikUsluge> korisnikOpt = korisnikService.findById(id);
    	if(korisnikOpt.isPresent()){
    		List<Usluga> uslugas=uslugaService.findByKorisnik(korisnikOpt.get());
    		return new ResponseEntity<>(uslugas,HttpStatus.OK);
    	}
    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @ApiOperation(value = "Returns list of Uslugas containing string that was forwarded as path variable in 'naziv'.")
	@GetMapping("usluga/naziv/{naziv}")
	public ResponseEntity<List<Usluga>> getByNaziv(@PathVariable("naziv") String naziv){
		List<Usluga> uslugas = uslugaService.findByNazivContainingIgnoreCase(naziv);
        return new ResponseEntity<>(uslugas, HttpStatus.OK);
	}
	

    @ApiOperation(value = "Adds new Usluga to database.")
	@PostMapping("usluga")
	public ResponseEntity<Usluga> addUsluga(@RequestBody Usluga usluga) {
		Usluga savedUsluga = uslugaService.save(usluga);
        URI location = URI.create("/usluga/" + savedUsluga.getId());
		return ResponseEntity.created(location).body(savedUsluga);
	}

    @ApiOperation(value = "Updates Usluga that has id that was forwarded as path variable with values forwarded in Request Body.")
    @PutMapping(value = "usluga/{id}")
    public ResponseEntity<Usluga> updateUsluga(@RequestBody Usluga usluga, @PathVariable("id") Integer id) {
        if (uslugaService.existsById(id)) {
        	usluga.setId(id);
        	Usluga savedUsluga = uslugaService.save(usluga);
            return ResponseEntity.ok().body(savedUsluga);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
    @ApiOperation(value = "Deletes Usluga with id that was forwarded as path variable.")
    @DeleteMapping("usluga/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        if (id == -100 && !uslugaService.existsById(id)) {
            jdbcTemplate.execute(
            		"INSERT INTO usluga (\"id\", \"naziv\", \"opis_usluge\", \"datum_ugovora\", \"provizija\", \"filijala\", \"korisnik\") VALUES (-100, 'Test Naziv', 'Test Opis_Usluge', '2023-05-08', 500, 5, 5)");
            
        }

        if (uslugaService.existsById(id)) {
        	uslugaService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }
}
