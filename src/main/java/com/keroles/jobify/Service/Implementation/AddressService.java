package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalIdNotFoundException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.Entity.Address;
import com.keroles.jobify.Repository.AddressRepo;
import com.keroles.jobify.Service.Operation.AddressServiceOp;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService implements AddressServiceOp {

    private final AddressRepo addressRepo;
    private final Environment environment;

    public AddressService(AddressRepo addressRepo, Environment environment) {
        this.addressRepo = addressRepo;
        this.environment = environment;
    }

    @Override
    public Address update(Address address) {
        if (address.getId()==null)
            throw new GlobalIdNotFoundException(environment.getProperty("validate.message.user.address.not_found"));
        Optional<Address> retrievedAddress=addressRepo.findById(address.getId());
        if (!retrievedAddress.isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.address.not_found"));
        return addressRepo.save(address);
    }

    @Override
    public Address saveOrUpdate(Address address) {
        return null;
    }

    @Override
    public Address save(Address address) {
        return addressRepo.save(address);
    }
}
