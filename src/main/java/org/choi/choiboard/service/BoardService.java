package org.choi.choiboard.service;

import org.choi.choiboard.dto.BoardDTO;
import org.choi.choiboard.dto.PageRequestDTO;
import org.choi.choiboard.dto.PageResponseDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
