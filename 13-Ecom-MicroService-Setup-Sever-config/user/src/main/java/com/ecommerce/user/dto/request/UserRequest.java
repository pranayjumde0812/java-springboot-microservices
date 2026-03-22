package com.ecommerce.user.dto.request;

import com.ecommerce.user.dto.AddressDTO;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AddressDTO address;
}
