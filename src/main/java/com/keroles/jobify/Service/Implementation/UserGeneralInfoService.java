package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectFoundException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.Entity.UserGeneralInfo;
import com.keroles.jobify.Repository.UserGeneralInfoRepo;
import com.keroles.jobify.Service.Operation.UserGeneralInfoServiceOp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserGeneralInfoService implements UserGeneralInfoServiceOp {
    @Autowired
    UserGeneralInfoRepo userGeneralInfoRepo;
    @Autowired
    AddressService addressService;
    @Autowired
    Environment environment;
    @Transactional
    @Override
    public UserGeneralInfo save(UserGeneralInfo userInfo) {

        Optional<UserGeneralInfo> userGeneralInfo=userGeneralInfoRepo.findByEmail(userInfo.getEmail());
        if(!userGeneralInfo.isPresent()) {
            userInfo.setAddress(addressService.save(userInfo.getAddress()));

            return userGeneralInfoRepo.save(userInfo);
        }

        throw new GlobalObjectFoundException(environment.getProperty("validate.message.user.general_info.found"));
    }
    @Transactional
    @Override
    public UserGeneralInfo update(UserGeneralInfo userInfo) {
        Optional<UserGeneralInfo> userGeneralInfo=userGeneralInfoRepo.findById(userInfo.getId());
        if(userGeneralInfo.isPresent()){
            addressService.update(userInfo.getAddress());
            return userGeneralInfoRepo.save(userInfo);
        }
        throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.general_info.not_found"));
    }

    @Override
    public UserGeneralInfo getInfoCredentialByEmail(String email) {

        Optional<UserGeneralInfo> userGeneralInfo=userGeneralInfoRepo.findByEmail(email);
        if(!userGeneralInfo.isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.general_info.not_found"));
        return userGeneralInfo.get();
    }

    @Override
    public UserGeneralInfo getByEmail(String email) {
        Optional<UserGeneralInfo> userGeneralInfo=userGeneralInfoRepo.findByEmail(email);
        if(!userGeneralInfo.isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.general_info.not_found"));
        return userGeneralInfo.get();
    }

    @Transactional
    @Override
    public int remove(String email) {
        return userGeneralInfoRepo.removeByEmail(email);
    }

}
