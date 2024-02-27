package edu.iescamp.venteoRestApi.users.web.controllers;

import edu.iescamp.venteoRestApi.auctions.repositories.entities.Auction;
import edu.iescamp.venteoRestApi.auctions.services.AuctionService;
import edu.iescamp.venteoRestApi.users.models.dto.RegistrationFormDTO;
import edu.iescamp.venteoRestApi.users.models.dto.UserDTO;
import edu.iescamp.venteoRestApi.users.models.dto.UserPatchDTO;
import edu.iescamp.venteoRestApi.users.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuctionService auctionService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> findAll(@RequestParam(required = false) Long roleId) {
        if (roleId == null) {
            return ResponseEntity.status(HttpStatus.OK).body(usersService.findAll());
        }

        List<UserDTO> users = usersService.findByRol(roleId);

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> create(@Valid @RequestBody RegistrationFormDTO registrationFormDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.create(registrationFormDTO));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.findById(id));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(usersService.update(userDTO));
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<UserDTO> updatePartial(@PathVariable Long id, @Valid @RequestBody UserPatchDTO userPatchDTO) {
        userPatchDTO.setUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(usersService.updatePartial(userPatchDTO));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usersService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/user/{id}/auctions")
    public ResponseEntity<List<Auction>> findAuctionsByUserId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(auctionService.findAuctionsByUserId(id));
    }
}
