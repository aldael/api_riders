package API.service;


import API.model.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import repository.RiderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class RiderService {

    @Autowired
    public RiderService(RiderRepository rr) {
        this.rr = rr;
    }

    private final RiderRepository rr;

    public ResponseEntity addRider(Rider r){
        try{
            rr.save(r);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public Rider getRider(Integer id){
        return rr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "rider no encontrado"));

    }

    public List<Rider> getAllRiders() { return rr.findAll();}

    public ResponseEntity updateRider(Integer id, Rider raidaa) {
        Rider r = rr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "rider no encontrado"));
        r.setRider(raidaa.getRider());
        r.setId(raidaa.getId());
        r.setIdentidad(raidaa.getIdentidad());
        rr.save(r);
        return ResponseEntity.status(OK).build();
    }

    public ResponseEntity deleteRider(Integer id) {
        try{
            rr.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
