package com.example.phuonglth_sprint_2.repository.customer;

import com.example.phuonglth_sprint_2.dto.customer.CustomerView;
import com.example.phuonglth_sprint_2.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select id_customer as idCustomer,date_of_birth as dateOfBirth, email, gender,id_card as idCard, `name`," +
            "phone,account_id_account as accountId, order_id_order as orderId, create_date as createDate, modify_date as modifyDate, address from customer  where flag_delete = false and `name` like concat('%', :name, '%') and email like concat('%', :email, '%')",nativeQuery = true)
    Page<CustomerView> getAllCustomer(@Param("name") String name, @Param("email") String email, Pageable pageable);

    @Query(value = "update customer set flag_delete = true where id_customer = :id",nativeQuery = true)
    void removeFlag(@Param("id") Long id);
}
