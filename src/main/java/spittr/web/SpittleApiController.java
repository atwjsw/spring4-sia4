package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spittr.Spittle;
import spittr.data.SpittleRepository;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/spittles")
public class SpittleApiController {

  private static final String MAX_LONG_AS_STRING = "9223372036854775807";

  private SpittleRepository spittleRepository;

  @Autowired
  public SpittleApiController(SpittleRepository spittleRepository) {
    this.spittleRepository = spittleRepository;
  }

//  @RequestMapping(method=RequestMethod.GET)
  @RequestMapping(method=RequestMethod.GET, produces="application/json")
  public @ResponseBody List<Spittle> spittles(
      @RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
      @RequestParam(value="count", defaultValue="20") int count) {
    return spittleRepository.findSpittles(max, count);
  }

//  @RequestMapping(method=RequestMethod.GET)
//  public String spittles(Model model) {
//    model.addAttribute(
//            spittleRepository.findSpittles(
//                    Long.MAX_VALUE, 20));
//    return "spittles";
//  }

//  @RequestMapping(method=RequestMethod.GET)
//  public List<Spittle> spittles(@RequestParam("max") long max, @RequestParam("count") int count) {
//    return spittleRepository.findSpittles(max, count);
//  }

//  @RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
//  public String spittle(
//      @PathVariable("spittleId") long spittleId,
//      Model model) {
//    model.addAttribute(spittleRepository.findOne(spittleId));
//    return "spittle";
//  }

//  @RequestMapping(value="/{id}", method=RequestMethod.GET)
//  public @ResponseBody Spittle spittleById(@PathVariable long id) {
//    if (id == 3) {
//      return null;
//    }
//    return spittleRepository.findOne(id);
//  }

//  @RequestMapping(value="/{id}", method=RequestMethod.GET)
//  public ResponseEntity<Spittle> spittleById(@PathVariable long id) {
//    Spittle spittle = null;
//    if (id != 3) {
//      spittle = spittleRepository.findOne(id);
//    }
//    HttpStatus status = spittle != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
//    return new ResponseEntity<Spittle>(spittle, status);
//  }

  @RequestMapping(value="/{id}", method=RequestMethod.GET)
  public @ResponseBody Spittle spittleById(@PathVariable long id) {
    Spittle spittle = null;
    if (id != 3) {
      spittle = spittleRepository.findOne(id);
    }
    if (spittle == null) {throw new SpittleNotFoundException(id);}
    return spittle;
  }

//  @ExceptionHandler(SpittleNotFoundException.class)
//  public ResponseEntity<Error> spittleNotFound(SpittleNotFoundException e) {
//    long spittleId = e.getSpittleId();
//    Error error = new Error(4, "Spittle [" + spittleId + "] not found");
//    return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
//  }

  @ExceptionHandler(SpittleNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public @ResponseBody Error spittleNotFound(SpittleNotFoundException e) {
    long spittleId = e.getSpittleId();
    Error error = new Error(4, "Spittle [" + spittleId + "] not found");
    return error;
  }


//  @RequestMapping(method=RequestMethod.POST)
//  public String saveSpittle(SpittleForm form, Model model) throws Exception {
//    spittleRepository.save(new Spittle(null, form.getMessage(), new Date(),
//        form.getLongitude(), form.getLatitude()));
//    return "redirect:/spittles";
//  }

//  @RequestMapping(method=RequestMethod.POST, consumes = "application/json")
//  public String saveSpittle(@RequestBody SpittleForm form, Model model) throws Exception {
//    spittleRepository.save(new Spittle(null, form.getMessage(), new Date(),
//            form.getLongitude(), form.getLatitude()));
//    return "redirect:/spittles";
//  }

//  @RequestMapping(method=RequestMethod.POST, consumes = "application/json")
//  @ResponseStatus(HttpStatus.CREATED)
//  public @ResponseBody Spittle saveSpittle(@RequestBody SpittleForm form, Model model) throws Exception {
//    Spittle spittle = new Spittle(null, form.getMessage(), new Date(),
//            form.getLongitude(), form.getLatitude());
//    spittleRepository.save(spittle);
//    return spittle;
//  }

  @RequestMapping(method=RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<Spittle> saveSpittle(@RequestBody SpittleForm form, UriComponentsBuilder ucb) throws Exception {
    Spittle spittle = new Spittle(null, form.getMessage(), new Date(),
            form.getLongitude(), form.getLatitude());
    spittleRepository.save(spittle);
    URI locationURL = ucb.path("/spitters")
            .path("/321")
            .build().toUri();
    HttpHeaders headers = new HttpHeaders();
//    headers.setLocation(new URI("http://localhost:8080/spittles/" + "123"));
    headers.setLocation(locationURL);
    ResponseEntity<Spittle> responseEntity =new  ResponseEntity<Spittle>(spittle, headers, HttpStatus.CREATED);
    return responseEntity;
  }


}
