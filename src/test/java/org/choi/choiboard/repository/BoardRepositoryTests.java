package org.choi.choiboard.repository;

import lombok.extern.log4j.Log4j2;
import org.choi.choiboard.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest // 테스트 클래스를 스프링 부트에서 동작하게 해줌
@Log4j2 // 로그
public class BoardRepositoryTests {
    @Autowired //객체(Bean)를 자동으로 찾아서 주입
    private BoardRepository boardRepository;

    @Test // DB에 데이터 삽입
    public void testInsert() {

        IntStream.rangeClosed(1,500).forEach(i -> { // 1부터 500까지의 정수 생성해서 i에 넣음
            Board board=Board.builder()
                    .title("제목: "+i)
                    .content("내용: "+i)
                    .writer("작성자: "+(i%10)) // 작성자는 10명, 10명이 반복함
                    .build();

            Board result = boardRepository.save(board) ; // save는 DB에 데이터가 없으면 insert, 있으면 update 자동 실행
            log.info("게시물 번호: " + result.getBno() + "게시물 제목: " + result.getTitle());
        });
    }

    @Test
    public void testSelect() {
        Long bno = 100L; // 100번 게시물

        Optional<Board> result = boardRepository.findById(bno);
        // findById(bno): bno번호를 DB에서 찾아서 데이터 가져옴
        // optional: 만약 찾는 번호가 없을 수도, 있을 수도 있음
        // findById=select * from board where bno=bno

        Board board=result.orElseThrow(); //Board 객체가 존재하면 그대로 반환하고 존재하지 않으면 예외 발생

        log.info(bno + "가 데이터 베이스에 존재합니다.");
        log.info(board);
    }

    @Test
    public void testUpdate() {
        Long bno = 100L;// 100번 게시물

        Optional<Board> result = boardRepository.findById(bno);
        Board board= result.orElseThrow();
        board.change("100번 게시물의 제목을 수정 및 업데이트합니다.", "100번 게시물의 내용을 수정 및 업데이트합니다.");
        boardRepository.save(board); // save는 DB에 데이터가 없으면 insert, 있으면 update 자동 실행
    }

    @Test
    public void testDelete() {
        Long bno = 1L;
        boardRepository.deleteById(bno);
    }

    @Test
    public void testPaging() { // 전체 리스트 출력
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        // 0페이지부터 시작, 페이지 당 데이터 10개, bno를 기준으로 내림차순
        Page<Board> result = boardRepository.findAll(pageable);
        // 1페이지에 Board의 객체를 result로 넣음

        log.info("전체 게시물 수 : " + result.getTotalElements());
        log.info("총 페이지 수 : " + result.getTotalPages());
        log.info("현재 페이지 번호 : " + result.getNumber());
        log.info("페이지당 데이터 개수 : " + result.getSize() );
        log.info("다음페이지 여부 : " + result.hasNext());
        log.info("시작페이지 여부 : " + result.isFirst());

        List<Board> todoList = result.getContent(); //페이징 처리 결과를 "todoList"에 넣음
        todoList.forEach(board -> log.info(board));// todoList에 있는 각 board 객체를 하나씩 꺼내서 log.info(board)를 통해 로그에 출력

    }
    @Test
    public void testSearch1(){
        Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.search1(pageable);
        result.getContent().forEach(board -> log.info(board));
    }

    @Test
    public void testSearchAll(){
        String[] types = {"t", "w"};
        String keyword = "10";

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        log.info("전체 게시물 수 : " + result.getTotalElements());
        log.info("총 페이지 수 : " + result.getTotalPages());
        log.info("현재 페이지 번호 : " + result.getNumber());
        log.info("페이지당 데이터 개수 : " + result.getSize() );
        log.info("다음페이지 여부 : " + result.hasNext());
        log.info("시작페이지 여부 : " + result.isFirst());

        result.getContent().forEach(board -> log.info(board));

    }

}
