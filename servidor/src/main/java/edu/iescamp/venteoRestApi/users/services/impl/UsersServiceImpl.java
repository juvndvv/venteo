package edu.iescamp.venteoRestApi.users.services.impl;

import edu.iescamp.venteoRestApi.auctions.repositories.entities.Auction;
import edu.iescamp.venteoRestApi.auctions.services.AuctionService;
import edu.iescamp.venteoRestApi.users.exceptions.UserNotFoundException;
import edu.iescamp.venteoRestApi.users.models.dto.RegistrationFormDTO;
import edu.iescamp.venteoRestApi.users.models.dto.UserDTO;
import edu.iescamp.venteoRestApi.users.models.dto.UserPatchDTO;
import edu.iescamp.venteoRestApi.users.models.mappers.UserDTOMapper;
import edu.iescamp.venteoRestApi.users.repositories.dao.WalletRepository;
import edu.iescamp.venteoRestApi.users.repositories.entities.User;
import edu.iescamp.venteoRestApi.users.repositories.entities.Wallet;
import edu.iescamp.venteoRestApi.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import edu.iescamp.venteoRestApi.users.repositories.dao.UserRepository;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private UserDTOMapper userDTOMapper;

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(
                user -> UserDTO.builder()
                        .id(user.getUserId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .userName(user.getUserName())
                        .email(user.getEmail())
                        .birthDate(user.getBornDate())
                        .imageUrl(user.getImageUrl())
                        .roleId(user.getRoleId())
                        .build()
        ).toList();
    }

    @Override
    public List<UserDTO> findByRol(Long roleId) {
        return userRepository.findByRoleId(roleId).stream().map(
                user -> UserDTO.builder()
                        .id(user.getUserId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .userName(user.getUserName())
                        .email(user.getEmail())
                        .birthDate(user.getBornDate())
                        .imageUrl(user.getImageUrl())
                        .roleId(user.getRoleId())
                        .build()
        ).toList();
    }

    @Override
    public UserDTO create(RegistrationFormDTO registrationFormDTO) {
        User user = new User();
        user.setFirstName(registrationFormDTO.getFirstName());
        user.setLastName(registrationFormDTO.getLastName());
        user.setUserName(registrationFormDTO.getUserName());
        user.setEmail(registrationFormDTO.getEmail());
        user.setPassword(registrationFormDTO.getPassword());
        user.setImageUrl(registrationFormDTO.getImageUrl());

        System.out.println(registrationFormDTO.getImageUrl());
        System.out.println(user.getImageUrl());
        user = userRepository.save(user);

        return UserDTO.builder()
                .id(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .birthDate(user.getBornDate())
                .imageUrl(user.getImageUrl())
                .build();
    }

    @Override
    public UserDTO findById(Long id) {
        return userRepository.findById(id).map(
                user -> UserDTO.builder()
                        .id(user.getUserId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .userName(user.getUserName())
                        .email(user.getEmail())
                        .birthDate(user.getBornDate())
                        .imageUrl(user.getImageUrl())
                        .money(walletRepository.findWalletByUserId(user.getUserId()).getAmount())
                        .build()
        ).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        if (findById(userDTO.getId()) == null) {
            throw new UserNotFoundException(userDTO.getId());
        }

        User user = new User();
        user.setUserId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setImageUrl(userDTO.getImageUrl());

        System.out.println(userDTO.getImageUrl());
        System.out.println(user.getImageUrl());
        user = userRepository.save(user);

        return findById(user.getUserId());
    }

    @Override
    public UserDTO updatePartial(UserPatchDTO userPatchDTO) {
        System.out.printf("id: %d%n", userPatchDTO.getUserId());
        User user = userRepository.findById(userPatchDTO.getUserId()).orElseThrow(() -> new UserNotFoundException(userPatchDTO.getUserId()));

        if (userPatchDTO.getFirstName() != null) {
            user.setFirstName(userPatchDTO.getFirstName());
        }

        if (userPatchDTO.getLastName() != null) {
            user.setLastName(userPatchDTO.getLastName());
        }

        if (userPatchDTO.getUserName() != null) {
            user.setUserName(userPatchDTO.getUserName());
        }

        if (userPatchDTO.getEmail() != null) {
            user.setEmail(userPatchDTO.getEmail());
        }

        if (userPatchDTO.getPassword() != null) {
            user.setPassword(userPatchDTO.getPassword());
        }

        if (userPatchDTO.getImageUrl() != null) {
            user.setImageUrl(userPatchDTO.getImageUrl());
        }

        if (userPatchDTO.getRoleId() != null) {
            user.setRoleId(userPatchDTO.getRoleId());
        }

        if (userPatchDTO.getBornDate() != null) {
            user.setBornDate(userPatchDTO.getBornDate());
        }

        if(userPatchDTO.getMoney() != 0){
            Wallet wallet = walletRepository.findWalletByUserId(user.getUserId());
            wallet.setAmount(userPatchDTO.getMoney());
            walletRepository.save(wallet);
        }

        user = userRepository.save(user);

        return UserDTO.builder()
                .id(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .birthDate(user.getBornDate())
                .imageUrl(user.getImageUrl())
                .money(walletRepository.findWalletByUserId(user.getUserId()).getAmount())
                .build();
    }

    @Override
    public boolean delete(Long id) {
        if (findById(id) == null) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Auction> findAuctionsByUserId(Long id) {
        return null;
    }
}
