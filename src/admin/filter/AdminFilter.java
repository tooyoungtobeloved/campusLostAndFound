package admin.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminL
 */
@WebFilter(urlPatterns={"/admin/pages/*","/admin/index.html"})
public class AdminFilter implements Filter {
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpSession hs = req.getSession();
        if(hs.getAttribute("admin") == null && hs.getAttribute("superadmin") == null) {
        	resp.sendRedirect("/lostandfound/admin/login.html");
        }
        else{
    		chain.doFilter(req, resp);
        }
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
