package controller;

import API.model.Programa;
import API.model.RiderDTO;
import API.service.ProgramaService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programa")
@CrossOrigin(origins = "*")
public class ProgramaController {

    @Autowired
    private ProgramaService ps;

    @PostMapping("")
    public ResponseEntity addPrograma(@RequestBody final @NotNull Programa p) { return ps.addPrograma(p);}

    @GetMapping("/getAll")
    public List<Programa> getAll() {
        return ps.getAllProgramas();
    }

    @PostMapping("/{id}/update")
    public ResponseEntity updatePrograma(@PathVariable final @NotNull Integer id, @RequestBody final @NotNull Programa p) {
        return ps.updatePrograma(id, p);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity deletePrograma(@PathVariable final @NotNull Integer id) {
        return ps.deletePrograma(id);
    }

    @GetMapping("/{id}")
    public Programa getPrograma(@PathVariable final @NotNull Integer id) {
        return ps.getPrograma(id);
    }

    @GetMapping("/{id}/riders")
    public List<RiderDTO> getProgramaRiders(@PathVariable Integer id) {
        return ps.getProgramaRiders(id);
    }

}
