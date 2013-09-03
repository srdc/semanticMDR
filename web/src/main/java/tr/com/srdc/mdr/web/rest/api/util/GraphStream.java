package tr.com.srdc.mdr.web.rest.api.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import com.hp.hpl.jena.rdf.model.Model;

public class GraphStream implements StreamingOutput {

	private Model model;
	private String lang;

	public GraphStream() {
		this.model = null;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setLanguage(String lang) {
		this.lang = lang;
	}

	@Override
	public void write(OutputStream output) throws IOException,
			WebApplicationException {
		try {
			model.write(output, lang);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}
