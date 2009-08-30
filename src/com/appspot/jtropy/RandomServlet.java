package com.appspot.jtropy;

import java.io.IOException;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 2ca3
 * 
 */
@SuppressWarnings("serial")
public class RandomServlet extends HttpServlet {
	private static Log log = LogFactory.getLog(RandomServlet.class);

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			int count = (Integer) pm.newQuery("SELECT count(id) FROM " + JtropyBean.class.getName()).execute();

			String title = "エラー発生";
			String body = "検索に失敗しました。申し訳御座いませんが、暫く時間を置いてからお試し下さい。";
			Long key = null;
			for (int i = 0; i < 3; i++) { // 念のため無限ループを避ける
				try {
					key = new Long(RandomUtils.nextInt(count) + 1);
					JtropyBean jtropyBean = pm.getObjectById(JtropyBean.class , key);
					title = jtropyBean.getTitle();
					body = jtropyBean.getBody().replaceAll("\r\n|\r|\n", "<br />");
					break;
				} catch (JDOObjectNotFoundException e) {
					// 削除は無いため基本的にありえない
					key = null;
				}
			}
			if(key != null) req.setAttribute("key", key);
			req.setAttribute("title", title);
			req.setAttribute("body", body);
			getServletContext().getRequestDispatcher("/jsp/random.jsp").forward(req, res);
		} catch (ServletException e) {
			log.error(e, e);
		} finally {
			pm.close();
		}
	}
}
