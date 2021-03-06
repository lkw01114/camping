package com.camping.home.bbs.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.camping.home.bbs.model.Bbs;
import com.camping.home.bbs.model.FileVO;
import com.camping.home.bbs.service.BbsService;
import com.camping.home.common.CommonFile;
import com.camping.home.common.CommonUtil;
import com.camping.home.member.model.Member;

@Controller
@RequestMapping("/bbs/*")
public class BbsController {
	
	@Autowired
	BbsService bbsService;	
	@Autowired
	private String uploadPath;
	@Autowired
	ServletContext context;	
	
	//logger
	public static final Logger logger = LoggerFactory.getLogger(BbsController.class);
	private static final String URL_OF_COMMON = "/bbs/";
	
	/**
	 * 게시판 글쓰기 폼페이지 이동
	 * @param model
	 * @param menuseq
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "bbs_write")
	public String bbsWrite(Model model, @RequestParam Map<String,Object> params, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 왜 interceptor에서 안들어 오는건가???
		//logger.info("menuseq ^^^^^^^" + menuseq);
		logger.info("bbs_write");
		int menuSeq = CommonUtil.nvl(params.get("menuseq"), 0);
		model.addAttribute("categoryList", bbsService.listCategory());
		model.addAttribute("menuSeq", menuSeq);
		return URL_OF_COMMON + "bbs_write";
	}
	
	
	/**
	 * 게시판 글저장
	 * @param multiRequest
	 * @param params
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "bbs_writeAction")
	public String bbsWriteAction(final MultipartHttpServletRequest multiRequest, 
			@RequestParam Map<String,Object> params, Model model, HttpServletRequest request, HttpServletResponse response)throws Exception {
		// 세션 가져오기
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");		
		
		String strFileName ="";
		int board_type = CommonUtil.nvl(params.get("board_category"), 0);
		params.put("board_type", board_type);
		params.put("reg_id", member.getId());
		
		// board Group Max Number
		int maxRef = bbsService.getMaxRef();
		logger.info("maxRef >> " + maxRef);
		params.put("ref", maxRef + 1);
		params.put("re_step", 1);
		params.put("re_level", 1);
		
		// 등록 성공여부
		int insertResult = bbsService.insertBbs(params);
		
		logger.info("insertResult >> " + insertResult);
		logger.info("idx >  " + params.get("idx"));
		
		// 등록된 게시판 번호
		String insertIdx = CommonUtil.nvlTrim(params.get("idx"));
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		String imageYN = CommonUtil.nvl(params.get("board_type")).equals("2") == true ? "Y" : "N";
		
		// 파일 물리적 저장.
		List<Map<String,Object>> fileList = CommonFile.getFile(files,imageYN, String.valueOf(board_type));		

		// 파일 DB 저장
		if(fileList != null && fileList.size() > 0){
			
			strFileName = CommonUtil.nvl(fileList.get(0).get("fileAllList"));
			logger.info("strFileName >> " + strFileName);
			String[] spStrNames = strFileName.split("\\|\\|");
			for(String fileName : spStrNames) {
				params.put("board_idx", insertIdx);
				params.put("file_name", fileName);
				bbsService.insertFile(params);
			}			
		}
		
		return "redirect:/bbs/bbs_list?menuseq="+board_type;
	}
	
	
	
	/**
	 * 게시판 리스트
	 * @param params
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "bbs_list")
	public String bbs_list(@RequestParam Map<String,Object> params, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		String menuSeq = CommonUtil.nvl(params.get("menuseq"),"0");

		String page = CommonUtil.nvlTrim(request.getParameter("page")).equals("") ? "1" : request.getParameter("page");
		String pageSize = CommonUtil.nvlTrim(request.getParameter("pageSize")).equals("") ? "10" : request.getParameter("pageSize");

		int resultPageSize = Integer.parseInt(pageSize) == 0 ? BbsService.pager : Integer.parseInt(pageSize);
		int startCount = (Integer.parseInt(page) - 1) * resultPageSize + 1; // startCount
		int endCount = Integer.parseInt(page) * resultPageSize; // endCount
		params.put("board_type", menuSeq);
		params.put("startCount", startCount);
		params.put("endCount", endCount);
		
		try {
			model.addAttribute("bbsList", bbsService.selectBbsList(params));
			int totalCount = bbsService.selectBbsCount(params); // totalCount
			int countnum = totalCount - (Integer.parseInt(pageSize) * (Integer.parseInt(page) - 1)); // countnum
			int totalPage = totalCount;
			double tempCount = 0;
			double tempTotalPage = 0;
			tempCount = totalCount;
			if (resultPageSize != 0) {
				tempTotalPage = (double) tempCount / Integer.parseInt(pageSize);
			} else {
				tempTotalPage = (double) tempCount / BbsService.pager;
			}

			totalPage = (int) Math.ceil(tempTotalPage);

			model.addAttribute("countnum", countnum);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("page", page);
			model.addAttribute("pagename", "/bbs/bbs_list");
			model.addAttribute("pageSize", resultPageSize);
			model.addAttribute("menuseq", menuSeq);
			return URL_OF_COMMON + "bbs_list";		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	/**
	 * 게시물 상세보기
	 * @param model
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "bbs_detail")
	public String bbs_detail(Model model, @RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String page = CommonUtil.nvlTrim(request.getParameter("page")).equals("") ? "1" : request.getParameter("page");
		
		// 조회수 증가.
		bbsService.updateReadNum(params);
		model.addAttribute("bbs", bbsService.detailBbs(params)); 		// 현재 글 정보
		params.put("board_idx", CommonUtil.nvl(params.get("idx")));
		model.addAttribute("file", bbsService.selectFileList(params)); 	// 파일 가져오기
		model.addAttribute("page", page);
		model.addAttribute("menuseq", CommonUtil.nvlTrim(params.get("menuseq")));
		model.addAttribute("pageSize", CommonUtil.nvlTrim(request.getParameter("pageSize")));
		
		return  URL_OF_COMMON + "bbs_detail";
	}
	
	
	/**
	 * 게시물 삭제
	 * @param model
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "bbs_delete")
	public String bbs_delete(Model model, @RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String menuSeq = CommonUtil.nvl(params.get("menuseq"),"0");
		
		params.put("idx", CommonUtil.nvl(params.get("idx")));
		params.put("board_idx", CommonUtil.nvl(params.get("idx")));
		Bbs bbs  = bbsService.detailBbs(params);

		bbsService.deletefileBoard(params);
		
		//공지사항 || 포토갤러리 바로 삭제처리
		if(bbs.getBoard_type() == 0 || bbs.getBoard_type() ==2) {
			bbsService.deleteboard(params);
		}else if(bbs.getBoard_type() == 1) {	// 예약문의
			bbsService.delflag_update(params);
		}
		
		return "redirect:/bbs/bbs_list?menuseq="+menuSeq;
	}
	
	
	/**
	 * 게시판 답변글쓰기 폼페이지 이동
	 * @param model
	 * @param menuseq
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "bbs_Rewrite")
	public String bbs_Rewrite(Model model, @RequestParam Map<String,Object> params, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 왜 interceptor에서 안들어 오는건가???
		//logger.info("menuseq ^^^^^^^" + menuseq);
		logger.info("bbs_write");
		int menuSeq = CommonUtil.nvl(params.get("menuseq"), 0);
		
		model.addAttribute("categoryList", bbsService.listCategory());
		model.addAttribute("menuSeq", menuSeq);
		model.addAttribute("idx", CommonUtil.nvl(params.get("idx")));
		model.addAttribute("ref", CommonUtil.nvl(params.get("ref")));
		model.addAttribute("re_step", CommonUtil.nvl(params.get("re_step")));
		model.addAttribute("re_level", CommonUtil.nvl(params.get("re_level")));
		return URL_OF_COMMON + "bbs_write";
	}
	
	/**
	 * 게시판 답변 글저장
	 * @param multiRequest
	 * @param params
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "bbs_RewriteAction")
	public String bbs_RewriteAction(final MultipartHttpServletRequest multiRequest, 
			@RequestParam Map<String,Object> params, Model model, HttpServletRequest request, HttpServletResponse response)throws Exception {
		// 세션 가져오기
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");		
		
		String strFileName ="";
		int board_type = CommonUtil.nvl(params.get("board_category"), 0);
		params.put("board_type", board_type);
		params.put("reg_id", member.getId());
		
		// 같은 그룹의 글을 대상 부모글보다 큰 레벨의 값을 모두 1씩 증가
		
		params.put("ref", CommonUtil.nvlTrim(params.get("ref"),"0"));
		params.put("re_step", CommonUtil.nvlTrim(params.get("re_step"),"0"));
		params.put("re_level", CommonUtil.nvlTrim(params.get("re_level"),"0"));
		
		// 등록 성공여부
		int insertResult = bbsService.insertBbs(params);
		
		logger.info("insertResult >> " + insertResult);
		logger.info("idx >  " + params.get("idx"));
		
		// 등록된 게시판 번호
		String insertIdx = CommonUtil.nvlTrim(params.get("idx"));
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		String imageYN = CommonUtil.nvl(params.get("board_type")).equals("2") == true ? "Y" : "N";
		
		// 파일 물리적 저장.
		List<Map<String,Object>> fileList = CommonFile.getFile(files,imageYN, String.valueOf(board_type));		

		// 파일 DB 저장
		if(fileList != null && fileList.size() > 0){
			
			strFileName = CommonUtil.nvl(fileList.get(0).get("fileAllList"));
			logger.info("strFileName >> " + strFileName);
			String[] spStrNames = strFileName.split("\\|\\|");
			for(String fileName : spStrNames) {
				params.put("board_idx", insertIdx);
				params.put("file_name", fileName);
				bbsService.insertFile(params);
			}			
		}
		
		return "redirect:/bbs/bbs_list?menuseq="+board_type;
	}	
	
	
	
	
	
	
	
	
}
