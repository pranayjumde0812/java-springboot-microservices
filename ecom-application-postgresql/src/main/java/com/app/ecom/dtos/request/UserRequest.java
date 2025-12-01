package com.app.ecom.dtos.request;

import com.app.ecom.dtos.AddressDTO;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AddressDTO address;
}
