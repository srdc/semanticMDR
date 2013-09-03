package tr.com.srdc.mdr.core.api.ai;

import tr.com.srdc.mdr.core.model.iso11179.RepresentationClassResource;

/**
 * Representation Class is the Classification Scheme for representation. The set
 * of classes make it easy to distinguish among the elements in the registry.
 * For instance, a data element categorized with the representation class
 * 'amount' is different from an element categorized as 'number'. It probably
 * won't make sense to compare the contents of these elements, or perform
 * calculations using them together.<br>
 * As an Administered Item, a Representation Class carries its own
 * Administration Record information, allowing it to be identified, named,
 * defined and optionally classified in a Classification Scheme.<br>
 * The major intent of Representation class is to provide a discrete and
 * complete set of high-level (coarse granularity) definitions for data
 * element/value domain categorization. This is an aid to the user in terms of
 * application of business rules.<br>
 * Representation Class is a mechanism by which the functional and/or
 * presentational category of an item may be conveyed to a user.<br>
 * An informational list of representation class terms is provided in ISO/IEC
 * 11179-5. The list below has been expanded to provide a more comprehensive
 * list of examples.<br>
 * <ul>
 * <li>Code -– A system of valid symbols that substitute for specified values
 * e.g. alpha, numeric, symbols and/or combinations.</li>
 * <li>Count –- Non-monetary numeric value arrived at by counting.</li>
 * <li>Currency –- Monetary representation</li>
 * <li>Date –- Calendar representation e.g. YYYY-MM-DD</li>
 * <li>Graphic –- Diagrams, graphs, mathematical curves, or the like – usually a
 * vector image.</li>
 * <li>Icon –- A sign or representation that stands for its object by virtue of
 * a resemblance or analogy to it</li>
 * <li>Picture –- A visual representation of a person, object, or scene –
 * usually a raster image.</li>
 * <li>Quantity –- A continuous number such as the linear dimensions,
 * capacity/amount (non-monetary) of an object</li>
 * <li>Text – A text field that is usually unformatted.</li>
 * <li>Time –- Time of day or duration eg HH:MM:SS.SSSS.</li>
 * </ul>
 * None of the terms in this list is required in any specific implementation of
 * representation class. By using representation class, enhanced semantic
 * control over the contents of value domains can be maintained. Rules can be
 * drawn against representation classes that allow enforcement of content within
 * and among value domains. For example:<br>
 * “A number-class data element cannot be used in a calculation.” “A date-class
 * data element must be in the format YYYY-MM-DD.” “A relationship must exist
 * between a code representation and the specific form of the value meanings
 * which the code represents.”
 * 
 * @author anil
 * 
 */
public interface RepresentationClass extends AdministeredItem {

	@Override
	/**
	 * @return the {@link RepresentationClassResource} version this instance.
	 */
	RepresentationClassResource asMDRResource();

}
