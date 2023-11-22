package com.travelbee.app.repository;

import com.travelbee.app.enities.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,String> {

    @Query("select o from Voucher o where o.id like :id or o.title like :title")
    List<Voucher> findByIdOrTitle(@Param("id") String id,@Param("title") String title);

    @Query("select o from Voucher o where o.isactive = :active")
    List<Voucher> findByActive(@Param("active") Boolean active);
}
