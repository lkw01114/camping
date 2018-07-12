package com.camping.home.bbs.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.camping.home.bbs.service.BbsService;
import com.camping.home.common.CommonUtil;
import com.camping.home.member.model.Member;
import com.camping.home.common.CommonFile;

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
		
		//TODO 새글 등록 글번호 가져오는 부분.
		int insertResult = bbsService.insertBbs(params);
		logger.info("insertResult >> " + insertResult);
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		
		String imageYN = CommonUtil.nvl(params.get("board_type")).equals("2") == true ? "Y" : "N";
		// 파일 물리적 저장.
		List<Map<String,Object>> fileList = CommonFile.getFile(files,imageYN, String.valueOf(board_type));		

		String thum = "";
		if(fileList != null && fileList.size() > 0)
		{
			strFileName = CommonUtil.nvl(fileList.get(0).get("fileAllList"));
			
			if("Y".equals(CommonUtil.nvl(fileList.get(0).get("thum"))))
			{
				thum = CommonUtil.nvl(fileList.get(0).get("fileName")) + "||" + CommonUtil.nvl(params.get("thum_alt"));
			}
		}
		
		/*
		if (file != null) {
			logger.info("file upload: " + file.getOriginalFilename());
			
			String saveFileName = null;
			try {
				saveFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("saveFileName", saveFileName);
			model.addAttribute("orgFileName", file.getOriginalFilename());		
		}
		*/		
		
		return "redirect:/bbs/bbs_list.do?menuseq="+board_type;
		
	}
	
	
	private String uploadFile(String orgFileName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + orgFileName;
		
		String absoluteFilePath = context.getRealPath(uploadPath);
		File target = new File(absoluteFilePath, savedName);
		FileCopyUtils.copy(fileData, target);
		
		return savedName;
	}
	
	@RequestMapping(value = "bbs_list")
	public String bbs_list(@RequestParam Map<String,Object> params, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		return "";
	}
	
	
}
