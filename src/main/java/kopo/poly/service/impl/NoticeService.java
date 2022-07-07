package kopo.poly.service.impl;

import kopo.poly.dto.NoticeDTO;
import kopo.poly.persistance.mapper.INoticeMapper;
import kopo.poly.service.INoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("NoticeService")
public class NoticeService implements INoticeService {

    private final INoticeMapper noticeMapper; // 전역변수

    @Autowired
    public NoticeService(INoticeMapper noticeMapper) { this.noticeMapper = noticeMapper;}
    /*
    Class SumCal {
	int result
	public SumCal(int result) {
		this.result = result;
	}
	같은 구조로 전역변수의 result값에 SumCal 지역변수의 result의 값을 대입하고 싶을때
	this.전역변수 = 지역변수
	*/
    // 현재 클래스 전역 변수와 NoticeService 지역 변수의 이름이 같은 상황
    // NoticeService의 noticeMapper의 값에
    // this 전역변수에 접근할때 씀 ( class
    // 서비스랑 매퍼랑 연결하는 역할
    // 서버를 킬때 전체 코드가 한번 실행됨 클래스 이름과 같은 메소드는 무조건 실행 ,
    @Override
    public int InsertNoticeInfo(NoticeDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "InsertNoticeInfo start !");
        int res = noticeMapper.InsertNoticeInfo(pDTO);
        log.info(this.getClass().getName() + "InsertNoticeInfo end !");
        return res;
    }

    @Override
    public List<NoticeDTO> getNoticeList() throws Exception {
        log.info(this.getClass().getName() + "InsertNoticeInfo start !");
        List<NoticeDTO> rList = noticeMapper.getNoticeList();
        log.info(this.getClass().getName() + "InsertNoticeInfo end !");
        return rList;
    }

    @Override
    public NoticeDTO getNoticeDetail(NoticeDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "getNoticeDetail start !");
        NoticeDTO rDTO = noticeMapper.getNoticeDetail(pDTO);
        log.info(this.getClass().getName() + "getNoticeDetail start !");
        return rDTO;
    }
}
