package com.camping.home.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


public class CommonFile {
	
	
	@Autowired
	static
	ServletContext context;
	
	/**
	 * 파일저장.
	 * 
	 * @param files
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static List<Map<String,Object>> getFile(Map<String, MultipartFile> files,String imageYN,String seq) throws IllegalStateException, IOException{
		if(files.isEmpty()) return null;
			
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		List<Map<String,Object>> result  = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> thumResult  = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> defaultResult  = new ArrayList<Map<String,Object>>();
		seq = CommonUtil.nvl(seq);
		
		int idx = 0;
		boolean thumFlag = false;
		
		while (itr.hasNext()) {
			Map<String,Object> fileMap = new HashMap<String, Object>();
			
		    Entry<String, MultipartFile> entry = itr.next();
		    String formName = (String)entry.getKey();
		    System.out.println("formName >>> " + formName);
		    file = entry.getValue();
		    String orginFileName = file.getOriginalFilename();
		    
		    if ("".equals(orginFileName)) {
		    	continue;
		    }
		    String path = "";
		    String addFolder = "";	//seq에 따라서 폴더생성 변수
		    
		    
		    //path = PropertyUtil.getProperty("rootPath")+PropertyUtil.getProperty("Global.filePath2");
		    path = PropertyUtil.getProperty("Global.rootPath")+PropertyUtil.getProperty("Global.filePath2");
		    path = path.replace("//", "/");
		    path = path.replace("\\", "/");
		    System.out.println("path >>>> " + path);
		    
		    File f = new File(path);
		    
		    if(!f.isDirectory()) f.mkdirs();
		    
		    if(CommonUtil.isNotEmpty(seq))
		    {
		    	String newFolderName = "/bbs"+seq+"/";
		    	addFolder = path+newFolderName;
		    	
		    	addFolder = addFolder.replace("//", "/");
		    	addFolder = addFolder.replace("\\", "/");
		    	File f2 = new File(addFolder);
			    
			    if(!f2.isDirectory()) f2.mkdirs();
			    
			    orginFileName = newFolderName + orginFileName;
		    }
		    
		    String fileFullPath = path + orginFileName;
		    System.out.println("fileFullPath >> " + fileFullPath);
		    String fileExt = orginFileName.substring(orginFileName.lastIndexOf(".") + 1);
		    file.transferTo(new File(fileFullPath));
		    fileMap.put("fileSize", Long.toString(file.getSize()));
		    fileMap.put("fileName", orginFileName);
		    fileMap.put("fileFullPath", fileFullPath);
		    fileMap.put("fileExt", fileExt);
		    
		    
		    if("thum".equals(formName))
		    {
		    	fileMap.put("thum", "Y");
		    	thumResult.add(fileMap);
		    	thumFlag = true;
		    }
		    else
		    {
		    	if(idx==0)
		    	{
		    		fileMap.put("thum","");
		    	}
		    	defaultResult.add(fileMap);
		    }
		    idx++;
		}
		
		if(thumResult != null && thumResult.size() > 0)
		{
			result.add(0,(Map)thumResult.get(0));
		}
		
		if(defaultResult != null && defaultResult.size() > 0)
		{
			//占쏙옙占쏙옙占쏙옙
			Collections.reverse(defaultResult);
			for(int i=0; i<defaultResult.size(); i++)
			{
				Map row = (Map)defaultResult.get(i);
				result.add(row);
			}
		}
		
		//占쏙옙占싹몌옙 占쏙옙占�
		if(result != null && result.size() > 0)
		{
			ArrayList<String> tmpStrings = new ArrayList<String>();
			for(int i=0; i<result.size(); i++)
			{	
				if(thumFlag && i==0) continue;
				
				Map<?,?> row = (Map<?,?>)result.get(i);
				tmpStrings.add(CommonUtil.nvl(row.get("fileName")));
			}
			result.get(0).put("fileAllList", CommonUtil.arrayJoin("||", tmpStrings.toArray(new String[tmpStrings.size()])));
		}
		
		return result;
	}
	
	/**
	 * txt form占쏙옙 占쏙옙占쏙옙占쏙옙 Map<String,Strnig[]>占쏙옙占쏙옙 占쏙옙환占쌌니댐옙.
	 * @param files
	 * @return
	 * @throws IOException
	 */
	public static Map<String,Object> getFileToArray(Map<String, MultipartFile> files) throws IOException{
		if(files.isEmpty()) return null;
		
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file = null;
		Map<String,Object> result = new HashMap<String,Object>();
		
		while (itr.hasNext()) 
		{
		    Entry<String, MultipartFile> entry = itr.next();
		    
		    String formName = (String)entry.getKey();
		    
		    file = entry.getValue();
		    
		    String orginFileName = file.getOriginalFilename();
		    
		    //form 占싱몌옙占쏙옙 txt占쏙옙 占쏙옙占쌜되는것몌옙 占쌔댐옙 占쌨소드에占쏙옙 처占쏙옙 占쏙옙占쏙옙 -> Exception 占쏙옙占쏙옙
		    if(formName.indexOf("txt") == -1 || "".equals(orginFileName)) continue;
		    
		    String[] resultArr = pathToArray(file.getInputStream());
		    
		    result.put(formName, resultArr);
		    
		}
		return result;
	}
	
	/**
	 * txt 占쏙옙占쏙옙占쏙옙 Array占쏙옙 占쏙옙占쏙옙
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private static String[] pathToArray(InputStream is) throws IOException{
		InputStreamReader r = new InputStreamReader(is);
		List<String> lines = new ArrayList<String>();
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(r);
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            lines.add(line);
	        }
	    } finally {
	        reader.close();
	    }
	    return lines.toArray(new String[lines.size()]);
	}
}
