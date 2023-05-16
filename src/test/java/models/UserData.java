package models;

import lombok.Data;

@Data
public class UserData {
     private Integer id, userStatus;
     private String username, firstName, lastName, email, password, phone;
}
