package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class BuyerController {


    @Autowired
    private BuyerRepository buyerRepository;

    @GetMapping("/buyers")
    public List<Buyer> getAllUsers() {
        return buyerRepository.findAll();
    }

    @GetMapping("/buyers/{id}")
    public Optional<Buyer> getUsersById(@PathVariable(value = "id") Long buyerId) {
        Optional<Buyer> optionalBuyer =
                buyerRepository
                        .findById(buyerId) ;
        return optionalBuyer;
    }



    /**
     * Create Buyer buyer.
     * @param buyer
     *
     */
    @PostMapping("/buyers")
    public String createUser(@Valid @RequestBody Buyer buyer) {
        List<Buyer> buyerFound = buyerRepository.findAll();

        Buyer buyerFound1=buyerFound.stream()
                                    .filter(name -> buyer.getBuyerName().equals(name))
                                    .findAny()
                                    .orElse(null);

        if(buyerFound1 == null){

             buyerRepository.save(buyer);
             return "buyer saved";

        }else{
            return "buyer exists already";
        }

    }


    /**
     * Update buyer response entity.
     *  @param buyerId the buyer id
     *  @param buyerDetails the buyer details
     *  @return the response entity
     */
    @PutMapping("/buyers/{id}")
    public ResponseEntity<Buyer> updateUser (
            @PathVariable(value = "id") Long buyerId, @Valid @RequestBody Buyer buyerDetails)  {
         Buyer buyer =
                buyerRepository
                        .findById(buyerId)
                         .orElseThrow(() -> new NoSuchElementException("No buyer found with id " + buyerId));

        buyer.setId(buyerDetails.getId()); ;
        buyer.setBuyerName(buyerDetails.getBuyerName());
        buyer.setTransactions(buyerDetails.getTransactions());
        buyer.setValue(buyerDetails.getValue());
        final Buyer updatedBuyer = buyerRepository.save (buyer);
        return ResponseEntity.ok(updatedBuyer);
    }


    @DeleteMapping("/buyers/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long buyerId) throws Exception {
        Buyer buyer =
                buyerRepository
                        .findById(buyerId)
                        .orElseThrow(() -> new NoSuchElementException("buyer not found with id :: " + buyerId));
        buyerRepository.delete(buyer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
