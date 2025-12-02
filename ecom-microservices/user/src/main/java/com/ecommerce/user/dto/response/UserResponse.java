package com.ecommerce.user.dto.response;


import com.ecommerce.user.dto.AddressDTO;
import com.ecommerce.user.enums.UserRole;
import lombok.Data;

@Data
public class UserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private UserRole role;
    private AddressDTO address;
}
