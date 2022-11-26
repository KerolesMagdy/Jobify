package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "url must not be null")
    private String name;
    @NotNull(message = "url must not be null")
    private String url;
    @Nullable
    private String icon;

    @ManyToOne
    @JoinColumn(name = "user_online_presence_id")
    @JsonBackReference(value = "useronlinepresence-sociallink")
    private UserOnlinePresence userOnlinePresence;
}
