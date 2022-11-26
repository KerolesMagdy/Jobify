package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalIdNotFoundException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.DTO.SocialLinkDto;
import com.keroles.jobify.Model.Entity.Degree;
import com.keroles.jobify.Model.Entity.SocialLink;
import com.keroles.jobify.Model.Mapper.SocialLinkMapper;
import com.keroles.jobify.Repository.SocialLinkRepo;
import com.keroles.jobify.Service.Operation.SocialLinkOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialLinkService implements SocialLinkOp {

    @Autowired
    private SocialLinkRepo socialLinkRepo;
    @Autowired
    private SocialLinkMapper socialLinkMapper;
    @Autowired
    private Environment environment;
    @Override
    public SocialLinkDto save(SocialLink socialLink) {
        return socialLinkMapper.mapToDto(socialLinkRepo.save(socialLink));
    }

    @Override
    public SocialLinkDto update(SocialLink socialLink) {
        if (socialLink.getId()==null)
            throw new GlobalIdNotFoundException(environment.getProperty("validate.message.social_link.not_found"));
        Optional<SocialLink> retrievedSocialLink=socialLinkRepo.findById(socialLink.getId());
        if (!retrievedSocialLink.isPresent())
            throw new GlobalIdNotFoundException(environment.getProperty("validate.message.social_link.not_found"));
        return socialLinkMapper.mapToDto(socialLinkRepo.save(socialLink));
    }

    @Override
    public String deleteById(long id) {
        Optional<SocialLink> retrievedSocialLink=socialLinkRepo.findById(id);
        if (!retrievedSocialLink.isPresent())
            throw new GlobalIdNotFoundException(environment.getProperty("validate.message.social_link.not_found"));
        socialLinkRepo.delete(retrievedSocialLink.get());
        return "social link deleted successfully";
    }
}
