package com.lcwd.user.service.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hotel {
    private String id;
    private String name;
    private String location;
    private String about;
}
