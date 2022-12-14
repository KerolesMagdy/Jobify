package com.keroles.jobify.Sec.Token.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompositeToken {
    private char[] accessToken;
    private char[] refreshToken;
}
