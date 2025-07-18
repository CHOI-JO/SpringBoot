package org.mbc.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@Builder //세터를 사용하지 않고 빌터 패턴을 사용, 아래 2개 어노테아션 필수
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class PageRequestDTO {
    // 페이징 처리에 요청용 (프론트에서 요청이오면 동작)
    // 페이징에 관련된 정보(page, size), 검색 종류(thpes -> 제목t, 내용c, 작성자 w), 키워드(검색단어)

    @Builder.Default // 빌터 패턴 시작시 초기값이 들어감
    private int page=1;

    @Builder.Default
    private int size =10; // 게시물 수, 한페이지에 보는

    private String type; // 검색 종류 t, c, w, tc, tw, twc

    private String keyword;// 풀박스에 검색 내용

    private String link; // 프론트에 페이징번호 클릭시 처리되는 문자열
    // list?page=3&type=w&keyword=kkw

    public String getLink() {

        if(link ==null) {
            StringBuilder builder = new StringBuilder(); // String +연산자로 사용하면 객체가 많이 생김
            // 이를 해결하기 위한 기법
            builder.append("page: " + this.page); //page=1
            builder.append("&size: " + this.size); //

            if (type != null && type.length() > 0) {
                // 타입이 있을 때
                builder.append("&types: " + type);
            }// 다입이 없을 때 if문종료

            if (keyword != null) {
                try {
                    builder.append("&keyword: " + URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    log.info(e.getStackTrace());
                    log.info("UTF-8 처리중 오류발생");
                } // try문 종료
            }// 키워드 if문종료
            link = builder.toString(); // 최종 결과물이 문자열로 변환되어 link에 저장됨
        }// if문종료
        return link; //  page=3&type=w&keyword=kkw 가 나옴
    }

    // 추가 메서드
    public String[] getTypes() {
        if(type == null || type.isEmpty()) {
            // 넘어온 값이 널이거나 비어있으면
            return null;
        }
        return type.split(""); // 차후에 프론트에 풀박스 확인하고 조절
        // 문자열로 넘어온 값을 분할하여 배열에 넣는다.
    }
    //
    public Pageable getPageable(String...props) {// String...props: 배열이 몇개가 들어올 지 모를때
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
        //                          페이지번호           게시물 수       정렬기법
    }
}
