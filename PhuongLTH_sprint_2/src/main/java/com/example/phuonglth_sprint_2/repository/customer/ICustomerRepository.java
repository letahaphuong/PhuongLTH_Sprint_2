package com.example.phuonglth_sprint_2.repository.customer;

import com.example.phuonglth_sprint_2.dto.customer.CustomerView;
import com.example.phuonglth_sprint_2.dto.customer.GetIdCustomerView;
import com.example.phuonglth_sprint_2.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select id_customer        as idCustomer,\n" +
            "       date_of_birth      as dateOfBirth,\n" +
            "       email,\n" +
            "       gender,\n" +
            "       id_card            as idCard,\n" +
            "       `name`,\n" +
            "       phone,\n" +
            "       account_id_account as accountId,\n" +
            "       order_id_order     as orderId,\n" +
            "       create_date        as createDate,\n" +
            "       modify_date        as modifyDate,\n" +
            "       address\n" +
            "from customer\n" +
            "where flag_delete = false\n" +
            "  and `name` like concat('%', :name, '%')\n" +
            "  and email like concat('%', :email, '%')",
            countQuery = "select id_customer        as idCustomer,\n" +
                    "       date_of_birth      as dateOfBirth,\n" +
                    "       email,\n" +
                    "       gender,\n" +
                    "       id_card            as idCard,\n" +
                    "       `name`,\n" +
                    "       phone,\n" +
                    "       account_id_account as accountId,\n" +
                    "       order_id_order     as orderId,\n" +
                    "       create_date        as createDate,\n" +
                    "       modify_date        as modifyDate,\n" +
                    "       address\n" +
                    "from customer\n" +
                    "where flag_delete = false\n" +
                    "  and `name` like concat('%', :name, '%')\n" +
                    "  and email like concat('%', :email, '%')", nativeQuery = true)
    Page<CustomerView> getAllCustomer(@Param("name") String name, @Param("email") String email, Pageable pageable);

    @Query(value = "update customer set flag_delete = true where id_customer = :id", nativeQuery = true)
    void removeFlag(@Param("id") Long id);

    Optional<Customer> findByEmail(String email);

    @Query(value = "select c.id_customer as idCustomer\n" +
            "from account as a\n" +
            "         join customer c on a.id_account = c.account_id_account\n" +
            "where id_account = :id",
            countQuery = "select c.id_customer as idCustomer\n" +
                    "from account as a\n" +
                    "         join customer c on a.id_account = c.account_id_account\n" +
                    "where id_account = :id", nativeQuery = true)
    Optional<GetIdCustomerView> getIdCustomerByIdAccount(@Param("id") Long idAccount);
}
