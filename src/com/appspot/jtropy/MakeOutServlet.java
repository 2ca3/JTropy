package com.appspot.jtropy;

import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author 2ca3
 * 
 */
@SuppressWarnings("serial")
public class MakeOutServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		boolean isError = false;
		if (StringUtils.isBlank(req.getParameter("title")) || req.getParameter("title").length() > 50) {
			req.getSession().setAttribute("error", "タイトルの指定が不正です。1～50文字で指定して下さい。");
			isError = true;
		} else if (StringUtils.isBlank(req.getParameter("body")) || req.getParameter("body").replaceAll("\n|\r\n", "").length() > 1000) {
			req.getSession().setAttribute("error", "本文の指定が不正です。1～1000文字で指定して下さい。");
			isError = true;
		}
		if (isError) {
			HttpSession session = req.getSession();
			if (StringUtils.isNotEmpty(req.getParameter("key")) && StringUtils.isNumeric(req.getParameter("key")))
				session.setAttribute("key", req.getParameter("key"));
			session.setAttribute("title", req.getParameter("title"));
			session.setAttribute("body", req.getParameter("body"));
			res.sendRedirect("/e");
			return;
		}

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			JtropyBean jtropyBean = null;
			if (StringUtils.isNotEmpty(req.getParameter("key")) && StringUtils.isNumeric(req.getParameter("key"))) {
				jtropyBean = pm.getObjectById(JtropyBean.class, new Long(req.getParameter("key")));
				jtropyBean.setTitle(StringEscapeUtils.escapeHtml(req.getParameter("title")));
				jtropyBean.setBody(StringEscapeUtils.escapeHtml(req.getParameter("body")));
				jtropyBean.setEditAt(new Date());
			} else {
				jtropyBean = new JtropyBean(StringEscapeUtils.escapeHtml(req.getParameter("title")), StringEscapeUtils.escapeHtml(req.getParameter("body")), new Date(), null);
			}
			pm.makePersistent(jtropyBean);
		} finally {
			pm.close();
		}
		res.sendRedirect("/");
	}
}
