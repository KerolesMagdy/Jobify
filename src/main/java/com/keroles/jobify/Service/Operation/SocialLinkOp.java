package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.DTO.SocialLinkDto;
import com.keroles.jobify.Model.Entity.SocialLink;

public interface SocialLinkOp {
    SocialLinkDto save(SocialLink socialLink);
    SocialLinkDto update(SocialLink socialLink);
    String deleteById(long id);
}
