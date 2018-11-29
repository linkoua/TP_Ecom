package fr.adaming.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.adaming.managedBean.AdminManagedBean;

//Implementation de servlet filtre
public class FilterLoginAd implements Filter{

	@Override
	public void destroy() {	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//Instancier notre ManagedBean Admin
		AdminManagedBean adMB= (AdminManagedBean)((HttpServletRequest)request).getSession().getAttribute("adMB");
		//Regle si notre ManagedBean est null (aucun admin) ou que le boolean est false
		//Alors tu le renvoi sur login
		  if (adMB == null || !adMB.isLogIn()) {
			  String contextPath = ((HttpServletRequest)request).getContextPath();
	            ((HttpServletResponse)response).sendRedirect(contextPath + "/login.xhtml");
	        }
	         
	        chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	
}
