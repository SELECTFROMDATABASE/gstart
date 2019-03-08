package com.gstart.upms.repository;

import com.gstart.upms.rpc.api.pojo.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position,Integer> {
}
