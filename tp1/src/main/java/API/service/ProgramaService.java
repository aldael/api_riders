package API.service;

import API.model.Programa;
import API.model.Rider;
import API.model.RiderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import repository.ProgramaRepository;
import repository.RiderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class ProgramaService {

    @Autowired
    public ProgramaService(ProgramaRepository pr, RiderRepository rr) {
        this.pr = pr;
        this.rr = rr;
    }

    private final ProgramaRepository pr;
    private final RiderRepository rr;
    private final ModelMapper mm = new ModelMapper();

    public ResponseEntity addPrograma(Programa p) {
        try {
            pr.save(p);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public Programa getPrograma(Integer id) {
        return pr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "programa no encontrado"));
    }

    public List<RiderDTO> getProgramaRiders(Integer id){
        try{
            List<Integer> rIdList = pr.findProgramariders(id);
            List<RiderDTO> riders = new ArrayList<>();
            for (Integer rId : rIdList) {
                Rider r = rr.findById(rId).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST));
                riders.add(mm.map(r, RiderDTO.class));
            }
            return riders;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public List<Programa> getAllProgramas() { return pr.findAll();}

    public ResponseEntity updatePrograma(Integer id, Programa program) {
        Programa p = pr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "programa no encontrado"));
        p.setId(program.getId());
        p.setSerie(p.getSerie());
        p.setYear(program.getYear());
        pr.save(p);
        return ResponseEntity.status(OK).build();
    }

    public ResponseEntity deletePrograma(Integer id) {
        try {
            pr.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
