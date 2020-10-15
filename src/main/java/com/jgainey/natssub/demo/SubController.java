package com.jgainey.natssub.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubController {

    NatsSub natsSub = new NatsSub();

    @RequestMapping(method = RequestMethod.GET, value = "/connect", produces = "application/json")
    public ResponseEntity<String> connect(){
        natsSub.connect();
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/disconnect", produces = "application/json")
    public ResponseEntity<String> disconnect(){
        natsSub.disconnect();
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/subscribe", produces = "application/json")
    public ResponseEntity<String> sub(@RequestParam(value = "subject", defaultValue = "HelloWorld") String subject){
        natsSub.subscribe(subject);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/unsubscribe", produces = "application/json")
    public ResponseEntity<String> unsub(@RequestParam(value = "subject", defaultValue = "HelloWorld") String subject){
        natsSub.unSubscribe();
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
