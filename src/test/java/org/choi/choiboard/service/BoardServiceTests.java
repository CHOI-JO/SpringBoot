package org.choi.choiboard.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.choi.choiboard.dto.BoardDTO;
import org.choi.choiboard.dto.PageRequestDTO;
import org.choi.choiboard.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("boardService 제목")
                .content("boardService 내용")
                .writer("boardService 작성자")
                .build();

        Long bno = boardService.register(boardDTO);
        log.info("bno:" + bno);
    }

    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("수정된 제목...101")
                .content("수정된 내용 101")
                .build();
        boardService.modify(boardDTO);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        log.info(responseDTO);
    }
}
