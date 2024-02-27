package edu.iescamp.venteoRestApi.users.services;

import edu.iescamp.venteoRestApi.auctions.repositories.entities.Auction;
import edu.iescamp.venteoRestApi.users.models.dto.RegistrationFormDTO;
import edu.iescamp.venteoRestApi.users.models.dto.UserDTO;
import edu.iescamp.venteoRestApi.users.models.dto.UserPatchDTO;

import java.util.List;

public interface UsersService {
    List<UserDTO> findAll();
    List<UserDTO> findByRol(Long roleId);
    UserDTO create(RegistrationFormDTO registrationFormDTO);
    UserDTO findById(Long id);
    UserDTO update(UserDTO userDTO);
    UserDTO updatePartial(UserPatchDTO userPatchDTO);
    boolean delete(Long id);
    List<Auction> findAuctionsByUserId(Long id);
}
