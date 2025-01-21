package com.b2b.hotel.in.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
public class LoginDetails {
    @Indexed(unique = true)
    private String userName;
    private String password;
    private String agentId;
    private String status;
}
