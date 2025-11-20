package cl.iplacex.discografia.discos;

import cl.iplacex.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepository;

    @Autowired
    private IArtistaRepository artistaRepository;

    // insertar disco
    @PostMapping(
        value = "/disco",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> HandlePostDiscoRequest(@RequestBody Disco disco) {
        
        if (!artistaRepository.existsById(disco.getIdArtista())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Disco saved = discoRepository.save(disco);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // obtener discos
    @GetMapping(
        value = "/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosRequest() {
        List<Disco> discos = discoRepository.findAll();
        return ResponseEntity.ok(discos);
    }

    // obtener disco por id
    @GetMapping(
        value = "/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> HandleGetDiscoRequest(@PathVariable String id) {
        Optional<Disco> discoOpt = discoRepository.findById(id);
        if (discoOpt.isPresent()) {
            return ResponseEntity.ok(discoOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // obtener discos por artista
    @GetMapping(
        value = "/artista/{id}/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable("id") String idArtista) {
        List<Disco> discos = discoRepository.findDiscosByIdArtista(idArtista);
        return ResponseEntity.ok(discos);
    }
}
