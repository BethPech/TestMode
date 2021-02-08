package ru.netology.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration {
    private String login = "vasya";
    private String password = "password";
    private String status = "active";


}
