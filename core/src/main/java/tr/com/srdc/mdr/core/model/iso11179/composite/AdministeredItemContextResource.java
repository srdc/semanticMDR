package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ContextResource;



/**
 * 
 * This class is additionally created to handle the n-ary relation between
 * {@link AdministeredItemResource}, {@link TerminologicalEntryResource} and
 * {@link ContextResource}. Each {@link AdministeredItemContextResource} must have one
 * {@link ContextResource} and one {@link TerminologicalEntryResource}. Each
 * {@link AdministeredItemResource} must have at least one
 * {@link AdministeredItemContextResource}.
 * 
 * @author anil
 * 
 */
public interface AdministeredItemContextResource extends MDRResource {

	void setContext(ContextResource context);

	ContextResource getContext();

	void setTerminologicalEntry(TerminologicalEntryResource terminologicalEntry);

	TerminologicalEntryResource getTerminologicalEntry();

	void addGrouping(AdministeredItemResource administeredItem);

	void removeGrouping(AdministeredItemResource administeredItem);

	List<AdministeredItemResource> getGroupings() throws MDRException;

}