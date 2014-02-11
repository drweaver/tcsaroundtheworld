package org.tcsaroundtheworld.admin.server;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tcsaroundtheworld.admin.client.AdminControl;
import org.tcsaroundtheworld.common.server.db.DAO;


@SuppressWarnings("serial")
public class AwaitingApprovalsServlet extends HttpServlet {

	//TODO: Load these addresses from property file
	public static final String OTHER_ADDRESS = "OTHER_ADDRESS";
	public static final String ADMIN_ADDRESS = "ADMIN_ADDRESS";

	Logger log = Logger.getLogger(AwaitingApprovalsServlet.class.getName());

	private final DAO dao = new DAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {

		try {
			int familiesAwaitingApproval = dao.familiesAwaitingApproval();
			if( familiesAwaitingApproval > 0 ) {
				Properties props = new Properties();
				Session session = Session.getDefaultInstance(props, null);
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(ADMIN_ADDRESS, "TCS Around the World Admin"));
				msg.addRecipient(Message.RecipientType.TO,
						new InternetAddress(ADMIN_ADDRESS, "Vicki Macklin"));
				msg.addRecipient(Message.RecipientType.TO,
						new InternetAddress(OTHER_ADDRESS, "Shane Weaver"));

				msg.setSubject("Approvals waiting for TCS Around the World");
				StringBuilder sb = new StringBuilder();
				sb.append("There are currently ")
				.append(familiesAwaitingApproval)
				.append(" submissions awaiting approval.\n\n")
				.append("Please visit http://tcsaroundtheworld.appspot.com/administrator.html#")
				.append(AdminControl.AWAITINGAPPROVAL)
				.append(" to review.\n\n")
				.append("");
				msg.setText(sb.toString());
				Transport.send(msg);
			}
			resp.setStatus(HttpServletResponse.SC_OK);

		} catch (Exception e) {
			log.log(Level.SEVERE, "Failed to send approvals awaiting email: " + e.getMessage(), e);
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

}
