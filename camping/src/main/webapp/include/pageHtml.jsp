<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import="com.camping.home.common.StrUtil" %>

<%
	String pagename		= StrUtil.checkNull(request.getParameter("pagename"));
	String pagenum		= StrUtil.checkNull(request.getParameter("pagenum"));
	String totalPage	= StrUtil.checkNull(request.getParameter("totalPage"));
	String pageSize     = StrUtil.checkNull(request.getParameter("pageSize"));
	String SO			= StrUtil.checkNull(request.getParameter("SO"));
	if(!SO.equals("")) SO = StrUtil.replaceStr(SO, "^", "&amp;");

%>
<%  
if(Integer.parseInt(totalPage) > 0){
%>
<div class="page_area mt40">
<%	
	int blockpage = ((Integer.valueOf(pagenum) - 1) / 10) * 10 + 1;
%>
	<a rel="history" class="previous" href="javascript:<% if(blockpage == 1){  %>;<% } else { %>OnPage('<%=pagename%>','<%=blockpage-10%>', '<%=pageSize%>');<% } %>" title="previous">prev</a>
		<span class="num">
	
<%
	int i=1;
	while(i<10 && blockpage<=Integer.parseInt(totalPage)){
		if(blockpage == Integer.valueOf(pagenum)){
			out.println("<a class='on'>" + blockpage + "</a>");
		}else{
			out.println("<a rel='history' href=javascript:OnPage('" + pagename + "','" + blockpage + "','" + pageSize + "'); title=" + blockpage + ">" + blockpage + "</a>");		
		}
		
		blockpage+=1;
		i++;
	}

%>
	</span>
<a rel="history" class="next" href="javascript:<% if(blockpage > Integer.parseInt(totalPage)){ %>;<% }else { %>OnPage('<%=pagename%>','<%=blockpage%>', '<%=pageSize %>');<% } %>" title="next">next</a>
</div>
<%	
}
%>

