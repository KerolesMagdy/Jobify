package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.Entity.Address;

public interface AddressServiceOp {
    Address save(Address address);
    Address update(Address address);
}
