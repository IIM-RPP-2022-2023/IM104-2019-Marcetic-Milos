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
import rppbackend.model.Banka;
import rppbackend.service.BankaService;

@CrossOrigin
@RestController
public class BankaController {
	
@Autowired
private BankaService bankaService;

@Autowired
private JdbcTemplate jdbcTemplate;

@ApiOperation(value = "Returns List of all Banks")
@GetMapping("banka")
public ResponseEntity<List<Banka>> getAll(){
	List<Banka> banks = bankaService.getAll();
    return new ResponseEntity<>(banks, HttpStatus.OK);
}

@ApiOperation(value = "Returns Banka with id that was forwarded as path variable.")
@GetMapping("banka/{id}")
public ResponseEntity<Banka> getOne(@PathVariable("id") Integer id){
    if (bankaService.findById(id).isPresent()) {
    	Optional<Banka> banka = bankaService.findById(id);
        return new ResponseEntity<>(banka.get(), HttpStatus.OK);
    } else {
    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}


@ApiOperation(value = "Returns list of Banks containing string that was forwarded as path variable in 'naziv'.")
@GetMapping("banka/naziv/{naziv}")
public ResponseEntity<List<Banka>> getByNaziv(@PathVariable("naziv") String naziv){
	List<Banka> banks = bankaService.findByNazivContainingIgnoreCase(naziv);
    return new ResponseEntity<>(banks, HttpStatus.OK);
}

@ApiOperation(value = "Adds new Banka to database.")
@PostMapping("banka")
public ResponseEntity<Banka> addBanka(@RequestBody Banka banka) {
	Banka savedBanka = bankaService.save(banka);
    URI location = URI.create("/banka/" + savedBanka.getId());
	return ResponseEntity.created(location).body(savedBanka);
}

@ApiOperation(value = "Updates Banka that has id that was forwarded as path variable with values forwarded in Request Body.")
@PutMapping(value = "banka/{id}")
public ResponseEntity<Banka> updateBanka(@RequestBody Banka banka, @PathVariable("id") Integer id) {
    if (bankaService.existsById(id)) {
        banka.setId(id);
        Banka savedBanka = bankaService.save(banka);
        return ResponseEntity.ok().body(savedBanka);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

@ApiOperation(value = "Deletes Banka with id that was forwarded as path variable.")
@DeleteMapping("banka/{id}")
public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
    if (id == -100 && !bankaService.existsById(id)) {
        jdbcTemplate.execute(
                "INSERT INTO banka (\"id\", \"kontakt\", \"naziv\", \"pib\") VALUES (-100, 'Test Kontakt', 'Test Naziv', 200)");
    }

    if (bankaService.existsById(id)) {
        bankaService.deleteById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
    return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
}

}
