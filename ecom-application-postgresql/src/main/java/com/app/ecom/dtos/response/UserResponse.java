package com.app.ecom.dtos.response;

import com.app.ecom.dtos.AddressDTO;
import com.app.ecom.enums.UserRole;
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
