package com.hybrid.dtos;

import lombok.Data;

@Data
public class UserDTO {
    String username;
    String passOld;
    String passNew;
    String passConfirm;
}
