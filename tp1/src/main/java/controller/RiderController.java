package controller;


import API.model.Rider;
import API.service.RiderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rider")
@CrossOrigin(origins = "*")
public class RiderController {

    @Autowired
    private RiderService rs;

    @PostMapping("")
    public ResponseEntity addRider(@RequestBody final @NotNull Rider r) { return rs.addRider(r);}

    @GetMapping("/{id}")
    public Rider getRider(@PathVariable final @NotNull Integer id) { return rs.getRider(id);}

    @GetMapping("/getAll")
    public List<Rider> getAllRiders() {
        return rs.getAllRiders();
    }

    @PostMapping("/{id}/update")
    public ResponseEntity updateRider(@PathVariable final @NotNull Integer id, @RequestBody final @NotNull Rider r) {
        return rs.updateRider(id, r);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity deleteRider(@PathVariable final @NotNull Integer id) { return rs.deleteRider(id); }
}
