package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {

}
