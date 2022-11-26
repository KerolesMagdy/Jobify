package com.keroles.jobify.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialLinkDto {
    private Long id;
    private String name;
    private String url;
    private String icon;

}
