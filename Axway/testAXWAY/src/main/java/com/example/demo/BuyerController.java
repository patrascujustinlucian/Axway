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
    private BuyerIndividualRepository buyerIndividualRepository;

    @Autowired
    private BuyerCorporateRepository buyerCorporateRepository;

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
     *  @param buyerId the buyer id
     *  @param buyerDetails the buyer details
     *  @return the response entity
     */
    @PutMapping("/buyersCorporate/{id}")
    public ResponseEntity<Buyer> updateUser (
            @PathVariable(value = "id") Long buyerId, @Valid @RequestBody CorporateBuyer buyerDetails)  {
         CorporateBuyer buyer =
                buyerCorporateRepository
                        .findById(buyerId)
                         .orElseThrow(() -> new NoSuchElementException("No buyer found with id " + buyerId));

        buyer.setId(buyerDetails.getId()); ;
        buyer.setBuyerName(buyerDetails.getBuyerName());
        buyer.setTransactions(buyerDetails.getTransactions());
        buyer.setValue(buyerDetails.getValue());
        buyer.setAddress(buyerDetails.getAddress());
        buyer.setCompanyIdentification(buyerDetails.getCompanyIdentification());
        final Buyer updatedBuyer = buyerCorporateRepository.save (buyer);
        return ResponseEntity.ok(updatedBuyer);
    }

    /**
     *  @param buyerId the buyer id
     *  @param buyerDetails the buyer details
     *  @return the response entity
     */
    @PutMapping("/buyersIndividual/{id}")
    public ResponseEntity<Buyer> updateUser (
            @PathVariable(value = "id") Long buyerId, @Valid @RequestBody IndividualBuyer buyerDetails)  {
        IndividualBuyer buyer =
                buyerIndividualRepository
                        .findById(buyerId)
                        .orElseThrow(() -> new NoSuchElementException("No buyer found with id " + buyerId));

        buyer.setId(buyerDetails.getId()); ;
        buyer.setBuyerName(buyerDetails.getBuyerName());
        buyer.setTransactions(buyerDetails.getTransactions());
        buyer.setValue(buyerDetails.getValue());
        buyer.setBuyerPersonalIdentification(buyerDetails.getBuyerPersonalIdentification());
        buyer.setDateRegistered(buyerDetails.getDateRegistered());
        final Buyer updatedBuyer = buyerIndividualRepository.save (buyer);
        return ResponseEntity.ok(updatedBuyer);
    }


    @DeleteMapping("/buyersCorporate/{id}")
    public Map<String, Boolean> deleteUserCorporate(@PathVariable(value = "id") Long buyerId) throws Exception {
        CorporateBuyer buyer =
                buyerCorporateRepository
                        .findById(buyerId)
                        .orElseThrow(() -> new NoSuchElementException("buyer not found with id :: " + buyerId));
        buyerCorporateRepository.delete(buyer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/buyersIndividual/{id}")
    public Map<String, Boolean> deleteUserIndividual(@PathVariable(value = "id") Long buyerId) throws Exception {
        IndividualBuyer buyer =
                buyerIndividualRepository
                        .findById(buyerId)
                        .orElseThrow(() -> new NoSuchElementException("buyer not found with id :: " + buyerId));
        buyerIndividualRepository.delete(buyer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
