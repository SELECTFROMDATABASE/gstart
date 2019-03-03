package com.gstart.demo.repository;

import com.gstart.demo.repository.pojo.Demo;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Author     ：yangguangye.
 * @ Date       ：Created in 15:25 2019/3/1
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface DemoRepository extends JpaRepository<Demo,Integer> {
    @Modifying
    @Query(value = "insert into demo(maindId,name,password,main_id) values('123','222','333','33333')",nativeQuery = true)
    void ttte();
}
