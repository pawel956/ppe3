package com.pradyna.components.connexion.facebook;

import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.util.Throwables;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;

final class LocalServerReceiveFB implements VerificationCodeReceiver {
	private static final String LOCALHOST = "localhost";
	private static final String CALLBACK_PATH = "/Callback";
	private Server server;

	/* http://localhost:8080/Callback?#
	access_token={}
	&data_access_expiration_time={}
	&expires_in={}
	&state={} */

	public String code;

	public String access_token;
	public String data_access_expiration_time;
	public String expires_in;
	public String state;

	public String error;
	Map<String, String> resultat;


	final Semaphore waitUnlessSignaled;
	private int port;
	private final String host;
	private final String callbackPath;
	private String successLandingPageUrl;
	private String failureLandingPageUrl;

	public LocalServerReceiveFB() {
		this("localhost", 8080, "/Callback", (String)null, (String)null);
	}

	LocalServerReceiveFB(String host, int port, String successLandingPageUrl, String failureLandingPageUrl) {
		this(host, port, "/Callback", successLandingPageUrl, failureLandingPageUrl);
	}

	LocalServerReceiveFB(String host, int port, String callbackPath, String successLandingPageUrl, String failureLandingPageUrl) {
		this.waitUnlessSignaled = new Semaphore(0);
		this.host = host;
		this.port = port;
		this.callbackPath = callbackPath;
		this.successLandingPageUrl = successLandingPageUrl;
		this.failureLandingPageUrl = failureLandingPageUrl;
	}

	public String getRedirectUri() throws IOException {
		this.server = new Server(this.port != -1 ? this.port : 0);
		Connector connector = this.server.getConnectors()[0];
		connector.setHost(this.host);
		this.server.addHandler(new LocalServerReceiveFB.CallbackHandler());

		try {
			this.server.start();
			this.port = connector.getLocalPort();
		} catch (Exception var3) {
			Throwables.propagateIfPossible(var3);
			throw new IOException(var3);
		}

		return "http://" + connector.getHost() + ":" + this.port + this.callbackPath;
	}

	public String waitForCode() throws IOException {
		this.waitUnlessSignaled.acquireUninterruptibly();
		if (this.error != null) {
			throw new IOException("User authorization failed (" + this.error + ")");
		} else {
			return this.code;
		}
	}

	public Map<String, String> waitForData() throws IOException {
		this.waitUnlessSignaled.acquireUninterruptibly();
		if (this.error != null) {
			throw new IOException("User authorization failed (" + this.error + ")");
		} else {

			resultat = new HashMap<String, String>();
			resultat.put("access_token", this.access_token);
			resultat.put("data_access_expiration_time", this.data_access_expiration_time);
			resultat.put("expires_in", this.expires_in);
			resultat.put("state", this.state);
			return resultat;
		}
	}

	public void stop() throws IOException {
		this.waitUnlessSignaled.release();
		if (this.server != null) {
			try {
				this.server.stop();
			} catch (Exception var2) {
				Throwables.propagateIfPossible(var2);
				throw new IOException(var2);
			}

			this.server = null;
		}

	}

	public String getHost() {
		return this.host;
	}

	public int getPort() {
		return this.port;
	}

	public String getCallbackPath() {
		return this.callbackPath;
	}

	class CallbackHandler extends AbstractHandler {
		CallbackHandler() {
		}

		public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException {
			if (LocalServerReceiveFB.this.callbackPath.equals(target)) {
				try {
					((Request)request).setHandled(true);

					if (LocalServerReceiveFB.this.error == null && LocalServerReceiveFB.this.successLandingPageUrl != null) {
						response.sendRedirect(LocalServerReceiveFB.this.successLandingPageUrl);
					} else if (LocalServerReceiveFB.this.error != null && LocalServerReceiveFB.this.failureLandingPageUrl != null) {
						response.sendRedirect(LocalServerReceiveFB.this.failureLandingPageUrl);
					} else {
						this.writeLandingHtml(request, response);
					}

					response.flushBuffer();
				} finally {
					LocalServerReceiveFB.this.waitUnlessSignaled.release();
				}

			}
		}

		private void writeLandingHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {
			response.setStatus(200);
			response.setContentType("text/html");
			PrintWriter doc = response.getWriter();
			doc.println("<html>");
			doc.println("<head><title>OAuth 2.0 Authentication Token Received</title></head>");
			doc.println("<body>");
			doc.println("Received verification code. You may now close this window.");
			doc.println("</body>");
			doc.println("<script type='text/javascript'> if (window.location.hash !== '') { " +
					"window.location.href = window.location.href.replace('#', window.location.href.match(/(\\?#)/gmi) ? '' : '?'); " +
					"} </script>");
			doc.println("</html>");
			doc.flush();

			LocalServerReceiveFB.this.error = request.getParameter("error");
			LocalServerReceiveFB.this.code = request.getParameter("code");
			LocalServerReceiveFB.this.access_token = request.getParameter("access_token");
			LocalServerReceiveFB.this.data_access_expiration_time = request.getParameter("data_access_expiration_time");
			LocalServerReceiveFB.this.expires_in = request.getParameter("expires_in");
			LocalServerReceiveFB.this.state = request.getParameter("state");
		}
	}

	public static final class Builder {
		private String host = "localhost";
		private int port = -1;
		private String successLandingPageUrl;
		private String failureLandingPageUrl;
		private String callbackPath = "/Callback";

		public Builder() {
		}

		public LocalServerReceiveFB build() {
			return new LocalServerReceiveFB(this.host, this.port, this.callbackPath, this.successLandingPageUrl, this.failureLandingPageUrl);
		}

		public String getHost() {
			return this.host;
		}

		public LocalServerReceiveFB.Builder setHost(String host) {
			this.host = host;
			return this;
		}

		public int getPort() {
			return this.port;
		}

		public LocalServerReceiveFB.Builder setPort(int port) {
			this.port = port;
			return this;
		}

		public String getCallbackPath() {
			return this.callbackPath;
		}

		public LocalServerReceiveFB.Builder setCallbackPath(String callbackPath) {
			this.callbackPath = callbackPath;
			return this;
		}

		public LocalServerReceiveFB.Builder setLandingPages(String successLandingPageUrl, String failureLandingPageUrl) {
			this.successLandingPageUrl = successLandingPageUrl;
			this.failureLandingPageUrl = failureLandingPageUrl;
			return this;
		}
	}
}

