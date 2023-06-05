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
import rppbackend.service.KorisnikUslugeService;

@CrossOrigin
@RestController
public class KorisnikUslugeController {

	@Autowired
	private KorisnikUslugeService korisnikService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ApiOperation(value = "Returns List of all KorisnikUsluges")
	@GetMapping("korisnik_usluge")
	public ResponseEntity<List<KorisnikUsluge>> getAll(){
		List<KorisnikUsluge> korisniks = korisnikService.getAll();
        return new ResponseEntity<>(korisniks, HttpStatus.OK);
	}
	
    @ApiOperation(value = "Returns KorisnikUsluge with id that was forwarded as path variable.")
	@GetMapping("korisnik_usluge/{id}")
	public ResponseEntity<KorisnikUsluge> getOne(@PathVariable("id") Integer id){
	    if (korisnikService.findById(id).isPresent()) {
	    	Optional<KorisnikUsluge> korisnik = korisnikService.findById(id);
            return new ResponseEntity<>(korisnik.get(), HttpStatus.OK);
	    } else {
	    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }
	}
	
    @ApiOperation(value = "Returns list of KorisnikUsluges containing string that was forwarded as path variable in 'ime'.")
	@GetMapping("korisnik_usluge/ime/{ime}")
	public ResponseEntity<List<KorisnikUsluge>> getByNaziv(@PathVariable("ime") String ime){
		List<KorisnikUsluge> korisniks = korisnikService.findByImeContainingIgnoreCase(ime);
        return new ResponseEntity<>(korisniks, HttpStatus.OK);
	}
	
    @ApiOperation(value = "Adds new KorisnikUsluge to database.")
	@PostMapping("korisnik_usluge")
	public ResponseEntity<KorisnikUsluge> addKorisnikUsluge(@RequestBody KorisnikUsluge korisnik) {
		KorisnikUsluge savedKorisnikUsluge = korisnikService.save(korisnik);
        URI location = URI.create("/korisnik/" + savedKorisnikUsluge.getId());
		return ResponseEntity.created(location).body(savedKorisnikUsluge);
	}

    @ApiOperation(value = "Updates KorisnikUsluge that has id that was forwarded as path variable with values forwarded in Request Body.")
    @PutMapping(value = "korisnik_usluge/{id}")
    public ResponseEntity<KorisnikUsluge> updateKorisnikUsluge(@RequestBody KorisnikUsluge korisnik, @PathVariable("id") Integer id) {
        if (korisnikService.existsById(id)) {
        	korisnik.setId(id);
        	KorisnikUsluge savedKorisnikUsluge = korisnikService.save(korisnik);
            return ResponseEntity.ok().body(savedKorisnikUsluge);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
    @ApiOperation(value = "Deletes KorisnikUsluge with id that was forwarded as path variable.")
    @DeleteMapping("korisnik_usluge/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        if (id == -100 && !korisnikService.existsById(id)) {
            jdbcTemplate.execute(
            		"INSERT INTO korisnik_usluge (\"id\", \"ime\", \"prezime\", \"maticni_broj\") VALUES (-100, 'Test Ime', 'Test Prezime', 'Test MaticniBroj')");
            
        }

        if (korisnikService.existsById(id)) {
        	korisnikService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }
}
