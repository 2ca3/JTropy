package com.appspot.jtropy;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 2ca3
 * 
 */
@SuppressWarnings("serial")
public class MakeInServlet extends HttpServlet {
	private static Log log = LogFactory.getLog(MakeInServlet.class);

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			if (req.getSession().getAttribute("error") != null) {
				HttpSession session = req.getSession();
				req.setAttribute("error", session.getAttribute("error"));
				req.setAttribute("title", session.getAttribute("title"));
				req.setAttribute("body", session.getAttribute("body"));
				session.removeAttribute("error");
				session.removeAttribute("title");
				session.removeAttribute("body");
				if (session.getAttribute("key") != null) {
					req.setAttribute("key", session.getAttribute("key"));
					session.removeAttribute("key");
				}
			} else {
				PersistenceManager pm = PMF.get().getPersistenceManager();
				try {
					if (StringUtils.isNotEmpty(req.getParameter("key")) && StringUtils.isNumeric(req.getParameter("key"))) {
						String key = req.getParameter("key");
						JtropyBean jtropyBean = pm.getObjectById(JtropyBean.class, new Long(key));
						req.setAttribute("key", key);
						req.setAttribute("title", jtropyBean.getTitle());
						req.setAttribute("body", jtropyBean.getBody());
					}
				} finally {
					pm.close();
				}
			}
			getServletContext().getRequestDispatcher("/jsp/make_in.jsp").forward(req, res);
		} catch (ServletException e) {
			log.error(e, e);
		}
	}
}
