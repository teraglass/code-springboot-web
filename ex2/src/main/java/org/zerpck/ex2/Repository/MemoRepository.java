package org.zerpck.ex2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerpck.ex2.entity.Memo;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    void deleteMemoByMnoLessThan(Long num);

}
