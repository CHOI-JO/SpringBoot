package org.mbc.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO <E>{ // <E> 엔티티용 변수명(변할 수 있는 값)
    // 페이징 처리 응답용 객체
    // dto의 목록, 시작페이지/끝페이지 여부 등

    private int page, size, total; // 현재 페이지, 페이지당 게시물 수, 총 게시물수

    private int start, end; // 시작페이지번호, 끝 페이지 번호

    private boolean prev, next; // 이전 페이지 존재 여부, 다음페이지 존재여부

    private List<E> dtoList; //게시물 목록

    // 생성자
    @Builder(builderMethodName = "withAll")
    private PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        // PageRequestDTO return link
        // page=3&type=w&keyword=kkw
        // List<board> dtoList / List<Member> dtoList// List<Item> dtoList
        // int total -> 총 게시물 수

        if(total <=0) { //게시물이 없으면
            return;
        }
        // 게시물이 있으면
        this.page = pageRequestDTO.getPage(); // 요청에 대한 페이지 번호
        this.size = pageRequestDTO.getSize();// 요쳉에 대한 사이즈 (게시물 수 )
        this.total = total; // 파라미터로 넘어온 값
        this.dtoList=dtoList;// 파라미터로 넘어온 값

        this.end=(int) (Math.ceil(this.page/10.0)) * 10; // 화면에서의 마지막 번호
        // ceil: 정수로 소수점을 올림하겠다.
        // 10 =                 1<-1.0 = 1 / 10.0
        // 20 =                (2<-(2.1 =21/10.0)) *10

        this.start=this.end-9; //화면에서의 시작번호
        // 11 = 20 - 9;
        int last=(int) (Math.ceil((total/(double)size))); //데이터의 개수를 계산한 마지막 페이지 번호
        // 만약 88개의 게시물이면 9개의 페이지 번호가 나와야 함

        this.end=end>last ? last: end; // 3항 연산자 -> 최종 활용되는 페이지 번호
        //          조건      참    거짓

        this.prev=this.start >1; // 이전 페이지 유무

        this.next=total>this.end * this.size; // 다음 페이지 유무
    }
}
