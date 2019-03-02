package com.gstart.demo.repository;

import com.gstart.demo.repository.pojo.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ Author     ：yangguangye.
 * @ Date       ：Created in 15:25 2019/3/1
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Repository
public interface DemoRepository extends JpaRepository<Demo,Integer> {
}
