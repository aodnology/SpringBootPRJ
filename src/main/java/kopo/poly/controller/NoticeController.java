package kopo.poly.controller;

import kopo.poly.dto.NoticeDTO;
import kopo.poly.service.INoticeService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j // 중요
@Controller // 중요
public class NoticeController {

    @Resource(name = "NoticeService")
    private INoticeService noticeService;

    @GetMapping(value = "aodnology") // 주소 중요
    public String indexPage() throws Exception {
        log.info(this.getClass().getName() + ".indexPage Start !!");
        log.info(this.getClass().getName() + ".indexPage End !!");

        return "index"; // 내부 파일 중요
    }

    @GetMapping(value = "noticeInfo")
    public String noticeInfo() throws Exception {
        log.info(this.getClass().getName() + ".noticeInfo Start !!");
        log.info(this.getClass().getName() + ".noticeInfo End !!");
        return "form";
    }

    @PostMapping(value = "getNoticeData")
    public String getNoticeData(HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + ".getNoticeData Start !!");
        String title = CmmUtil.nvl(request.getParameter("title"));
        String username = CmmUtil.nvl(request.getParameter("username"));
        String contents = CmmUtil.nvl(request.getParameter("contents"));

        log.info("title : " + title);
        log.info("username : " + username);
        log.info("content : " + contents);

        model.addAttribute("title", title);
        model.addAttribute("username", username);
        model.addAttribute("contents", contents);

        log.info(this.getClass().getName() + ".getNoticeData End !!");
        return "getNoticeData";
    }

    @RequestMapping(value = "getInsertNotice")
    public String getInsertNotice(HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + ".getNoticeData Start !!");
        String reg_id = CmmUtil.nvl(request.getParameter("reg_id"));
        String title = CmmUtil.nvl(request.getParameter("title"));
        String contents = CmmUtil.nvl(request.getParameter("contents"));

        log.info("reg_id : " + reg_id);
        log.info("title : " + title);
        log.info("content : " + contents);

        NoticeDTO pDTO = new NoticeDTO();
        pDTO.setTitle(title);
        pDTO.setContents(contents);
        pDTO.setReg_id(reg_id);

        int res = noticeService.InsertNoticeInfo(pDTO);
        //저장되면 1의 값을 안되면 0의 값을 가져옴
        String msg;
        String url = "/getNoticeList";

        if(res>0){
            msg = "등록에 성공하셨습니다.";
        }else{
            msg = "등록에 실패하셨습니다.";
        }
        model.addAttribute("msg",msg);
        model.addAttribute("url",url);

        log.info(this.getClass().getName() + " .getNoticeData End !!");
        return "Redirect";
    }

    @RequestMapping(value ="getNoticeList")
    public String getNoticeList(HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + " .getNoticeList Start !!");

        List<NoticeDTO> rList = noticeService.getNoticeList();
        log.info(String.valueOf(rList.size()));
        if(rList == null){
            rList = new ArrayList<>();
        }
        model.addAttribute("rList",rList);

        log.info(this.getClass().getName() + " .getNoticeList End !!");

        return "NoticeList";
    }

    @RequestMapping(value = "NoticeDetail")
    public String NoticeDetail(HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + " .NoticeDetail Start !!");
        String notice_seq = CmmUtil.nvl(request.getParameter("no"));

        NoticeDTO pDTO = new NoticeDTO();
        pDTO.setNotice_seq(notice_seq);

        NoticeDTO rDTO = noticeService.getNoticeDetail(pDTO);
        if(rDTO == null){
            model.addAttribute("msg","존재하지 않는 게시물입니다!");
            model.addAttribute("url","getNoticeList");
            return "redirect";
        }

        model.addAttribute("rDTO", rDTO);
        log.info(this.getClass().getName() + " .NoticeDetail End !!");

        return "NoticeDetail";
    }
}
