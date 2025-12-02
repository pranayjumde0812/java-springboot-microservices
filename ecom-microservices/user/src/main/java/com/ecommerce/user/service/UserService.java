package com.ecommerce.user.service;

import com.ecommerce.user.dto.AddressDTO;
import com.ecommerce.user.dto.request.UserRequest;
import com.ecommerce.user.dto.response.UserResponse;
import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void createUser(UserRequest userRequest) {
        User user = new User();
        updateUserFromRequest(user, userRequest);
        userRepository.save(user);
    }

    public Optional<UserResponse> getUserById(Long userId) {
//        return userList.stream()
//                .filter(user -> user.getId().equals(userId))
//                .findFirst();
        return userRepository.findById(userId)
                .map(this::mapToUserResponse);
    }

    public boolean updateUserById(UserRequest updateUserRequest, Long userId) {
//        return userList.stream()
//                .filter(user -> user.getId().equals(userId))
//                .findFirst()
//                .map(existingUser -> {
//                    existingUser.setFirstName(userBody.getFirstName());
//                    existingUser.setLastName(userBody.getLastName());
//                    return true;
//                }).orElse(false);

        return userRepository.findById(userId)
                .map(existingUser -> {
                    updateUserFromRequest(existingUser, updateUserRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());

        if (userRequest.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());
            user.setAddress(address);
        }
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId().toString());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setPhoneNumber(user.getPhoneNumber());

        if (user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
//            addressDTO.setId(user.getAddress().getId().toString());
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            response.setAddress(addressDTO);
        }

        return response;
    }
}
