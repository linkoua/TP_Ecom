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

import fr.adaming.managedBean.ClientManagedBean;
public class FilterLoginCl implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//Instancier notre ManagedBean Admin
				ClientManagedBean clMB= (ClientManagedBean)((HttpServletRequest)request).getSession().getAttribute("clMB");
				//Regle si notre ManagedBean est null (aucun admin) ou que le boolean est false
				//Alors tu le renvoi sur login
				  if (clMB == null || !clMB.isLogInCl()) {
					  String contextPath = ((HttpServletRequest)request).getContextPath();
			            ((HttpServletResponse)response).sendRedirect(contextPath + "/login.xhtml");
			        }
			         
			        chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
	}

}
