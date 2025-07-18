package org.choi.choiboard.repository;

import org.choi.choiboard.domain.Board;
import org.choi.choiboard.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// Spring Data JPA를 이용할 때 JpaRepository 인터페이스 사용 -> MyBatis 이용하는 것과 유사
// JpaRepository는 jpa에서 미리 만들어 놓은 인터페이스
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    //JpaRepository 인터페이스 상속받음
    Page<Board> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable);
    // 제목에 keyword가 포함된 게시글(Board)들을 Bno 내림차순으로 정렬해서, 페이지 단위로 가져오는 메서드

    @Query("select b from Board b where b.title like concat('%',:keyword,'%')")
    Page<Board> findKeyword(String keyword, Pageable pageable);
    // keyword와 pageable (페이지 정보)를 받아서 조건에 맞는 Board 엔티티의 반환

    @Query(value = "select now()", nativeQuery = true) // 진짜 쿼리문으로 동작 nativeQuery = true
    String getTime();
    //데이터베이스의 현재 시간을 SQL 쿼리(select now())로 직접 조회해서 문자열로 반환
}
