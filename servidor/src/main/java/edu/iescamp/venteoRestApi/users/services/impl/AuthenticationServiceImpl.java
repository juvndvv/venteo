package edu.iescamp.venteoRestApi.users.services.impl;

import edu.iescamp.venteoRestApi.users.models.dto.LoginFormDTO;
import edu.iescamp.venteoRestApi.users.models.dto.UserDTO;
import edu.iescamp.venteoRestApi.users.repositories.dao.UserRepository;
import edu.iescamp.venteoRestApi.users.repositories.dao.WalletRepository;
import edu.iescamp.venteoRestApi.users.repositories.entities.User;
import edu.iescamp.venteoRestApi.users.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public UserDTO authenticate(LoginFormDTO loginFormDTO) {
        User user = userRepository.findByUsernameAndPassword(loginFormDTO.getUsername(), loginFormDTO.getPassword());
        UserDTO userDTO = UserDTO.builder()
                .id(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .money(walletRepository.findWalletByUserId(user.getUserId()).getAmount())
                .build();

        return userDTO;
    }
}
